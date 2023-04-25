package nykon.DAO;

import nykon.DAO.impl.JDBCDaoFactory;

public abstract class DaoFactory {
    private volatile static DaoFactory daoFactory;
    public abstract UsrDAO createUsrDao();
    public abstract RoleDAO createRoleDao();
    public abstract RoomDAO createRoomDao();
    public abstract OrderDAO createOrderDao();
    public static DaoFactory getInstance() {
        if (daoFactory == null) {

            synchronized (DaoFactory.class) {
                if (daoFactory == null) {
                    daoFactory = new JDBCDaoFactory();
                }
            }
        }
        return daoFactory;
    }
}
