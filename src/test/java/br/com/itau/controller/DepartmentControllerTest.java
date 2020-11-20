package br.com.itau.controller;

import br.com.itau.dto.AddressDTO;
import br.com.itau.dto.DepartmentDTO;
import br.com.itau.enumerator.Boards;
import br.com.itau.enumerator.States;
import br.com.itau.model.DepartmentEntity;
import br.com.itau.service.DepartmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DepartmentControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    @MockBean
    private MessageSource messageSource;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        when(this.messageSource.getMessage(any(), any(), any())).thenReturn("");
    }

    @Test
    @WithMockUser
    void findAll_emptyDepartmentList_success() throws Exception {
        when(this.departmentService.find(any(), any())).thenReturn(Page.empty());
        this.mockMvc.perform(get("/departments")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void findAll_uncaughtException_internalServerError() throws Exception {
        when(this.departmentService.find(any(), any())).thenThrow(new IllegalArgumentException());
        this.mockMvc.perform(get("/departments")).andExpect(status().isInternalServerError());
    }

    @Test
    @WithMockUser
    void get_existentDepartment_success() throws Exception {
        when(this.departmentService.get(eq(1L))).thenReturn(Optional.of(new DepartmentEntity()));
        this.mockMvc.perform(get("/departments/{id}", 1)).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void get_notExistentDepartment_notFound() throws Exception {
        this.mockMvc.perform(get("/departments/{id}", 1)).andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser
    void create_newDepartmentWithAllRequiredData_success() throws Exception {
        final var department = new DepartmentDTO();
        department.setName("Name");
        department.setBoard(Boards.BUSINESS);
        final var address = new AddressDTO();
        address.setAddress("Address");
        address.setCity("City");
        address.setState(States.AC);
        department.setAddress(address);
        doNothing().when(this.departmentService).create(any());
        this.mockMvc.perform(post("/departments").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(department)))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser
    void create_newDepartmentWithoutRequiredData_badRequest() throws Exception {
        doNothing().when(this.departmentService).create(any());
        this.mockMvc.perform(post("/departments/").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(new DepartmentDTO())))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser
    void create_existentDepartment_badRequest() throws Exception {
        final var department = new DepartmentDTO();
        department.setId(1L);
        department.setName("Name");
        department.setBoard(Boards.BUSINESS);
        final var address = new AddressDTO();
        address.setAddress("Address");
        address.setCity("City");
        address.setState(States.AC);
        department.setAddress(address);
        doNothing().when(this.departmentService).create(any());
        this.mockMvc.perform(post("/departments/").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(department)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser
    void update_existentDepartment_success() throws Exception {
        final var department = new DepartmentDTO();
        department.setId(1L);
        department.setName("Name");
        department.setBoard(Boards.BUSINESS);
        final var address = new AddressDTO();
        address.setAddress("Address");
        address.setCity("City");
        address.setState(States.AC);
        department.setAddress(address);
        doNothing().when(this.departmentService).update(any());
        this.mockMvc.perform(put("/departments/{id}", 1).contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(department))).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void updateWithoutPassingId_existentDepartment_success() throws Exception {
        final var department = new DepartmentDTO();
        department.setName("Name");
        department.setBoard(Boards.BUSINESS);
        final var address = new AddressDTO();
        address.setAddress("Address");
        address.setCity("City");
        address.setState(States.AC);
        department.setAddress(address);
        doNothing().when(this.departmentService).update(any());
        this.mockMvc.perform(put("/departments/{id}", 1).contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(department))).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void update_differentIdsInRequest_badRequest() throws Exception {
        final var department = new DepartmentDTO();
        department.setId(2L);
        department.setName("Name");
        department.setBoard(Boards.BUSINESS);
        final var address = new AddressDTO();
        address.setAddress("Address");
        address.setCity("City");
        address.setState(States.AC);
        department.setAddress(address);
        doNothing().when(this.departmentService).update(any());
        this.mockMvc.perform(put("/departments/{id}", 1).contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(department))).andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser
    void delete_existentDepartment_success() throws Exception {
        doNothing().when(this.departmentService).delete(eq(1L));
        this.mockMvc.perform(delete("/departments/{id}", 1)).andExpect(status().isOk());
    }
}
