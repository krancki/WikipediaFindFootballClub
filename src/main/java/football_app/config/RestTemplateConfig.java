package football_app.config;

import football_app.searcher.exception.WikipediaResponseErrorHandler;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Configuration
class RestTemplateConfig {

    @Bean
    public RestTemplate prepareRestTemplate() {
        return new RestTemplateBuilder()
                .setReadTimeout(Duration.of(1, ChronoUnit.SECONDS))
                .setConnectTimeout(Duration.of(1, ChronoUnit.SECONDS))
                .errorHandler(new WikipediaResponseErrorHandler())
                .build();
    }
}
