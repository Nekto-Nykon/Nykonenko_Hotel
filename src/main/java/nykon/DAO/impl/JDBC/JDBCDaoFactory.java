package nykon.DAO.impl.JDBC;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import nykon.DAO.*;
import nykon.DAO.impl.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

@Getter
public class JDBCDaoFactory extends DaoFactory {
    private HikariDataSource dataSource;
    public JDBCDaoFactory(){
       dataSource = ConnectionPool.getDataSource();
    }
    @Override
    public UsrDAO createUsrDao() {
        return new JDBCUsrDAO(getConnection());
    }

    @Override
    public RoleDAO createRoleDao() {
        return new JDBCRoleDAO(getConnection());
    }



    @Override
    public RoomDAO createRoomDao() {
        return new JDBCRoomDAO(getConnection());
    }

    @Override
    public OrderDAO createOrderDao() {
        return new JDBCOrderDAO(getConnection());
    }
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException exception) {
            System.out.println("123");
            throw new RuntimeException(exception);
        }
    }
}
