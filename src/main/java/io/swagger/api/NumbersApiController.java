package io.swagger.api;

import io.swagger.model.Numbers;
import io.swagger.model.SumResponse;
import io.swagger.model.Sums;
import io.swagger.service.NumberService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-04-08T21:52:52.495Z[GMT]")
@RestController
public class NumbersApiController implements NumbersApi {

    private static final Logger log = LoggerFactory.getLogger(NumbersApiController.class);
    
    @Autowired
    NumberService numberService;
    
    public ResponseEntity<Sums> getSums() {
        return new ResponseEntity<>(numberService.getSums(),HttpStatus.OK);
    }

    public ResponseEntity<SumResponse> insertSum(@Parameter(in = ParameterIn.DEFAULT, description = "The two numbers json object", required=true, schema=@Schema()) @Valid @RequestBody Numbers body) {
        return new ResponseEntity<>(numberService.InsertSum(body),HttpStatus.OK);
    }

}
