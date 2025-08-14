package me.lyamray.minestead.license;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
public class LicenseChecker {

    @Getter
    private static final LicenseChecker instance = new LicenseChecker();

    public void checkLicense() throws IOException, InterruptedException {
        String accountId = "b22ccdb1-ac00-4727-976c-4637dac173e7";
        String licenseKey = "FPE3-9KJM-T9YA-L9VV-UWLC-JXM9-H9XL-XYXE";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.keygen.sh/v1/accounts/" + accountId + "/licenses/actions/validate-key"))
                .header("Content-Type", "application/vnd.api+json")
                .POST(HttpRequest.BodyPublishers.ofString(
                        "{ \"meta\": { \"key\": \"" + licenseKey + "\" } }"
                ))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            String body = response.body();

            boolean valid = body.contains("\"valid\":true");

            String detail = extractDetail(body);

            if (valid) {
                log.warn("De licentie is juist: {}", detail);
            } else {
                log.warn("De licentie is onjuist: {} Contacteer LyamRay.", detail);
            }
        } else {
            log.warn("De licentie is onjuist of vervallen: {} Contacteer LyamRay.", response.body());
        }
    }

    private String extractDetail(String json) {
        final String DETAIL_KEY = "\"detail\":\"";
        int startIndex = json.indexOf(DETAIL_KEY);
        if (startIndex == -1) {
            return "Detail not available";
        }

        startIndex += DETAIL_KEY.length();
        int endIndex = json.indexOf("\"", startIndex);
        if (endIndex == -1) {
            return "Detail not available";
        }

        return json.substring(startIndex, endIndex).trim();
    }
}
