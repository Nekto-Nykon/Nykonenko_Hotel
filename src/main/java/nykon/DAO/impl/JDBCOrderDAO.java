package nykon.DAO.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import nykon.DAO.Mapper.OrderMapper;
import nykon.DAO.Mapper.RoleMapper;
import nykon.DAO.Mapper.RoomMapper;
import nykon.DAO.OrderDAO;
import nykon.Domain.Order;
import nykon.Domain.Role;
import nykon.Domain.Room;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Getter
@AllArgsConstructor
@Setter
public class JDBCOrderDAO implements OrderDAO {
    private Connection connection;

    @Override
    public Optional<Order> findById(int id) {
        Optional<Order> maybeOrder = Optional.empty();
        String sql = "SELECT * FROM orders WHERE id = ?;";

        try (final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                maybeOrder = Optional.ofNullable(new OrderMapper().extractFromResultSet(resultSet));
            }

        } catch (SQLException error) {
            error.printStackTrace();
        }

        return maybeOrder;
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<Order>();
        String sql = "SELECT * FROM orders;";
        try (final Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                orders.add(new OrderMapper().extractFromResultSet(resultSet));
            }
        } catch (SQLException error) {
            error.printStackTrace();
        }
        return orders;
    }

    @Override
    public void create(Order orderToCreate) {
        String createStatementString = "INSERT INTO orders (idAccount, idRoom, checkIn, checkOut, status, adultCapacityRequirement, childCapacityRequirement, comfortLevelRequirement ) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(createStatementString)) {
            statement.setInt(1, orderToCreate.getIdAccount());
            statement.setInt(2, orderToCreate.getIdRoom());
            statement.setDate(3, Date.valueOf(orderToCreate.getCheckIn()));
            statement.setDate(4, Date.valueOf(orderToCreate.getCheckOut()));
            statement.setInt(5, orderToCreate.getStatus().ordinal());
            statement.setInt(6, orderToCreate.getAdultCapacityRequirement());
            statement.setInt(7, orderToCreate.getChildCapacityRequirement());
            statement.setInt(8, orderToCreate.getComfortLevelRequirement().ordinal());
            if (statement.executeUpdate() == 0)
                throw new RuntimeException("Room couldn`t be created");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void update(Order obj) {

    }

    @Override
    public void delete(Order obj) {

    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}