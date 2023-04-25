package nykon.Service;

import nykon.DAO.DaoFactory;
import nykon.DAO.RoomDAO;
import nykon.Domain.Room;

import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class RoomService {
    private final DaoFactory daoFactory = DaoFactory.getInstance();
    private static final Logger logger = LogManager.getLogManager().getLogger(RoomService.class.getName());
    public List<Room> findAllRooms() {
        RoomDAO roomDao = daoFactory.createRoomDao();
        return roomDao.findAll();
    }
}
