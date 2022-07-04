package com.application.types.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.application.types.model.Type;
import com.application.types.repository.TypeRepository;

@ExtendWith(MockitoExtension.class)
public class TypeServiceTest {

	TypeService typeService;

	@Mock
	private TypeRepository typeRepository;

	@BeforeEach
	void setUp() {
		typeService = new TypeService(typeRepository);
	}

	@Test
	@DisplayName("JUnit test for getAllTypes when there is list of type return by repo")
	public void testGetAllTypes_withRecords() {

		// given
		assertNotNull(typeService);
		assertNotNull(typeRepository);
		List<Type> typeList = new ArrayList<>();
		typeList.add(Type.builder().annotation("SUBJID_temp").operation("tag").comments(true).segmentation("both")
				.patientSegmentLengthInParagraphs(2000).patientSegmentTableRows(true).displayName("SUBJID_temp")
				.highlightColour("Red").highlightColourHexCode("#FF0D0D").defaultMaskValue("[****]")
				.classification("DI").build());
		//when
		when(typeRepository.findAll()).thenReturn(typeList);

		// then
		List<Type> fetchedTypes = typeService.getAllTypes();
		assertThat(fetchedTypes.size()).isEqualTo(1);

	}

	@Test
	@DisplayName("JUnit test for getAllTypes when there is no type return by repo")
	void testGetAllTypes_withEmptyRecords() {
		// given
		assertNotNull(typeService);
		assertNotNull(typeRepository);
		List<Type> typeList = new ArrayList<>();
		// when
		when(typeRepository.findAll()).thenReturn(typeList);

		// then
		List<Type> fetchedTypes = typeService.getAllTypes();
		assertThat(fetchedTypes.size()).isEqualTo(0);

	}
}
