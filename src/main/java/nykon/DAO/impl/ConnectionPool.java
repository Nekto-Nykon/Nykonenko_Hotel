package nykon.DAO.impl;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.SQLException;
@Getter
@Slf4j
public class ConnectionPool {
    private static HikariDataSource dataSource;
    private static final String JDBC_URL = "jdbc:postgresql://localhost/PIS";
    private static final String JDBC_USER = "postgres";
    private static final String JDBC_PASSWORD = "root";
    private static final String JDBC_DRIVER_CLASS = "org.postgresql.Driver";

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(JDBC_URL);
        config.setUsername(JDBC_USER);
        config.setPassword(JDBC_PASSWORD);
        //config.setDriverClassName(JDBC_DRIVER_CLASS);
        dataSource = new HikariDataSource(config);
    }
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static HikariDataSource getDataSource() {
        return dataSource;
    }
}
