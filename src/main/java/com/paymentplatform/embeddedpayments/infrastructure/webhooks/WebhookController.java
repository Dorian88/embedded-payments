package com.paymentplatform.embeddedpayments.infrastructure.webhooks;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/webhooks")
public class WebhookController {

    @PostMapping
    public ResponseEntity<Map<String, String>> receive(@RequestBody String payload) {
        return ResponseEntity.accepted().body(Map.of("status", "received", "size", String.valueOf(payload.length())));
    }
}

