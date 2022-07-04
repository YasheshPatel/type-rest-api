package com.application.types.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.types.model.Type;
import com.application.types.repository.TypeRepository;
import com.application.types.service.ITypeService;

/**
 * service for business logic 
 * 
 * @author Yashesh Patel
 */

@Service
public class TypeService implements ITypeService {
	private static final Logger logger = LoggerFactory.getLogger(TypeService.class);

	private TypeRepository typeRepository;

	@Autowired
	TypeService(TypeRepository typeRepository) {
		this.typeRepository = typeRepository;
	}

	@Override
	public List<Type> getAllTypes() {
		logger.info("TypeService.getAllTypes() ==> Fetching types using repo");
		return typeRepository.findAll();
	}

}
