package nykon.DAO;

import java.util.List;
import java.util.Optional;

public interface DAO<T> extends AutoCloseable{
    Optional<T> findById(int id);
    List<T> findAll();
    void create(T obj);
    void update(T obj);
    void delete(T obj);
    void close();
}