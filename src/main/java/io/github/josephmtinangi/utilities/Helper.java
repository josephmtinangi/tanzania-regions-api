package io.github.josephmtinangi.utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

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

	public static String saveFile(MultipartFile file, String rootStorage) throws Exception {

		String fileName = "";
		String fileHashName = "";

		new File(rootStorage + "/").mkdirs();

		try {
			// Get the file and save it somewhere
			byte[] bytes = file.getBytes();

			fileName = file.getOriginalFilename();

			// System.out.println(fileName);

			String fileNameTokens[] = fileName.split("\\.");

			fileHashName = UUID.randomUUID().toString();

			if (fileNameTokens.length > 1) {
				fileHashName += "." + fileNameTokens[fileNameTokens.length - 1];
			}

			Path path = Paths.get(rootStorage + "/" + fileHashName);

			Files.write(path, bytes);

			// System.out.println(path);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return fileHashName;

	}

        return output;
    }
}
