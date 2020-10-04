package football_app.searcher.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

public class WikipediaResponseErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        HttpStatus statusCode = clientHttpResponse.getStatusCode();
        return statusCode.is4xxClientError()
                || statusCode.is5xxServerError();
    }

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
        HttpStatus statusCode = clientHttpResponse.getStatusCode();
        if (statusCode.is4xxClientError()) {
            throw new ResponseStatusException(statusCode, clientHttpResponse.getStatusText());
        }
        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Github is not available");
    }
}
