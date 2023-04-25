package nykon.Service;

import nykon.DAO.DaoFactory;
import nykon.DAO.UsrDAO;
import nykon.Domain.Usr;

import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class OrderService {
    private final DaoFactory daoFactory = DaoFactory.getInstance();
    private static final Logger logger = LogManager.getLogManager().getLogger(OrderService.class.getName());
    public List<Usr> findAllUsers() {
        UsrDAO userDao = daoFactory.createUsrDao();
        return userDao.findAll();
    }
}
