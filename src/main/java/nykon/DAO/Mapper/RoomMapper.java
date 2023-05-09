package nykon.DAO.Mapper;

import nykon.Domain.ComfortLevel;
import nykon.Domain.Room;
import nykon.Domain.Usr;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomMapper implements Mapper<Room>{
    @Override
    public Room extractFromResultSet(ResultSet resultSet) throws SQLException {
        return Room.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .comfortLevel(resultSet.getObject("comfortLevel", ComfortLevel.class))
                .price(resultSet.getBigDecimal("price"))
                .adultCapacity(resultSet.getInt("adultCapacity"))
                .childCapacity(resultSet.getInt("childCapacity"))
                .build();
    }
}
