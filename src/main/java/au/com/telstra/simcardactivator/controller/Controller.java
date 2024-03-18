package au.com.telstra.simcardactivator.controller;

import au.com.telstra.simcardactivator.data.SimCardRequest;
import au.com.telstra.simcardactivator.entity.SimCard;
import au.com.telstra.simcardactivator.service.SimCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sim-card")
public class Controller {

    @Autowired
    private SimCardService service;

    @PostMapping("/activation")
    public ResponseEntity<String> addSimCardActivationStatus(
            @RequestBody SimCardRequest simCardRequest) {
        return new ResponseEntity<>(service.sendSimCardDetails(simCardRequest), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SimCard> getSimCardActivationStatus(@RequestParam(name = "id") Long id) {
        return new ResponseEntity<>(service.getSimCardDetails(id), HttpStatus.OK);
    }
}
