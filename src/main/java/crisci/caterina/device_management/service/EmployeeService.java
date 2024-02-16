package crisci.caterina.device_management.service;

import crisci.caterina.device_management.exceptions.NotFoundException;
import crisci.caterina.device_management.models.Device;
import crisci.caterina.device_management.models.DeviceStatus;
import crisci.caterina.device_management.models.Employee;
import crisci.caterina.device_management.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DeviceService deviceService;

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee getById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Employee save(Employee device) {
        return employeeRepository.save(device);
    }

    public Employee updateById(Long id, Employee device) {
        if (!employeeRepository.existsById(id)) {
            return null;
        }

        return employeeRepository.save(device);
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    public Employee assignDevice(Long employeeId, Long deviceId, boolean toBeAssigned) {
        if (!deviceService.existsDeviceWithId(deviceId) || !employeeRepository.existsById(employeeId)) {
            return null;
        }

        Device device = deviceService.getById(deviceId);

        if (!DeviceStatus.AVAILABLE.equals(device.getDeviceStatus())) {
            System.err.printf("Device with id %d is not available right now.\n", deviceId);
            return null;
        }

        Employee employee = getById(employeeId);

        if (!toBeAssigned) {
            Optional<Device> employeeDevice = employee.getDevices()
                    .stream()
                    .filter(eDevice -> eDevice.getId().equals(deviceId))
                    .findFirst();

            if (employeeDevice.isEmpty()) {
                System.err.printf("Employee %d does not have any device with id %d currently assigned\n", employeeId, deviceId);
                return null;
            }

            device = employeeDevice.get();
            device.setDeviceStatus(DeviceStatus.AVAILABLE);
            device.setEmployee(null);
            deviceService.save(device);

            employee.getDevices().remove(device);
            return save(employee);
        }

        employee.getDevices().add(device);

        device.setDeviceStatus(DeviceStatus.ASSIGNED);
        device.setEmployee(employee);
        deviceService.save(device);

        return save(employee);
    }
}
