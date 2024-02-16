package crisci.caterina.device_management.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Device {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private DeviceType deviceType;

    private DeviceStatus deviceStatus;

    @ManyToOne
    private Employee employee;

}
