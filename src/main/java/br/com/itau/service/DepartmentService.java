package br.com.itau.service;

import br.com.itau.exception.BusinessException;
import br.com.itau.model.DepartmentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface DepartmentService {

    Page<DepartmentEntity> find(final DepartmentEntity department, final Pageable pageable);

    Optional<DepartmentEntity> get(final Long id);

    void create(final DepartmentEntity department);

    void update(final DepartmentEntity department) throws BusinessException;

    void delete(final Long id) throws BusinessException;

}
