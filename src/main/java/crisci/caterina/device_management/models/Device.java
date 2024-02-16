package crisci.caterina.device_management.models;

import crisci.caterina.device_management.dto.device.NewDeviceDTO;
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

    public static Device fromDTO(NewDeviceDTO dto) {
        Device device = new Device();
        device.setName(dto.name());
        device.setDeviceType(dto.deviceType());
        device.setDeviceStatus(dto.deviceStatus());
        return device;
    }

}
