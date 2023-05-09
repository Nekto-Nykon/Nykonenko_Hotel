package nykon.DAO.impl.JPA;

import lombok.AllArgsConstructor;
import lombok.Getter;
import nykon.DAO.RoleDAO;
import nykon.Domain.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
@Getter
@AllArgsConstructor
public class JpaRoleDAO implements RoleDAO {

    private final EntityManager entityManager;
    @Override
    public Optional<Role> findById(int id) {
        return Optional.of(entityManager.find(Role.class, id));
    }

    @Override
    public List<Role> findAll() {
        return entityManager.createQuery("select r from Role r").getResultList();
    }

    @Override
    public void create(Role obj) {
        entityManager.persist(obj);
    }

    @Override
    public void update(Role obj) {
        entityManager.merge(obj);
    }

    @Override
    public void delete(Role obj) {
        entityManager.remove(obj);
    }

    @Override
    public void close() {

    }
}
