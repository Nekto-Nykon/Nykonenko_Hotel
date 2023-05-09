package nykon.DAO.impl.JDBC;

import lombok.AllArgsConstructor;
import lombok.Getter;
import nykon.DAO.Mapper.RoleMapper;
import nykon.DAO.Mapper.RoomMapper;
import nykon.DAO.Mapper.UsrMapper;
import nykon.DAO.RoomDAO;
import nykon.Domain.Role;
import nykon.Domain.Room;
import nykon.Domain.Usr;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Getter
@AllArgsConstructor
public class JDBCRoomDAO implements RoomDAO {
    private Connection connection;

    @Override
    public Optional<Room> findById(int id) {
        Optional<Room> maybeRoom = Optional.empty();
        String sql = "SELECT * FROM rooms WHERE id = ?;";

        try (final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                maybeRoom = Optional.ofNullable(new RoomMapper().extractFromResultSet(resultSet));
            }

        } catch (SQLException error) {
            error.printStackTrace();
        }

        return maybeRoom;
    }

    @Override
    public List<Room> findAll() {
        List<Room> rooms = new ArrayList<Room>();
        String sql = "SELECT * FROM rooms;";
        try (final Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                rooms.add(new RoomMapper().extractFromResultSet(resultSet));
            }
        } catch (SQLException error) {
            error.printStackTrace();
        }
        return rooms;
    }

    @Override
    public void create(Room roomToCreate) {
        String createStatementString = "INSERT INTO rooms (name, comfortlevel, price, adultcapacity, childcapacity) " + "VALUES (?, ?, ?, ?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(createStatementString)) {
            statement.setString(1, roomToCreate.getName());
            statement.setInt(2, roomToCreate.getComfortLevel().ordinal());
            statement.setBigDecimal(3, roomToCreate.getPrice());
            statement.setInt(4, roomToCreate.getAdultCapacity());
            statement.setInt(5, roomToCreate.getChildCapacity());
            if (statement.executeUpdate() == 0)
                throw new RuntimeException("Room couldn`t be created");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void update(Room obj) {
        String updateStatementString = "UPDATE rooms SET name = (?)," +
                " comfortLevel = (?)," +
                " price = (?), adultCapacity = (?), childCapacity = (?)" +
                ", WHERE id = (?);";
        try(PreparedStatement statement = connection.prepareStatement(updateStatementString)){
            statement.setString(1, obj.getName());
            statement.setInt(2, obj.getComfortLevel().ordinal());
            statement.setBigDecimal(3, obj.getPrice());
            statement.setInt(4, obj.getAdultCapacity());
            statement.setInt(5, obj.getChildCapacity());
            statement.setInt(6, obj.getId());
            if (statement.executeUpdate() == 0)
                throw new RuntimeException("Some problems with update room ");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void delete(Room obj) {

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
