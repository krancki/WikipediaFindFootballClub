package football_app.searcher;

import football_app.config.WikipediaProperties;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
class WikipediaDataMapper {

    private final ClubLinkFactory clubLinkFactory;
    private final WikipediaProperties wikipediaProperties;

    List<ClubLinkDto> mapFootballDataToClubLinkDto(WikipediaApiSearchData wikipediaApiSearchData) {
        return wikipediaApiSearchData.getFootballClubData().stream()
                .map(footballData -> new ClubLinkDto(
                        footballData.getPageId(),
                        clubLinkFactory.getWikipediaLink(
                                wikipediaProperties.getUrl(),
                                footballData.getTitle())
                ))
                .collect(Collectors.toUnmodifiableList());
    }
}
