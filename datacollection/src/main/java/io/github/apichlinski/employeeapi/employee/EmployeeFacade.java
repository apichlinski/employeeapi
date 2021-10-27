package io.github.apichlinski.employeeapi.employee;

import io.github.apichlinski.employeeapi.employee.query.SimpleEmployeeQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class EmployeeFacade {
    private final EmployeeFactory employeeFactory;

    private final EmployeeRepository employeeRepository;

    public EmployeeFacade(
            final EmployeeRepository employeeRepository,
            final EmployeeFactory employeeFactory
    ) {
        this.employeeRepository = employeeRepository;
        this.employeeFactory = employeeFactory;
    }

    public List<Employee> list() {
        return employeeRepository
                .findAll()
                .stream()
                .map(entity -> Employee.builder()
                        .id(entity.getId())
                        .company(entity.getCompany())
                        .firstName(entity.getFirstName())
                        .lastName(entity.getLastName())
                        .build()
                ).collect(toList());
    }

    public Optional<Employee> get(int id) {
        return employeeRepository.findById(id).map(EmployeeEntity::toDto);
    }

    public Employee save(SimpleEmployeeQuery toSave) {
        EmployeeEntity employee = new EmployeeEntity();
        employee.setFirstName(toSave.getFirstName());
        employee.setLastName(toSave.getFirstName());
        employeeRepository.save(employee);
        return employee.toDto();
    }

    public Employee save(Employee toSave) {
        return
            employeeRepository.save(
                employeeRepository.findById(toSave.getId())
                    .map(existingEmployee -> {
                        //update
                        existingEmployee.setFirstName(toSave.getFirstName());
                        existingEmployee.setLastName(toSave.getLastName());
                        existingEmployee.setCompany(toSave.getCompany());
                        return existingEmployee;
                    }).orElseGet(() -> {
                        return new EmployeeEntity(toSave.getFirstName(), toSave.getLastName(), toSave.getCompany());
                    })
            ).toDto();
    }

    public void delete(int id) {
        employeeRepository.deleteById(id);
    }
}
