package io.github.josephmtinangi.utilities;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

public class Helper {

    @Value("${app.url}")
    private static String appURL;

    public static ResponseEntity<?> createResponse(Object data, HttpStatus code) {

        return new ResponseEntity<>(data, code);
    }

    public static Object createMessage(String message) {

        HashMap<String, Object> output = new HashMap<>();
        output.put("message", message);
        output.put("documentation_url", appURL + "/docs");

        return output;
    }
}
