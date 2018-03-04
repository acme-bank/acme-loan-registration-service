package com.acme.bank.loan.web.resource;

import com.acme.bank.loan.domain.model.RegisterLoanModel;
import com.acme.bank.loan.service.service.RegisterLoanService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@Api(value = "Register Loan API", tags = {"loans", "register"})
@RequestMapping(path = "${acme.loan.registration.api.url:/api/1.0/loans}")
@RestController
public class LoanRegistrationResource {

    private final RegisterLoanService service;

    @Autowired
    public LoanRegistrationResource(final RegisterLoanService service) {
        this.service = service;
    }

    @ApiOperation(value = "Find registered loans by person ID", httpMethod = "GET", response = RegisterLoanModel.class, responseContainer = "List")
    @GetMapping(params = "personId")
    public ResponseEntity<List<RegisterLoanModel>> find(@RequestParam("personId") UUID personId) {
        return ResponseEntity.ok(service.find(personId));
    }

    @ApiOperation(value = "Find all registered loans", httpMethod = "GET", response = RegisterLoanModel.class, responseContainer = "List")
    @GetMapping
    public ResponseEntity<List<RegisterLoanModel>> find() {
        return ResponseEntity.ok(service.find());
    }

    @ApiOperation(value = "Get registered loan by event ID", httpMethod = "GET", response = RegisterLoanModel.class)
    @ApiParam(name = "eventId", value = "Unique identifier for loan", required = true)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Event found"),
            @ApiResponse(code = 204, message = "No event found")
    })
    @GetMapping(path = "/{eventId}")
    public ResponseEntity<RegisterLoanModel> get(@PathVariable("eventId") UUID eventId) {
        RegisterLoanModel model = service.get(eventId);
        return model != null ? ResponseEntity.ok(model) : ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Creates registered loan", httpMethod = "POST")
    @PostMapping
    public ResponseEntity<Void> post(@RequestBody RegisterLoanModel model) {
        UUID eventId = service.save(model);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{eventId}")
                .buildAndExpand(eventId.toString())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
