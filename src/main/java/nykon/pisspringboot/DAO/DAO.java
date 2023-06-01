package nykon.pisspringboot.DAO;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    Optional<T> findById(int id);
    List<T> findAll();
    T save(T obj);
    void delete(T obj);
}
