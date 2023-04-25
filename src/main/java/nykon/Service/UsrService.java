package nykon.Service;

import nykon.Controller.Webpage;
import nykon.DAO.DaoFactory;
import nykon.DAO.RoleDAO;
import nykon.DAO.UsrDAO;
import nykon.Domain.Usr;

import java.util.Optional;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class UsrService {
    private final DaoFactory daoFactory = DaoFactory.getInstance();
    private static final Logger logger = LogManager.getLogManager().getLogger(UsrService.class.getName());

    public void create(Usr usrToCreate){
        try (UsrDAO usrDAO = daoFactory.createUsrDao()){
            usrDAO.create(usrToCreate);
        }
    }


    public Optional<Usr> findByEmail(String email) {
        try (UsrDAO usrDAO = daoFactory.createUsrDao()){
            return usrDAO.findByEmail(email);
        }
    }
}
