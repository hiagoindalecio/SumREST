package io.swagger.controller;

import io.swagger.api.NumbersApi;
import io.swagger.model.Numbers;
import io.swagger.model.Sum;
import io.swagger.model.SumResponse;
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

import java.util.List;

import javax.validation.Valid;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-04-08T21:52:52.495Z[GMT]")
@RestController
public class NumbersApiController implements NumbersApi {

    private static final Logger log = LoggerFactory.getLogger(NumbersApiController.class);
    
    @Autowired
    NumberService numberService;
    
    public ResponseEntity<List<Sum>> getSums(@Parameter(in = ParameterIn.DEFAULT, description = "Min and max filters", required=true, schema=@Schema()) @Valid @RequestBody Double min, Double max) {
    	log.info("NumbersApiController.getSums() - Entering...");
    	List<Sum> list = numberService.getSums(min, max);
    	log.info("NumbersApiController.getSums() - Completed");
    	return new ResponseEntity<>(list,HttpStatus.OK);
    }

    public ResponseEntity<SumResponse> insertSum(@Parameter(in = ParameterIn.DEFAULT, description = "The two numbers json object", required=true, schema=@Schema()) @Valid @RequestBody Numbers body) {
    	log.info("NumbersApiController.insert() - Entering...");
    	SumResponse resp = numberService.InsertSum(body);
    	log.info("NumbersApiController.insert() - Completed");
    	return new ResponseEntity<>(resp,HttpStatus.OK);
    }

}
