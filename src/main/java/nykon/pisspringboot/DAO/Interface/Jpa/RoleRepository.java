package nykon.pisspringboot.DAO.Interface.Jpa;

import nykon.pisspringboot.DAO.DAO;
import nykon.pisspringboot.DAO.Interface.RoleDao;
import nykon.pisspringboot.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends
        RoleDao,
        JpaRepository<Role, Integer> {
    Optional<Role> findByName(String guest);
}
