package br.com.itau.service.impl;

import br.com.itau.exception.BusinessException;
import br.com.itau.exception.NotValidException;
import br.com.itau.model.DepartmentEntity;
import br.com.itau.repository.DepartmentRepository;
import br.com.itau.service.DepartmentService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private static final ExampleMatcher EXAMPLE_MATCHER = ExampleMatcher.matchingAll().withIgnoreCase()
            .withIgnoreNullValues().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Page<DepartmentEntity> find(final DepartmentEntity department, final Pageable pageable) {
        return this.departmentRepository.findAll(Example.of(department, EXAMPLE_MATCHER), pageable);
    }

    @Override
    public Optional<DepartmentEntity> get(Long id) {
        return this.departmentRepository.findById(id);
    }

    @Override
    public void create(DepartmentEntity department) {
        this.departmentRepository.save(department);
    }

    @Override
    public void update(DepartmentEntity department) throws BusinessException {
        this.throwIfNotExists(department.getId());
        this.departmentRepository.save(department);
    }

    @Override
    public void delete(Long id) throws BusinessException {
        this.throwIfNotExists(id);
        this.departmentRepository.deleteById(id);
    }

    private void throwIfNotExists(Long id) throws NotValidException {
        if (!this.departmentRepository.existsById(id))
            throw new NotValidException("department.not.exists");
    }
}
