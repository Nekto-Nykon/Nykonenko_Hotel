package nykon.DAO.impl.JPA;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nykon.DAO.OrderDAO;
import nykon.Domain.Order;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;


@AllArgsConstructor
public class JpaOrderDAO implements OrderDAO {
    @PersistenceContext
    private final EntityManager entityManager;
    @Override
    public Optional<Order> findById(int id) {
        return Optional.of(entityManager.find(Order.class, id));
    }

    @Override
    public List<Order> findAll() {
        return entityManager.createQuery("SELECT o from Order o").getResultList();

    }

    @Override
    public void create(Order order) {
        entityManager.persist(order);
    }

    @Override
    public void update(Order obj) {
        entityManager.merge(obj);
    }

    @Override
    public void delete(Order obj) {
        entityManager.remove(obj);
    }

    @Override
    public void close() {

    }
}
