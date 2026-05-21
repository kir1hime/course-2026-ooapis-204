package ua.com.kneu.dtos.clients;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientsDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String email;
    private String phone;
    private String address;
    private Integer age;
    private Long userId;
    private String username;
    private List<Long> accountIds;
}