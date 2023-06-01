package nykon.pisspringboot.DAO.Interface;

import nykon.pisspringboot.DAO.DAO;
import nykon.pisspringboot.Model.ComfortLevel;
import nykon.pisspringboot.Model.Room;

import java.util.Collection;
import java.util.List;

public interface RoomDao extends DAO<Room>{

    List<Room> findAllByIdNotIn(List<Integer> idList);

    List<Room> findByComfortLevelAndAdultCapacityAndChildCapacityNotIn(ComfortLevel comfortLevel, Integer adultCapacity, Integer childCapacity, Collection<Integer> idList);


}
