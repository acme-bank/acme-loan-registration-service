package com.acme.bank.loan.web.resource;

import com.acme.bank.loan.domain.model.RegisterLoanModel;
import com.acme.bank.loan.service.service.RegisterLoanService;
import com.acme.bank.loan.web.helper.WebHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

    @ApiOperation(value = "Find registered loans by personal ID", httpMethod = "GET", response = RegisterLoanModel.class, responseContainer = "List")
    @GetMapping(params = "personalId")
    public ResponseEntity<List<RegisterLoanModel>> find(@RequestParam("personalId") String personalId) {
        return ResponseEntity.ok(service.find(personalId));
    }

    @ApiOperation(value = "Find all registered loans", httpMethod = "GET", response = RegisterLoanModel.class, responseContainer = "List")
    @GetMapping
    public ResponseEntity<List<RegisterLoanModel>> find() {
        return ResponseEntity.ok(service.find());
    }

    @ApiOperation(value = "Get registered loan by UUID", httpMethod = "GET", response = RegisterLoanModel.class)
    @ApiParam(name = "uuid", value = "Unique identifier for loan", required = true)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Event found"),
            @ApiResponse(code = 204, message = "No event found")
    })
    @GetMapping(path = "/{uuid}")
    public ResponseEntity<RegisterLoanModel> get(@PathVariable("uuid") String uuid) {
        RegisterLoanModel model = service.get(WebHelper.parseUUID(uuid));
        return model != null ? ResponseEntity.ok(model) : ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Creates registered loan", httpMethod = "POST")
    @PostMapping
    public ResponseEntity<Void> post(@RequestBody RegisterLoanModel model) {
        UUID uuid = service.save(model);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{uuid}")
                .buildAndExpand(uuid.toString())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
