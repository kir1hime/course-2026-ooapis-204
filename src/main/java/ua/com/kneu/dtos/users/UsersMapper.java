package ua.com.kneu.dtos.users;

import ua.com.kneu.entities.Users;

public class UsersMapper {

    public static UsersDTO toDTO(Users user) {
        return new UsersDTO(user.getId(), user.getUsername());

    }

    public static Users toEntity(UsersDTO dto) {
        Users user = new Users();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        return user;
    }
}
