package football_app.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "wikipedia")
@Getter
@Setter
public class WikipediaProperties {
    private String url;
    private String apiUrl;
}
