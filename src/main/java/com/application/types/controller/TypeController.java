package com.application.types.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.types.dto.TypeDto;
import com.application.types.service.impl.TypeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

/**
 * RestController to handle all REST APIs client request such as GET, POST,
 * Delete, PUT requests Currently support get operation
 * 
 * @author Yashesh Patel
 */
@RestController
@RequestMapping(path = { "/api/v1" }, produces = MediaType.APPLICATION_JSON_VALUE)
public class TypeController {

	private static final Logger logger = LoggerFactory.getLogger(TypeController.class);

	private final TypeService typeService;
	private final ModelMapper modelMapper;

	@Autowired
	TypeController(TypeService typeService, ModelMapper modelMapper) {
		this.typeService = typeService;
		this.modelMapper = modelMapper;
	}

	@Operation(summary = "Returns a list of types and sorted/filtered based on the query parameters")
	@ApiResponse(responseCode = "200", description = "List of Types", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = TypeDto.class))) })
	@GetMapping("/types")
	public ResponseEntity<List<TypeDto>> getTypes() {
		logger.info("TypeController.getTypes() ==> fetching types");
		try {
			return ResponseEntity
					.ok(typeService.getAllTypes().stream().map(type -> modelMapper.map(type, TypeDto.class)).toList());
		} catch (Exception e) {
			logger.error("TypeController.getTypes() ==>Error while fetching types");
			throw e;
		}
	}

}
