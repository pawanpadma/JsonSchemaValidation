package org.schema.validator;

import org.example.Main;
import org.junit.jupiter.api.Test;

public class JsonValidatorTest {

    @Test
    public void validateEmployeeJsonTest() {
        String schemaPath = "src/main/java/org/example/schema/employee.json";
        String jsonPath = "src/test/resources/employee.json";
        Main main = new Main();
        try {
            main.validateJson(schemaPath, jsonPath);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }





}
