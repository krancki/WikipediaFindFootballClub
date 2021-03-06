package football_app.searcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
class SearchFootballClubTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private static final WireMockServer wm = new WireMockServer(options().port(9000));

    @BeforeAll
    static void setUp() {
        wm.start();
    }

    @BeforeAll
    static void after() {
        wm.stop();
    }


    @Test
    void shouldReturnRepository() throws Exception {
        //given
        wm.stubFor(get(urlEqualTo("/w/api.php?action=query&format=json&list=search&srsearch=football%20club%20ClubName&srinfo=&srprop="))
                .willReturn(aResponse().withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(getExampleJsonResponseFromWikipedia())
                )
        );

        //when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/ClubName"));

        //then
        String responseContent = response.andReturn().getResponse().getContentAsString();
        response.andExpect(status().isOk());
        List<ClubLinkDto> clubs = List.of(objectMapper.readValue(responseContent, ClubLinkDto[].class));

        assertEquals(18119L, clubs.get(0).getPageId());
        assertEquals(8247848L, clubs.get(1).getPageId());
        assertEquals("https://pl.wikipedia.org/wiki/Liverpool_F.C.", clubs.get(0).getUrl());
        assertEquals("https://pl.wikipedia.org/wiki/Liverpool_F.C._Women", clubs.get(1).getUrl());

    }

    @Test
    void shouldThrowExceptionWhenWikipediaIsNotAvailable() throws Exception {
        //given
        wm.stubFor(get(anyUrl())
                .willReturn(aResponse().withStatus(HttpStatus.BAD_GATEWAY.value()).withBody("Error"))
        );

        //expect
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/ClubName"))
                .andExpect(status().isServiceUnavailable());
    }

    String getExampleJsonResponseFromWikipedia() throws Exception {
        return new String(Files.readAllBytes(Paths.get("src/test/resources/SampleWikipediaResponse.json")));
    }
}