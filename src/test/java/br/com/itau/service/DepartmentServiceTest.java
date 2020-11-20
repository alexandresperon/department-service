package br.com.itau.service;

import br.com.itau.exception.NotValidException;
import br.com.itau.model.DepartmentEntity;
import br.com.itau.repository.DepartmentRepository;
import br.com.itau.service.impl.DepartmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class DepartmentServiceTest {

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Mock
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findByFilter_emptyDepartmentList_success() {
        when(this.departmentRepository.findAll(any(), any(Pageable.class))).thenReturn(Page.empty());
        assertEquals(Page.empty(), this.departmentService.find(new DepartmentEntity(), Pageable.unpaged()));
    }

    @Test
    void get_existentDepartment_success() {
        when(this.departmentRepository.findById(eq(1L))).thenReturn(Optional.of(new DepartmentEntity()));
        assertTrue(this.departmentService.get(1L).isPresent());
    }

    @Test
    void get_notExistentDepartment_success() {
        when(this.departmentRepository.findById(any())).thenReturn(Optional.empty());
        assertFalse(this.departmentService.get(1L).isPresent());
    }

    @Test
    void create_notExistentDepartment_success() {
        when(this.departmentRepository.save(any())).thenReturn(new DepartmentEntity());
        assertDoesNotThrow(() -> this.departmentService.create(new DepartmentEntity()));
    }

    @Test
    void update_existentDepartment_success() {
        final var department = new DepartmentEntity();
        department.setId(1L);
        when(this.departmentRepository.existsById(eq(1L))).thenReturn(true);
        when(this.departmentRepository.save(any())).thenReturn(department);
        assertDoesNotThrow(() -> this.departmentService.update(department));
    }

    @Test
    void update_notExistentDepartment_notValidException() {
        final var department = new DepartmentEntity();
        department.setId(1L);
        when(this.departmentRepository.existsById(eq(1L))).thenReturn(false);
        when(this.departmentRepository.save(any())).thenReturn(department);
        assertThrows(NotValidException.class, () -> this.departmentService.update(department));
    }

    @Test
    void delete_existentDepartment_success() {
        final var id = 1L;
        when(this.departmentRepository.existsById(eq(id))).thenReturn(true);
        doNothing().when(this.departmentRepository).deleteById(id);
        assertDoesNotThrow(() -> this.departmentService.delete(id));
    }

    @Test
    void delete_notExistentDepartment_notValidException() {
        final var id = 1L;
        when(this.departmentRepository.existsById(eq(id))).thenReturn(false);
        doNothing().when(this.departmentRepository).deleteById(id);
        assertThrows(NotValidException.class, () -> this.departmentService.delete(id));
    }

}
