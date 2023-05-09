package nykon.DAO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import nykon.DAO.impl.JDBC.JDBCDaoFactory;
import nykon.DAO.impl.JPA.JpaDaoFactory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;


public abstract class DaoFactory  {
    private static DaoFactory daoFactory;

    protected DaoFactory() {
    }

    public abstract UsrDAO createUsrDao();
    public abstract RoleDAO createRoleDao();
    public abstract RoomDAO createRoomDao();
    public abstract OrderDAO createOrderDao();
    public static DaoFactory getJpaInstance(EntityManager entityManager) {
        if (daoFactory == null) {

            synchronized (DaoFactory.class) {
                if (daoFactory == null) {
                    daoFactory = new JpaDaoFactory(entityManager);
                }
            }
        }
        return daoFactory;
    }
}
