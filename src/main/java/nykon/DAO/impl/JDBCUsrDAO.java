package nykon.DAO.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import nykon.DAO.Mapper.RoleMapper;
import nykon.DAO.Mapper.UsrMapper;
import nykon.DAO.UsrDAO;
import nykon.Domain.Role;
import nykon.Domain.Usr;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Getter
@AllArgsConstructor
@Setter
public class JDBCUsrDAO implements UsrDAO {
    private Connection connection;

    @Override
    public Optional<Usr> findById(int id) {
        Optional<Usr> maybeUsr = Optional.empty();
        String sql = "SELECT * FROM usrs WHERE id = ?;";

        try (final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                maybeUsr = Optional.ofNullable(new UsrMapper().extractFromResultSet(resultSet));
            }

        } catch (SQLException error) {
            error.printStackTrace();
        }

        return maybeUsr;
    }

    @Override
    public List<Usr> findAll() {
        List<Usr> usrs = new ArrayList<Usr>();
        String sql = "SELECT * FROM usrs;";
        try (final Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                usrs.add(new UsrMapper().extractFromResultSet(resultSet));
            }
        } catch (SQLException error) {
            error.printStackTrace();
        }
        return usrs;
    }

    @Override
    public void create(Usr usrToCreate) {
        String insertStatementString = "INSERT INTO usrs (name, email, password, role_id) " + "VALUES (?, ?, ?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(insertStatementString)) {

            statement.setString(1, usrToCreate.getName());
            statement.setString(2, usrToCreate.getEmail());
            statement.setString(3, usrToCreate.getPassword());
            statement.setInt(4, usrToCreate.getRole().getId());
            if (statement.executeUpdate() == 0)
                throw new RuntimeException("User with this email is already exists");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void update(Usr obj) {
        String updateStatementString = "UPDATE usrs SET name = (?) , email = (?) WHERE id = (?);";
        try(PreparedStatement statement = connection.prepareStatement(updateStatementString)){
            statement.setString(1, obj.getName());
            statement.setString(2, obj.getEmail());
            statement.setInt(3, obj.getId());
            if (statement.executeUpdate() == 0)
                throw new RuntimeException("Some problems with update usrs ");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void delete(Usr obj) {

    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public Optional<Usr> findByEmail(String email) {
        Optional<Usr> maybeUsr = Optional.empty();
        String sql = "SELECT * FROM usrs WHERE email= ?;";

        try (final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                maybeUsr = Optional.ofNullable(new UsrMapper().extractFromResultSet(resultSet));
            }

        } catch (SQLException error) {
            error.printStackTrace();
        }

        return maybeUsr;
    }
}
