package crisci.caterina.device_management.controller;

import crisci.caterina.device_management.models.Device;
import crisci.caterina.device_management.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public Device saveDevice(@RequestBody Device body) throws Exception {
        System.out.println(body);
        return deviceService.save(body);
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
