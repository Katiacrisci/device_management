package crisci.caterina.device_management.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    private String username;
    private String name;
    private String surname;
    private String email;
    private String password;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Device> devices;


}
