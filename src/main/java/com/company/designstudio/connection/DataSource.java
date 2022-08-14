package com.company.designstudio.connection;

import lombok.extern.log4j.Log4j2;

import java.io.Closeable;
import java.sql.Connection;

import com.company.designstudio.ConfigurationManager;

@Log4j2
public class DataSource implements Closeable {
    public final static DataSource INSTANCE = new DataSource();
    private ConnectionPool connectionPool;
    private final String url;
    private final String password;
    private final String user;
    private final String driver;

    private DataSource() {
        url = ConfigurationManager.INSTANCE.getProperty("db.url");
        password = ConfigurationManager.INSTANCE.getProperty("db.password");
        user = ConfigurationManager.INSTANCE.getProperty("db.user");
        driver = ConfigurationManager.INSTANCE.getProperty("db.driver");
    }

    public Connection getConnection() {
        if (connectionPool == null) {
            connectionPool = new ConnectionPool(driver, url, user, password);
            log.info("Connection pool initialized");
        }
        return connectionPool.getConnection();
    }

    ConnectionPool getConnectionPool() {
        return connectionPool;
    }

    @Override
    public void close() {
        if (connectionPool != null) {
            connectionPool.destroyPool();
            connectionPool = null;
            log.info("Connection pool destroyed");
        }

    }
}