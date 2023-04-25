package nykon.Service;

import nykon.DAO.DaoFactory;
import nykon.DAO.RoleDAO;
import nykon.DAO.UsrDAO;
import nykon.Domain.Role;
import nykon.Domain.Usr;

import java.util.List;
import java.util.Optional;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class RoleService {
    private final DaoFactory daoFactory = DaoFactory.getInstance();
    private static final Logger logger = LogManager.getLogManager().getLogger(OrderService.class.getName());
    public List<Role> findRoles() {
        try (RoleDAO roleDAO = daoFactory.createRoleDao()){
            return roleDAO.findAll();
        }

    }
    public Optional<Role> findById(Integer id){
        try (RoleDAO roleDAO = daoFactory.createRoleDao()){
            return roleDAO.findById(id);
        }
    }
}
