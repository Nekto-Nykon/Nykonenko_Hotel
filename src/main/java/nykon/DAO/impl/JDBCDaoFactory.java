package nykon.DAO.impl;

import com.zaxxer.hikari.HikariDataSource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import nykon.DAO.*;



import java.sql.Connection;
import java.sql.SQLException;

@Getter
@Slf4j
@AllArgsConstructor
@Setter
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
