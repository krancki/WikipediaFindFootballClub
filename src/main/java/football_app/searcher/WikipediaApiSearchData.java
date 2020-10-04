package football_app.searcher;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.util.List;

@Value
class WikipediaApiSearchData {

    WikipediaApiQueryData wikipediaApiQueryResponse;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public WikipediaApiSearchData(@JsonProperty("query") WikipediaApiQueryData wikipediaApiQueryResponse) {
        this.wikipediaApiQueryResponse = wikipediaApiQueryResponse;
    }

    List<WikipediaApiFootballClubData> getFootballClubData() {
        return wikipediaApiQueryResponse.getWikipediaApiFootBallClubData();
    }
}
