package com.step.converter.application.controller;

import com.step.converter.domain.dto.ErrorResponse;
import com.step.converter.domain.service.ConvertService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;
import static org.springframework.http.ResponseEntity.status;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@RestController
@Api( value = "converter", description = "XML Converter" )
public class ConverterController {

	@Autowired
	private ConvertService convertService;

	@RequestMapping( value = "/v1/converter/stepxml", method = GET, produces = "application/json" )
	@ResponseBody
	@ApiOperation( value = "Return the STEPXml converted in JSON", response = List.class )
	public ResponseEntity stepxml() {

		try {

			JSONObject json = convertService.convertXmlToJson();

			return ok( json.toString() );

		} catch ( Exception e ) {

			log.error( "", e );

			ErrorResponse error = new ErrorResponse();

			error.setCode( 0L );
			error.setMessage( e.getMessage() );

			return status( INTERNAL_SERVER_ERROR ).body( error );
		}
	}
}