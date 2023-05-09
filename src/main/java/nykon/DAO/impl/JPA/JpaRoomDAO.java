package nykon.DAO.impl.JPA;

import lombok.AllArgsConstructor;
import lombok.Getter;
import nykon.DAO.RoomDAO;
import nykon.Domain.Room;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
@Getter
@AllArgsConstructor
public class JpaRoomDAO implements RoomDAO{
    @PersistenceContext
    private final EntityManager entityManager;
    @Override
    public Optional<Room> findById(int id) {
        return Optional.of(entityManager.find(Room.class, id));
    }

    @Override
    public List<Room> findAll() {
        return entityManager.createQuery("select r from Room r").getResultList();
    }

    @Override
    public void create(Room obj) {
        entityManager.persist(obj);
    }

    @Override
    public void update(Room obj) {
        entityManager.merge(obj);
    }

    @Override
    public void delete(Room obj) {
        entityManager.remove(obj);
    }

    @Override
    public void close() {

    }
}
