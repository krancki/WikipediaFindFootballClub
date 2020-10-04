package football_app.searcher;

import football_app.config.WikipediaProperties;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
class ClubSearcherService implements ClubSearcher {

    private final RestTemplate restTemplate;
    private final WikipediaProperties wikipediaProperties;
    private final WikipediaDataMapper wikipediaDataMapper;

    @Override
    public List<ClubLinkDto> findClubsByClubName(String clubName) {

        Optional<WikipediaApiSearchData> searchedWikipediaFootballClubs = Optional.ofNullable(
                restTemplate.getForObject(prepareRequestLinkToGetFootballClubs(clubName), WikipediaApiSearchData.class)
        );

        return searchedWikipediaFootballClubs.map(wikipediaDataMapper::mapFootballDataToClubLinkDto).orElse(List.of());
    }

    private String prepareRequestLinkToGetFootballClubs(String clubName) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(wikipediaProperties.getApiUrl());
        return uriComponentsBuilder.queryParam("action", "query")
                .queryParam("format", "json")
                .queryParam("list", "search")
                .queryParam("srsearch", "football club " + clubName)
                .queryParam("srinfo", "")
                .queryParam("srprop", "")
                .build().toUriString();
    }
}