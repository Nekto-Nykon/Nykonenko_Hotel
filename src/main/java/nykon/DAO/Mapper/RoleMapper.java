package nykon.DAO.Mapper;

import nykon.Domain.Role;
import nykon.Domain.Usr;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleMapper implements Mapper<Role>{
    @Override
    public Role extractFromResultSet(ResultSet resultSet) throws SQLException {
        return Role.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .build();
    }
}
