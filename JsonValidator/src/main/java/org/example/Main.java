package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;

import java.io.File;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws Exception {

        new Main().validateJson("", "");
    }

    public void validateJson(String jsonSchemaPath, String jsonFilePath) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonSchema = mapper.readTree(new File(jsonSchemaPath));
        JsonNode jsonData = mapper.readTree(new File(jsonFilePath));

        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7);
        JsonSchema schema = factory.getSchema(jsonSchema);

        Set<ValidationMessage> errors = schema.validate(jsonData);

        if (errors.size() > 0) {
            String messages = errors.stream()
                    .map(ValidationMessage::getMessage)
                    .collect(Collectors.joining("\n"));

            throw new RuntimeException("Schema validation errors\n" + messages);
        }
    }
}
