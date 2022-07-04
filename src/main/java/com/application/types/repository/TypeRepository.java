package com.application.types.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.application.types.model.Type;

/**
 * Repository to perform CRUD
 * @author Yashesh Patel
 */

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
}
