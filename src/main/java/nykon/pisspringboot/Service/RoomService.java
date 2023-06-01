package nykon.pisspringboot.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nykon.pisspringboot.DAO.Interface.OrderDao;
import nykon.pisspringboot.DAO.Interface.RoomDao;
import nykon.pisspringboot.Model.ComfortLevel;
import nykon.pisspringboot.Model.Room;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class RoomService {
    @Qualifier("roomRepository")
    private final RoomDao roomDao;
    @Qualifier("orderRepository")
    private final OrderDao orderDao;
    @Transactional(readOnly = true)
    public List<Room> getAllRooms(){
        return roomDao.findAll();
    }
    @Transactional(readOnly = true)
    public List<Room> getAllAvailableInDate(LocalDate startDate, LocalDate endDate){
        List<Integer> idIncorectList = orderDao.findAllIncorrectByDate(startDate, endDate);
        return roomDao.findAllByIdNotIn(idIncorectList);
    }
    @Transactional(readOnly = true)
    public List<Room> getAllAvailable(
            LocalDate checkin, LocalDate checkOut,
            ComfortLevel comfortLevel,
            Integer adultCapacity, Integer childCapacity) {
        Collection<Integer> idList = orderDao.findAllIncorrectByDate(checkin, checkOut);
        return roomDao.findByComfortLevelAndAdultCapacityAndChildCapacityNotIn(comfortLevel, adultCapacity, childCapacity, idList);
    }

    public Room createRoom(String name, ComfortLevel comfortLevel, Integer adultCapacity, Integer childCapacity, BigDecimal price) {
        Room room = Room.builder()
                .name(name)
                .comfortLevel(comfortLevel)
                .adultCapacity(adultCapacity)
                .childCapacity(childCapacity)
                .price(price)
                .build();
        return roomDao.save(room);
    }

    public Room updateRoomPrice(Integer idRoom, BigDecimal price) {
        Optional<Room> maybeRoom = roomDao.findById(idRoom);
        if(maybeRoom.isEmpty()){
            throw new RuntimeException("Some problem with id Room ");
        }
        Room room = maybeRoom.get();
        room.setPrice(price);
        return roomDao.save(room);
    }
}
