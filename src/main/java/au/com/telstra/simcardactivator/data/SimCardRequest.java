package au.com.telstra.simcardactivator.data;

import lombok.Data;

@Data
public class SimCardRequest {
    private String iccid;
    private String customerEmail;
}
