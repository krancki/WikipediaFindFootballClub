package football_app.searcher;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.util.List;

@Value
class WikipediaApiQueryData {

    List<WikipediaApiFootballClubData> wikipediaApiFootBallClubData;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public WikipediaApiQueryData(@JsonProperty("search") List<WikipediaApiFootballClubData> wikipediaApiFootBallClubData) {
        this.wikipediaApiFootBallClubData = wikipediaApiFootBallClubData;
    }
}