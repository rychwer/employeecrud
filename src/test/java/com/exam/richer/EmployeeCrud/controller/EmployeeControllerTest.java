package com.exam.richer.EmployeeCrud.controller;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.exam.richer.EmployeeCrud.EmployeeCrudApplication;
import com.exam.richer.EmployeeCrud.dto.EmployeeDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = EmployeeCrudApplication.class)
public class EmployeeControllerTest {

    @Autowired
    private EmployeeController employeeController;

    private MockMvc mockMvc;

    @Before
    public void before() {
        FixtureFactoryLoader.loadTemplates("com.exam.richer.EmployeeCrud.template");
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    public void validaCriacaoFuncionario() throws Exception {

        final EmployeeDTO employeeDTO = Fixture.from(EmployeeDTO.class).gimme("valida");

        ResultActions response = mockMvc.perform(post("/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createJson(employeeDTO)));

        response.andExpect(status().isCreated());
    }

    @Test
    public void validaAlteracaoFuncionario() throws Exception {

        final EmployeeDTO employeeDTO = Fixture.from(EmployeeDTO.class).gimme("valida");
        employeeDTO.setEmail("teste2@gmail");

        mockMvc.perform(post("/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createJson(employeeDTO)));

        ResultActions response = mockMvc.perform(put("/employee/{email}", "teste2@gmail")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createJson(employeeDTO)));

        response.andExpect(status().isOk());
    }

    @Test
    public void validaDelecaoFuncionario() throws Exception {

        final EmployeeDTO employeeDTO = Fixture.from(EmployeeDTO.class).gimme("valida");
        employeeDTO.setEmail("teste@gmail");

        mockMvc.perform(post("/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createJson(employeeDTO)));

        ResultActions response = mockMvc.perform(delete("/employee/{email}", "teste@gmail")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createJson(employeeDTO)));

        response.andExpect(status().isNoContent());
    }

    @Test
    public void validaAlteracaoCepFuncionario() throws Exception {

        final EmployeeDTO employeeDTO = Fixture.from(EmployeeDTO.class).gimme("valida");
        employeeDTO.setEmail("teste3@gmail");

        mockMvc.perform(post("/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createJson(employeeDTO)));

        ResultActions response = mockMvc.perform(patch("/employee/{email}", "teste3@gmail")
                .contentType(MediaType.APPLICATION_JSON)
                .param("cep", "01516100")
                .content(createJson(employeeDTO)));

        response.andExpect(status().isOk());
    }

    @Test
    public void validaRecuperaFuncionario() throws Exception {

        final EmployeeDTO employeeDTO = Fixture.from(EmployeeDTO.class).gimme("valida");

        ResultActions response = mockMvc.perform(get("/employee")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk());
    }

    @Test
    public void validaValoresRequeridos() throws Exception {

        final EmployeeDTO employeeDTO = Fixture.from(EmployeeDTO.class).gimme("invalida");

        ResultActions response = mockMvc.perform(post("/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createJson(employeeDTO)));

        response.andExpect(status().isBadRequest());
    }


    private String createJson(Object anObject) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(anObject);
    }

}
