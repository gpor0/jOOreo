package com.github.gpor0.jooreo.test.support;

import com.github.gpor0.jooreo.SingleEntityTest;
import com.github.gpor0.jooreo.test.support.tx.JtaEnvironment;
import liquibase.CatalogAndSchema;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.FileSystemResourceAccessor;
import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldSetup;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.RegisterExtension;

import jakarta.enterprise.inject.spi.CDI;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

import static com.github.gpor0.jooreo.test.support.tx.TransactionalConnectionProvider.DS_URL;

public abstract class JooreoTest {

    private static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(DS_URL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RegisterExtension
    final JtaEnvironment jta = new JtaEnvironment();
    @WeldSetup
    public WeldInitiator weld = WeldInitiator.from(WeldInitiator.createWeld().enableDiscovery()).build();

    @BeforeAll
    private static void beforeAll() throws LiquibaseException {
        Database database = DatabaseFactory.getInstance()
                .findCorrectDatabaseImplementation(new JdbcConnection(connection));
        File path = new File(SingleEntityTest.class.getClassLoader().getResource("").getFile());
        Liquibase liquibase = new Liquibase("database.xml", new FileSystemResourceAccessor(path),
                database);
        liquibase.update("");
    }

    @AfterAll
    private static void afterAll() throws LiquibaseException {
        Database database = DatabaseFactory.getInstance()
                .findCorrectDatabaseImplementation(new JdbcConnection(connection));
        database.dropDatabaseObjects(CatalogAndSchema.DEFAULT);
    }

    @BeforeEach
    private void beforeEach() throws Exception {
    }

    @AfterEach
    private void afterEach() throws Exception {
        CDI.current().select(Connection.class).get().rollback();
    }

}
