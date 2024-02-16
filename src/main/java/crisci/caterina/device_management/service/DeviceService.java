package crisci.caterina.device_management.service;

import crisci.caterina.device_management.dto.device.NewDeviceDTO;
import crisci.caterina.device_management.exceptions.NotFoundException;
import crisci.caterina.device_management.models.Device;
import crisci.caterina.device_management.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;

    public Page<Device> getAllPaged(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return deviceRepository.findAll(pageable);
    }

    public Device getById(Long id) {
        return deviceRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Device save(NewDeviceDTO device) {
        return deviceRepository.save(Device.fromDTO(device));
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
