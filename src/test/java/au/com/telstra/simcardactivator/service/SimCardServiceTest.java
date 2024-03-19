package au.com.telstra.simcardactivator.service;

import au.com.telstra.simcardactivator.data.SimCardRequest;
import au.com.telstra.simcardactivator.entity.SimCard;
import au.com.telstra.simcardactivator.repo.SimCardRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;

import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
class SimCardServiceTest {

    @InjectMocks
    private SimCardService service;

    @Mock
    private SimCardRepo simCardRepo;

    private SimCardRequest simCardRequest;

    @BeforeEach
    void setup() {
        simCardRequest = new SimCardRequest();
        simCardRequest.setIccid("iccid");
        simCardRequest.setCustomerEmail("customerEmail");
    }

    @Test
    void sendSimCardDetails() {
        String response = service.sendSimCardDetails(simCardRequest);
        Assertions.assertEquals("FAILED", response);
    }

    @Test
    void getSimCardDetails() {
        Mockito.when(simCardRepo.getReferenceById(anyLong())).thenReturn(new SimCard());
        Assertions.assertTrue(Objects.nonNull(service.getSimCardDetails(1L)));
    }
}