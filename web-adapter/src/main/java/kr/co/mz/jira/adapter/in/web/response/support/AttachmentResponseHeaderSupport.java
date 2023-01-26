package kr.co.mz.jira.adapter.in.web.response.support;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;

public interface AttachmentResponseHeaderSupport {

    default HttpHeaders proceedResponseHeaders(final String filename) {
        final var contentDisposition = ContentDisposition
            .attachment()
            .filename(filename)
            .build();

        final var responseHeaders = new HttpHeaders();

        responseHeaders.setContentDisposition(contentDisposition);

        return responseHeaders;
    }
}
