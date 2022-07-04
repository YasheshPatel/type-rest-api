package com.application.types;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.application.types.controller.TypeController;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TypesApplicationTests {

	@Autowired
	private TypeController typeController;
	
	@Test
	void contextLoads() {
		//Testing if application load correctly
		assertThat(typeController).isNotNull();
	}

}
