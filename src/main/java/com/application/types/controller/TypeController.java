package com.application.types.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.application.types.dto.TypeDto;
import com.application.types.service.impl.TypeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * RestController to handle all REST APIs client request such as GET, POST,
 * Delete, PUT requests Currently support get operation
 * 
 * @author Yashesh Patel
 */
@RestController
@Slf4j
@RequestMapping(path = { "/api/v1" }, produces = MediaType.APPLICATION_JSON_VALUE)
public class TypeController {

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
	public List<TypeDto> getTypes() {
		log.info("TypeController.getTypes() ==> fetching types");
		try {
			return typeService.getAllTypes().stream().map(type -> modelMapper.map(type, TypeDto.class)).collect(Collectors.toList());
		} catch (Exception e) {
			log.error("TypeController.getTypes() ==>Error while fetching types");
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while fetching types. Please try again!");
			
		}
	}

}
