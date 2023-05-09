package nykon.DAO.impl.JDBC;

import lombok.AllArgsConstructor;
import lombok.Getter;
import nykon.DAO.Mapper.RoleMapper;
import nykon.DAO.Mapper.UsrMapper;
import nykon.DAO.RoleDAO;
import nykon.Domain.Role;
import nykon.Domain.Usr;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Getter
@AllArgsConstructor
public class JDBCRoleDAO implements RoleDAO {
    private final Connection connection;
    @Override
    public Optional<Role> findById(int id) {
        Optional<Role> maybeRole = Optional.empty();
        String sql = "SELECT * FROM roles WHERE id = ?;";
        try (final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                maybeRole = Optional.ofNullable(new RoleMapper().extractFromResultSet(resultSet));
            }

        } catch (SQLException error) {
            error.printStackTrace();
        }

        return maybeRole;
    }

    @Override
    public List<Role> findAll() {
        List<Role> roles = new ArrayList<Role>();
        String sql = "SELECT * FROM roles;";
        try (final Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                roles.add(new RoleMapper().extractFromResultSet(resultSet));
            }
        } catch (SQLException error) {
            error.printStackTrace();
        }
        return roles;
    }

    @Override
    public void create(Role roleToSave) {
        String insertStatementString = "INSERT INTO roles(name) " + "VALUES (?);";
        try (PreparedStatement statement = connection.prepareStatement(insertStatementString)) {

            statement.setString(1, roleToSave.getName());
            if (statement.executeUpdate() == 0)
                throw new RuntimeException("Role with this name is already exists");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void update(Role obj) {
        String updateStatementString = "UPDATE roles SET name = (?) WHERE id = (?);";
        try(PreparedStatement statement = connection.prepareStatement(updateStatementString)){
            statement.setString(1, obj.getName());
            statement.setInt(2, obj.getId());
            if (statement.executeUpdate() == 0)
                throw new RuntimeException("Some problems with update role ");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void delete(Role obj) {

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
