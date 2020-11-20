package br.com.itau.controller;

import br.com.itau.controller.request.Pagination;
import br.com.itau.controller.response.Page;
import br.com.itau.controller.response.Response;
import br.com.itau.dto.DepartmentDTO;
import br.com.itau.exception.BusinessException;
import br.com.itau.exception.NotFoundException;
import br.com.itau.exception.NotValidException;
import br.com.itau.model.DepartmentEntity;
import br.com.itau.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final ModelMapper modelMapper;
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.departmentService = departmentService;
    }

    @GetMapping
    public Response<Page<DepartmentDTO>> find(final DepartmentDTO department, final Pagination pagination) {
        return new Response<>(new Page<>(this.departmentService.find(this.mapToEntity(department), pagination.request())
                .map(this::mapToDTO)));
    }

    @GetMapping("/{id}")
    public Response<DepartmentDTO> get(@PathVariable final Long id) throws BusinessException {
        return new Response<>(this.departmentService.get(id).map(this::mapToDTO)
                .orElseThrow(() -> new NotFoundException("department.not.exists")));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response<Void> create(@Valid @RequestBody final DepartmentDTO department) throws BusinessException {
        if (department.getId() != null)
            throw new NotValidException("department.already.exists");
        this.departmentService.create(this.mapToEntity(department));
        return new Response<>();
    }

    @PutMapping("/{id}")
    public Response<Void> update(@PathVariable final Long id, @Valid @RequestBody final DepartmentDTO department) throws BusinessException {
        if (department.getId() == null)
            department.setId(id);
        if (!department.getId().equals(id))
            throw new NotValidException("invalid.update.id");
        this.departmentService.update(this.mapToEntity(department));
        return new Response<>();
    }

    @DeleteMapping("/{id}")
    public Response<Void> delete(@PathVariable final Long id) throws BusinessException {
        this.departmentService.delete(id);
        return new Response<>();
    }

    private DepartmentDTO mapToDTO(DepartmentEntity department) {
        return this.modelMapper.map(department, DepartmentDTO.class);
    }

    private DepartmentEntity mapToEntity(DepartmentDTO department) {
        return this.modelMapper.map(department, DepartmentEntity.class);
    }
}
