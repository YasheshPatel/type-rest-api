package com.application.types.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.types.model.Type;
import com.application.types.repository.TypeRepository;
import com.application.types.service.ITypeService;

import lombok.extern.slf4j.Slf4j;

/**
 * service for business logic 
 * 
 * @author Yashesh Patel
 */

@Service
@Slf4j
public class TypeService implements ITypeService {

	private TypeRepository typeRepository;

	@Autowired
	TypeService(TypeRepository typeRepository) {
		this.typeRepository = typeRepository;
	}

	@Override
	public List<Type> getAllTypes() {
		log.info("TypeService.getAllTypes() ==> Fetching types using repo");
		return typeRepository.findAll();
	}

}
