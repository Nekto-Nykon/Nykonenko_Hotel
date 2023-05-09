package nykon.DAO.Mapper;

import nykon.Domain.Role;
import nykon.Domain.Usr;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsrMapper implements Mapper<Usr> {
    @Override
    public Usr extractFromResultSet(ResultSet resultSet) throws SQLException {
        final Role role = Role.builder()
                .id(resultSet.getInt("role_id"))
                .name(resultSet.getString("role_name"))
                .build();
        return Usr.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .email(resultSet.getString("email"))
                .password(resultSet.getString("password"))
                .role(role)
                .build();
    }
}

