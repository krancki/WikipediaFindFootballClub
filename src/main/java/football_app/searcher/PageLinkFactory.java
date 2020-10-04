package football_app.searcher;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class PageLinkFactory {
    String getWikipediaLink(String url, String pageName) {
        return url + pageName.replace(" ", "_");
    }
}
