package nykon.DAO.impl.JPA;

import com.zaxxer.hikari.HikariDataSource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nykon.DAO.*;
import nykon.DAO.impl.ConnectionPool;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class JpaDaoFactory extends DaoFactory {

    EntityManager entityManager;
    @Override
    public UsrDAO createUsrDao() {
        return new JpaUsrDAO(entityManager);
    }

    @Override
    public RoleDAO createRoleDao() {
        return new JpaRoleDAO(entityManager);
    }

    @Override
    public RoomDAO createRoomDao() {
        return new JpaRoomDAO(entityManager);
    }

    @Override
    public OrderDAO createOrderDao() {
        return new JpaOrderDAO(entityManager);
    }
}
