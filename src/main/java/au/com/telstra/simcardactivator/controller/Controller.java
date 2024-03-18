package au.com.telstra.simcardactivator.controller;

import au.com.telstra.simcardactivator.data.SimCardRequest;
import au.com.telstra.simcardactivator.service.SimCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sim-card")
public class Controller {

    @Autowired
    private SimCardService service;

    @PostMapping("/activation")
    public ResponseEntity<String> addAdAccountToFacebookProfile(
            @RequestBody SimCardRequest simCardRequest) {
        return new ResponseEntity<>(service.sendSimCardDetails(simCardRequest), HttpStatus.OK);
    }

}
