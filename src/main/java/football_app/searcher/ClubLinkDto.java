package football_app.searcher;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Value;

@Value
class ClubLinkDto {
    Long pageId;
    String url;
    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ClubLinkDto(Long pageId, String url) {
        this.pageId = pageId;
        this.url = url;
    }
}
