package au.com.telstra.simcardactivator.service;

import au.com.telstra.simcardactivator.data.SimCardRequest;
import au.com.telstra.simcardactivator.data.SimCardResponse;
import au.com.telstra.simcardactivator.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Service
@Slf4j
public class SimCardService {

    public String sendSimCardDetails(SimCardRequest simCardRequest) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://localhost:8444/actuate")
                .post(RequestBody.create(MediaType.parse("application/json"), JsonUtil.getJson(simCardRequest)))
                .build();

        try {
            Response response = client.newCall(request).execute();
            String body = Objects.requireNonNull(response.body()).string();
            SimCardResponse apiResponse = JsonUtil.getObject(body, SimCardResponse.class);
            if (apiResponse.isSuccess()) {
                return "SUCCESS";
            }
        } catch (IOException e) {
            log.error("failed to get API response");
        }
        return "FAILED";
    }
}
