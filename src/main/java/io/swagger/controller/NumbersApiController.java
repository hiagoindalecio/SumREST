package io.swagger.controller;

import io.swagger.api.NumbersApi;
import io.swagger.model.NumberEnum;
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
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import javax.validation.Valid;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-04-08T21:52:52.495Z[GMT]")
@RestController
public class NumbersApiController implements NumbersApi {

    private static final Logger log = LoggerFactory.getLogger(NumbersApiController.class);
    
    @Autowired
    NumberService numberService;
    
    @GetMapping("/sums")
    public ResponseEntity<List<Sum>> getSums(@RequestParam(value = "min", required = false) Double min, @RequestParam(value = "max", required = false) Double max) throws Exception {
    	log.info("NumbersApiController.getSums() - Entering...");
    	List<Sum> list = numberService.getSums(min, max);
    	log.info("NumbersApiController.getSums() - Completed");
    	return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @PostMapping("/sums")
    public ResponseEntity<SumResponse> insertSum(@Parameter(in = ParameterIn.DEFAULT, description = "The two numbers json object", required=true, schema=@Schema()) @Valid @RequestBody Numbers body) throws Exception {
    	SumResponse resp = null;
    	HttpStatus status = HttpStatus.CREATED;
    	log.info("NumbersApiController.insert() - Entering...");
    	try {
    		resp = numberService.InsertSum(body);
    	} catch (Exception e) {
    		System.out.println("CAIU na exception: " + e.getCause().toString());
    		if (e instanceof NumberFormatException) {
    			System.out.println("NumberFormat exception");
				 throw new Exception(NumberEnum.BAD_REQUEST.toString());
			} else if (e instanceof NullPointerException) {
				System.out.println("NullPointerException");
				throw new Exception(NumberEnum.NULL_POINTER.toString());
			}
    		e.printStackTrace();
			System.out.println("NumbersApiController.InsertSum() - Exception: " + e.getMessage());
    	}
    	
    	log.info("NumbersApiController.insert() - Completed");
    	return new ResponseEntity<>(resp,status);
    }
    

}
