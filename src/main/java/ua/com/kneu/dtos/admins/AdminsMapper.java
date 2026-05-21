package ua.com.kneu.dtos.admins;

import ua.com.kneu.entities.Admins;

public class AdminsMapper {

    public static AdminsDTO toDTO(Admins admin) {
        AdminsDTO dto = new AdminsDTO();

        dto.setId(admin.getId());
        if (admin.getUser() != null) {
            dto.setUserId(admin.getUser().getId());
        }
        if (admin.getAdminType() != null) {
            dto.setAdminTypeId(admin.getAdminType().getId());
            dto.setAdminType(admin.getAdminType().getType());
        }

        return dto;
    }

    public static Admins toEntity(AdminsDTO dto) {
        Admins admin = new Admins();
        admin.setId(dto.getId());
        return admin;
    }
}