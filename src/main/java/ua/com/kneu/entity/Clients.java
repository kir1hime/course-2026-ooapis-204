package ua.com.kneu.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "clients")
public class Clients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    private String patronymic;
    private Integer age;
    private String phone;
    private String address;

    @OneToMany(mappedBy = "client")
    private List<Accounts> accounts = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "user_id")
    private Users user;

}
