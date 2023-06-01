package nykon.pisspringboot.DAO.Interface.Jpa;

import nykon.pisspringboot.DAO.Interface.RoomDao;
import nykon.pisspringboot.Model.ComfortLevel;
import nykon.pisspringboot.Model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface RoomRepository extends
        RoomDao,
        JpaRepository<Room, Integer> {

    List<Room> findAllByIdNotIn(List<Integer> idList);
    @Query("SELECT r FROM Room r " +
            "where r.adultCapacity = :adultCapacity " +
            "and r.childCapacity = :childCapacity " +
            "and r.comfortLevel = :comfortLevel " +
            "and r.id not in :idList")
    List<Room> findByComfortLevelAndAdultCapacityAndChildCapacityNotIn( ComfortLevel comfortLevel, Integer adultCapacity, Integer childCapacity, Collection<Integer> idList);

}
