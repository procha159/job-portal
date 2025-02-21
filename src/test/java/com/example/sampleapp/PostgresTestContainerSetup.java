package com.example.sampleapp;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PostgresTestContainerSetup {

    private static final PostgreSQLContainer<?> postgresContainer =
            new PostgreSQLContainer<>("postgres:15.2")
                    .withDatabaseName("testdb")
                    .withUsername("testuser")
                    .withPassword("testpass")
                    .withInitScript("sql/schema.sql"); // Run schema on startup

    @BeforeAll
    static void startContainerAndRunScripts() throws Exception {
        postgresContainer.start();

        // Run additional SQL scripts
        runSqlScript("src/test/resources/sql/data.sql");
    }

    @DynamicPropertySource
    static void configureDataSource(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresContainer::getUsername);
        registry.add("spring.datasource.password", postgresContainer::getPassword);
        registry.add("spring.datasource.driver-class-name", () -> "org.postgresql.Driver");
    }

    private static void runSqlScript(String scriptPath) throws Exception {
        Path path = Paths.get(scriptPath);
        if (!Files.exists(path)) {
            throw new RuntimeException("SQL script file not found: " + scriptPath);
        }
        try (Connection connection = postgresContainer.createConnection("");
             Statement statement = connection.createStatement()) {
            String sql = new String(Files.readAllBytes(path));
            statement.execute(sql);
        }
    }
}