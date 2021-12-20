package io.springboot.demo.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BaseService <T, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor <T> {

}