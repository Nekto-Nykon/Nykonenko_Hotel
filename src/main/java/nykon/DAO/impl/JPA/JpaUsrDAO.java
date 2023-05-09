package nykon.DAO.impl.JPA;

import lombok.AllArgsConstructor;
import lombok.Getter;
import nykon.DAO.UsrDAO;
import nykon.Domain.Usr;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
@Getter
@AllArgsConstructor
public class JpaUsrDAO implements UsrDAO {
    private EntityManager entityManager;
    @Override
    public Optional<Usr> findById(int id) {
        return Optional.of(entityManager.find(Usr.class, id));
    }

    @Override
    public List<Usr> findAll() {
        return entityManager.createQuery("select u from Usr u", Usr.class).getResultList();
    }

    @Override
    public void create(Usr obj) {
        entityManager.persist(obj);
    }

    @Override
    public void update(Usr obj) {
        entityManager.merge(obj);
    }

    @Override
    public void delete(Usr obj) {
        entityManager.remove(obj);
    }

    @Override
    public void close() {

    }
}
