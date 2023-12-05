package com.uni.labat23;

import com.uni.labat23.entity.Calculations;
import com.uni.labat23.entity.models.Numbers;
import com.uni.labat23.entity.models.NumbersDTO;
import com.uni.labat23.repository.CalculationsRepository;
import com.uni.labat23.service.CalculationsService;
import com.uni.labat23.service.OperationsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@ContextConfiguration(initializers = {Labat23ApplicationTests.Initializer.class})
@AutoConfigureMockMvc
@Testcontainers
public class Labat23ApplicationTests {
    @Container
    public static PostgreSQLContainer<?> postgresSQLContainer = new PostgreSQLContainer<>("postgres:15.1")
            .withDatabaseName("lab2")
            .withUsername("postgres")
            .withPassword("user")
            .withInitScript("database/V1__create.sql");
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    CalculationsRepository calculationsRepository;
    @Autowired
    CalculationsService calculationsService;

    @Test
    @Transactional
    public void calculatorByParameters() throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = df.parse("2023-11-01");
        Date endDate = df.parse("2023-11-07");
        List<Calculations> calculations = calculationsRepository.findByParameters(2, 2, "ADDITION", startDate, endDate);
        assertEquals(1, calculations.size());
    }

    @Test
    @Transactional
    public void calculatorWithoutOperation() throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = df.parse("2023-11-01");
        Date endDate = df.parse("2023-11-07");
        List<Calculations> calculations = calculationsRepository.findByNumberSystemOneAndNumberSystemTwoAndDateCreatedGreaterThanAndDateCreatedLessThan(2,2, startDate, endDate);
        assertEquals(3, calculations.size());
    }

    @Test
    @Transactional
    public void calculatorFindByDates() throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = df.parse("2023-11-01");
        Date endDate = df.parse("2023-11-07");
        List<Calculations> calculations
                = calculationsRepository.findByDateCreatedGreaterThanAndDateCreatedLessThan(startDate, endDate);
        assertEquals(4, calculations.size());
    }

    @Test
    @Transactional
    public void calculatorFindByDatesAndDivisionOperation() throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = df.parse("2023-11-01");
        Date endDate = df.parse("2023-11-07");
        List<Calculations> calculations = calculationsRepository.findByParameters(2, 2, "DIVISION", startDate, endDate);
        List<Calculations> calculations1 = calculationsRepository.findAll();
        for (Calculations calculation : calculations1) {
            System.out.println(calculation);
        }
        assertEquals(1, calculations.size());
    }

    @Test
    @Transactional
    public void createPlus() {
        Numbers numbers = new Numbers();
        numbers.setNum1(0b101);
        numbers.setNum2(0b111);
        numbers.setSystem(2);
        var res = OperationsService.calculate(numbers, '+');
        assertEquals(12, res);
    }

    @Test
    @Transactional
    public void createDivision() {
        Numbers numbers = new Numbers();
        numbers.setNum1(0x150);
        numbers.setNum2(0b11);
        numbers.setSystem(2);
        var res = OperationsService.calculate(numbers, '/');
        assertEquals(112, res);
    }

    @Test
    @Transactional
    public void createMult() {
        Numbers numbers = new Numbers();
        numbers.setNum1(0x152);
        numbers.setNum2(0b11);
        numbers.setSystem(2);
        var res = OperationsService.calculate(numbers, '*');
        assertEquals(1014, res);
    }

    @Test
    @Transactional
    public void createSubtract() {
        Numbers numbers = new Numbers();
        numbers.setNum1(0x152);
        numbers.setNum2(0b11);
        numbers.setSystem(2);
        var res = OperationsService.calculate(numbers, '-');
        assertEquals(335, res);
    }

    @Test
    public void testSub() throws Exception {
        Numbers numbers = new Numbers();
        numbers.setNum1(0x152);
        numbers.setNum2(0b11);
        numbers.setSystem(2);
        var res = OperationsService.calculate(numbers, '-');
        var response = this.mockMvc.perform(get("/calculations/subtraction")
                        .param("num1", String.valueOf(numbers.getNum1()))
                        .param("num2", String.valueOf(numbers.getNum2()))
                        .param("sys", String.valueOf(numbers.getSystem())))
                .andReturn().getResponse();
        assertEquals(String.valueOf(res), response.getContentAsString());
        assertEquals(200, response.getStatus());
    }

    @Test
    public void testAdd() throws Exception {
        Numbers numbers = new Numbers();
        numbers.setNum1(0x152);
        numbers.setNum2(0b11);
        numbers.setSystem(2);
        var res = OperationsService.calculate(numbers, '+');
        var response = this.mockMvc.perform(get("/calculations/addition")
                        .param("num1", String.valueOf(numbers.getNum1()))
                        .param("num2", String.valueOf(numbers.getNum2()))
                        .param("system", String.valueOf(numbers.getSystem())))
                .andReturn().getResponse();
        assertEquals(String.valueOf(res), response.getContentAsString());
        assertEquals(200, response.getStatus());
    }

    @Test
    public void testMult() throws Exception {
        Numbers numbers = new Numbers();
        numbers.setNum1(0x152);
        numbers.setNum2(0b11);
        numbers.setSystem(2);
        var res = OperationsService.calculate(numbers, '*');
        var response = this.mockMvc.perform(get("/calculations/multiplication")
                        .param("num1", String.valueOf(numbers.getNum1()))
                        .param("num2", String.valueOf(numbers.getNum2()))
                        .param("system", String.valueOf(numbers.getSystem())))
                .andReturn().getResponse();
        assertEquals(String.valueOf(res), response.getContentAsString());
        assertEquals(200, response.getStatus());
    }

    @Test
    public void testDiv() throws Exception {
        Numbers numbers = new Numbers();
        numbers.setNum1(0x152);
        numbers.setNum2(0b11);
        numbers.setSystem(2);
        var res = OperationsService.calculate(numbers, '/');
        var response = this.mockMvc.perform(get("/calculations/division")
                        .param("num1", String.valueOf(numbers.getNum1()))
                        .param("num2", String.valueOf(numbers.getNum2()))
                        .param("system", String.valueOf(numbers.getSystem())))
                .andReturn().getResponse();
        assertEquals(String.valueOf(res), response.getContentAsString());
        assertEquals(200, response.getStatus());
    }

    @Test
    public void findByParameters() throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = df.parse("2023-11-01");
        Date endDate = df.parse("2023-11-07");
        NumbersDTO numbersDTO = new NumbersDTO();
        numbersDTO.setOperationName("ADDITION");
        numbersDTO.setStartDate(startDate);
        numbersDTO.setEndDate(endDate);
        var response = this.mockMvc.perform(get("/calculations/findByParameters?endDate=2023-11-07&numberSystemOne=2&numberSystemTwo=2&startDate=2023-11-01")
                .param("operationName", numbersDTO.getOperationName())).andReturn().getResponse();
        List<Calculations> calculations = calculationsRepository.findByParameters(2, 2, numbersDTO.getOperationName(), startDate, endDate);
        assertEquals(calculations.size(), response.getHeaders("Content-Type").size());
        assertEquals(200, response.getStatus());
    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgresSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgresSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgresSQLContainer.getPassword()

            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }
}
