package com.uni.labat23;

import org.testcontainers.containers.PostgreSQLContainer;

public class PostgreTestContainer extends PostgreSQLContainer<PostgreTestContainer>{

    public static final String IMAGE_VERSION = "postgres:15.1";
    public static final String DATABASE_NAME = "test";
    public static PostgreSQLContainer container;

    public PostgreTestContainer() {
        super(IMAGE_VERSION);
    }

    public static PostgreSQLContainer getInstance() {
        if (container == null) {
            container = new PostgreTestContainer().withDatabaseName(DATABASE_NAME);
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("//localhost:5432/postgres", container.getJdbcUrl());
        System.setProperty("postgres", container.getUsername());
        System.setProperty("user", container.getPassword());
    }

    @Override
    public void stop() {
    }
}
