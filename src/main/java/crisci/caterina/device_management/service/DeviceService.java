package crisci.caterina.device_management.service;

import crisci.caterina.device_management.exceptions.NotFoundException;
import crisci.caterina.device_management.models.Device;
import crisci.caterina.device_management.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;

    public List<Device> getAll() {
        return deviceRepository.findAll();
    }

    public Device getById(Long id) {
        return deviceRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Device save(Device device) {
        return deviceRepository.save(device);
    }

    public Device updateById(Long id, Device device) {
        if (getById(id) == null) {
            return null;
        }

        return deviceRepository.save(device);
    }

    public boolean existsDeviceWithId(Long id) {
        return deviceRepository.existsById(id);
    }

    public void deleteById(Long id) {
        deviceRepository.deleteById(id);
    }
}
