package org.david.crytocurrency.http;

import com.slack.api.Slack;
import com.slack.api.webhook.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SlackHttpClient {
    public void send(String message) {
        try{
            Slack slack = Slack.getInstance();
            Payload payload = Payload.builder().text(message).build();
            slack.send("https://hooks.slack.com/services/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", payload);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
