package ch.bbw.pr.sospri.member;

import org.springframework.stereotype.Service;

/**
 * @author Lewin Gerber
 * @version 09.04.2021
 * 50_02_SpringBootChatAppSoSpriV01
 */

@Service
public class UrlService {
    String requestedUrl = "";

    public String getRequestedUrl() {
        return requestedUrl;
    }

    public void setRequestedUrl(String requestedUrl) {
        this.requestedUrl = requestedUrl;
    }
}
