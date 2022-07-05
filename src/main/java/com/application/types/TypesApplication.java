package com.application.types;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.application.types.model.Type;
import com.application.types.repository.TypeRepository;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Yashesh Patel
 */

@SpringBootApplication
@Slf4j
@OpenAPIDefinition(info = @Info(title="Type Rest API", version ="${application-version}", description="${application-description}"))
public class TypesApplication implements CommandLineRunner {

	private final TypeRepository typeRepository;

	@Autowired
	public TypesApplication(TypeRepository typeRepository) {
		this.typeRepository = typeRepository;
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(TypesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("TypesApplication.run(String... args) ==> Data Addition");
		loadTypes();
	}

	
	// save few records to database for get operation	
	private void loadTypes() {
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

		if (typeRepository.count() == 0) {
			typeRepository.saveAll(types);
			log.info("TypesApplication.loadTypes() ==> Default Types Loaded");
		}
	}

}
