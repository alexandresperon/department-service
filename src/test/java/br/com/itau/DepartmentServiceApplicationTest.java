package br.com.itau;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.liquibase.enabled=true")
class DepartmentServiceApplicationTest {

    @Test
    void contextLoads() {
    }

}
