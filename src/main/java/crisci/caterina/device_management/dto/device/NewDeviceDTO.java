package crisci.caterina.device_management.dto.device;

import crisci.caterina.device_management.models.DeviceStatus;
import crisci.caterina.device_management.models.DeviceType;
import jakarta.validation.constraints.*;

public record NewDeviceDTO(
        @NotEmpty(message = "device name is mandatory")
        @Size(min = 3, max = 30, message = "name length must be between 3 and 30 characters")
        String name,
        @NotNull
        DeviceType deviceType,
        @NotNull
        DeviceStatus deviceStatus
) {
}
