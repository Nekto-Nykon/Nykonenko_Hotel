package nykon.DAO;

import nykon.Domain.Usr;

import java.util.Optional;

public interface UsrDAO extends DAO<Usr>{
    Optional<Usr> findByEmail(String email);
}
