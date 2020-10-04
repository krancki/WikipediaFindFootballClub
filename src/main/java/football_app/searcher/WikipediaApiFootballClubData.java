package football_app.searcher;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
class WikipediaApiFootballClubData {
    String title;
    Long pageId;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public WikipediaApiFootballClubData(@JsonProperty("title") String title,
                                        @JsonProperty("pageid") Long pageId) {
        this.title = title;
        this.pageId = pageId;
    }
}
