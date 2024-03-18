package au.com.telstra.simcardactivator.service;

import au.com.telstra.simcardactivator.data.SimCardRequest;
import au.com.telstra.simcardactivator.data.SimCardResponse;
import au.com.telstra.simcardactivator.entity.SimCard;
import au.com.telstra.simcardactivator.repo.SimCardRepo;
import au.com.telstra.simcardactivator.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Service
@Slf4j
public class SimCardService {

    @Autowired
    private SimCardRepo simCardRepo;

    public String sendSimCardDetails(SimCardRequest simCardRequest) {
        OkHttpClient client = new OkHttpClient();

        SimCard simCard = new SimCard();
        simCard.setIccid(simCardRequest.getIccid());
        simCard.setCustomerEmail(simCardRequest.getCustomerEmail());
        simCard.setActive(Boolean.FALSE);

        Request request = new Request.Builder()
                .url("http://localhost:8444/actuate")
                .post(RequestBody.create(MediaType.parse("application/json"), JsonUtil.getJson(simCardRequest)))
                .build();

        try {
            Response response = client.newCall(request).execute();
            String body = Objects.requireNonNull(response.body()).string();
            SimCardResponse apiResponse = JsonUtil.getObject(body, SimCardResponse.class);
            if (apiResponse.isSuccess()) {
                simCard.setActive(Boolean.TRUE);
                return "SUCCESS";
            }
        } catch (IOException e) {
            log.error("failed to get API response");
        }
        simCardRepo.save(simCard);
        return "FAILED";
    }

    public SimCard getSimCardDetails(Long id) {
        return simCardRepo.getReferenceById(id);
    }
}
