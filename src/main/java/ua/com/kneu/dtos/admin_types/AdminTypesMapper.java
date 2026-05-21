package ua.com.kneu.dtos.admin_types;

import ua.com.kneu.entities.AdminTypes;

public class AdminTypesMapper {

    public static AdminTypesDTO toDTO(AdminTypes adminType) {
        return new AdminTypesDTO(adminType.getId(), adminType.getType());
    }

    public static AdminTypes toEntity(AdminTypesDTO dto) {
        return new AdminTypes(dto.getId(), dto.getType());
    }
}