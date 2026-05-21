package ua.com.kneu.dtos.admins;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminsDTO {
    private Long id;
    private Long userId;
    private String userEmail;
    private Long adminTypeId;
    private String adminType;
}