package nykon.pisspringboot.DAO.Interface;

import nykon.pisspringboot.DAO.DAO;
import nykon.pisspringboot.Model.User;

import java.util.Optional;

public interface UserDao extends DAO<User>{
    Optional<User> findByEmail(String email);
}
