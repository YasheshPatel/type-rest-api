package com.application.types.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.server.ResponseStatusException;

import com.application.types.TypesApplication;
import com.application.types.dto.TypeDto;
import com.application.types.model.Type;
import com.application.types.service.impl.TypeService;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = TypesApplication.class)
class TypeControllerTest {

	@Mock
	TypeService typeService;

	@Mock
	ModelMapper modelMapper;

	@Autowired
	MockMvc mockMvc;

	TypeController typeController;

	@BeforeEach
	void setUp() {
		typeController = new TypeController(typeService, modelMapper);
	}

	@Test
	@DisplayName("JUnit test for getTypes when there is list of type in database")
	void testGetTypesTest_withRecordList() {
		// give
		assertNotNull(typeService);
		assertNotNull(modelMapper);

		List<Type> types = new ArrayList<>();
		types.add(Type.builder().annotation("SUBJID_temp").operation("tag").comments(true).segmentation("both")
				.patientSegmentLengthInParagraphs(2000).patientSegmentTableRows(true).displayName("SUBJID_temp")
				.highlightColour("Red").highlightColourHexCode("#FF0D0D").defaultMaskValue("[****]")
				.classification("DI").build());
		types.add(Type.builder().annotation("WEIGHT_KG").operation("tag").comments(true).segmentation("both")
				.patientSegmentLengthInParagraphs(2000).patientSegmentTableRows(true).displayName("WEIGHT_KG")
				.highlightColour("Green").highlightColourHexCode("#8AC926").defaultMaskValue("[**]")
				.classification("QI").build());
		types.add(Type.builder().annotation("WEIGHT_LBS").operation("tag").comments(true).segmentation("both")
				.patientSegmentLengthInParagraphs(2000).patientSegmentTableRows(true).displayName("WEIGHT_LBS")
				.highlightColour("Red").highlightColourHexCode("#FF0D0D").defaultMaskValue("[**]").classification("DI")
				.build());

		// when
		when(typeService.getAllTypes()).thenReturn(types);

		// then
		List<TypeDto> types2 = typeController.getTypes();

		// equal the response list size with actual list size
		assertEquals(types.size(), types2.size());

	}

	@Test
	@DisplayName("JUnit test for getTypes when there is no type in database")
	void testGetTypesTest_withEmptyRecordList() {
		// give
		assertNotNull(typeService);
		assertNotNull(modelMapper);
		// when
		when(typeService.getAllTypes()).thenReturn(new ArrayList<Type>());

		// then
		List<TypeDto> types = typeController.getTypes();
		assertEquals(0, types.size());

	}

	@Test
	@DisplayName("JUnit test for getTypes when null pointer is thrown rear case")
	void testGetTypesTest_whenServiceThrowNullPointer() {
		// give
		assertNotNull(typeService);
		assertNotNull(modelMapper);
		// when
		when(typeService.getAllTypes()).thenThrow(ResponseStatusException.class);

		// then

		assertThrows(ResponseStatusException.class, () -> {
			@SuppressWarnings("unused")
			List<TypeDto> types = typeController.getTypes();
		});

	}

	@Test
	@DisplayName("JUnit test for getTypes using wrong method type")
	void testGetTypesTest_withNotSupportedMethodTypePostMockMvc() throws Exception {
		// give
		assertNotNull(typeService);
		assertNotNull(mockMvc);
		// when
		Mockito.lenient().when(typeService.getAllTypes()).thenReturn(new ArrayList<Type>());

		// then
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/types")).andExpect(status().isMethodNotAllowed());
	}

	@Test
	@DisplayName("JUnit test for wrong path/endpoint type")
	void testGetTypesTest_withNotSupportedEndpointMockMvc() throws Exception {
		// give
		assertNotNull(typeService);
		assertNotNull(mockMvc);
		// when
		Mockito.lenient().when(typeService.getAllTypes()).thenReturn(new ArrayList<Type>());

		// then
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/typ")).andExpect(status().isNotFound());
	}

}
