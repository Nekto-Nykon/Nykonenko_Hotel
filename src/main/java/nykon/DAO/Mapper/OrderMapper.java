package nykon.DAO.Mapper;

import nykon.Domain.ComfortLevel;
import nykon.Domain.Order;
import nykon.Domain.Status;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements Mapper<Order>{
    @Override
    public Order extractFromResultSet(ResultSet resultSet) throws SQLException {
        return Order.builder()
                .id(resultSet.getInt("id"))
                .idAccount(resultSet.getInt("idAccount"))
                .idRoom(resultSet.getInt("idRoom"))
                .status(resultSet.getObject("status", Status.class))
                .checkIn(resultSet.getDate("checkIn").toLocalDate())
                .checkOut(resultSet.getDate("checkOut").toLocalDate())
                .adultCapacityRequirement(resultSet.getInt("adultCapacityRequirement"))
                .childCapacityRequirement(resultSet.getInt("childCapacityRequirement"))
                .comfortLevelRequirement(resultSet.getObject("comfortLevelRequirement", ComfortLevel.class ))
                .build();
    }
}
