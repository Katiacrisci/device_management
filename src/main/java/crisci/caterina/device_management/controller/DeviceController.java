package crisci.caterina.device_management.controller;

import crisci.caterina.device_management.dto.device.NewDeviceDTO;
import crisci.caterina.device_management.dto.device.NewDeviceResponseDTO;
import crisci.caterina.device_management.exceptions.BadRequestException;
import crisci.caterina.device_management.models.Device;
import crisci.caterina.device_management.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public NewDeviceResponseDTO saveDevice(@RequestBody @Validated NewDeviceDTO body, BindingResult validation) throws Exception {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        Device device = deviceService.save(body);
        return new NewDeviceResponseDTO(device.getId());
    }

    @GetMapping("")
    public Page<Device> getAuthors(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
        return deviceService.getAllPaged(page, size, sortBy);
    }

    @GetMapping("/{deviceId}")
    public Device getById(@PathVariable long deviceId) throws Exception {
        return deviceService.getById(deviceId);
    }

    @PutMapping("/{deviceId}")
    public Device findAndUpdate(@PathVariable long deviceId, @RequestBody Device body) throws Exception {
        return deviceService.updateById(deviceId, body);
    }

    @DeleteMapping("/{deviceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204 NO CONTENT
    public void findAndDelete(@PathVariable long deviceId) {
        deviceService.deleteById(deviceId);
    }
}
