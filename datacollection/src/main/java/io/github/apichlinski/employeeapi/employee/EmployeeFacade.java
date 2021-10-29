package io.github.apichlinski.employeeapi.employee;

import io.github.apichlinski.employeeapi.employee.query.SimpleEmployeeQuery;
import io.github.apichlinski.employeeapi.permission.PermissionElectric;
import io.github.apichlinski.employeeapi.permission.PermissionFacade;
import io.github.apichlinski.employeeapi.permission.PermissionTechnical;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class EmployeeFacade {
    private static final int PAGE_SIZE = 10;
    private final EmployeeFactory employeeFactory;

    private final EmployeeRepository employeeRepository;
    private final PermissionFacade permissionFacade;

    public EmployeeFacade(
            final EmployeeRepository employeeRepository,
            final EmployeeFactory employeeFactory,
            final PermissionFacade permissionFacade
    ) {
        this.employeeRepository = employeeRepository;
        this.employeeFactory = employeeFactory;
        this.permissionFacade = permissionFacade;
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
                        .permissionsElectric(entity.getPermissionsElectric())
                        .permissionsTechnical(entity.getPermissionsTechnical())
                        .build()
                ).collect(toList());
    }

    private List<Employee> mapPageToDto(Page<EmployeeEntity> entities) {
        return entities.stream()
                .map(entity -> Employee.builder()
                        .id(entity.getId())
                        .company(entity.getCompany())
                        .firstName(entity.getFirstName())
                        .lastName(entity.getLastName())
                        .permissionsElectric(entity.getPermissionsElectric())
                        .permissionsTechnical(entity.getPermissionsTechnical())
                        .build()
                ).collect(toList());
    }

    public List<Employee> list(
            int page,
            Sort.Direction sort,
            boolean ownElectricPermission,
            boolean ownTechnicalPermission
    ) {
        return mapPageToDto(
                employeeRepository.findAllEmployees(
                        ownElectricPermission,
                        ownTechnicalPermission,
                        PageRequest.of(page, PAGE_SIZE, Sort.by(sort, "id"))
                )
        );
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

    public Optional<Employee> addElectricPermission(final int employeeId, final PermissionElectric toAdd) {
        return employeeRepository.findById(employeeId).map(employee -> {
            employee.addPermissionElectric(toAdd);
            return employeeRepository.save(employee).toDto();
        });
    }

    public Optional<Employee> addTechnicalPermission(final int employeeId, final PermissionTechnical toAdd) {
        return employeeRepository.findById(employeeId).map(employee -> {
            employee.addPermissionTechnical(toAdd);
            return employeeRepository.save(employee).toDto();
        });
    }

    public Optional<Employee> removePermission(final int employeeId, final int permissionId) {
        return employeeRepository.findById(employeeId).map(employee -> {
            return employeeRepository.save(employee).toDto();
        });
    }
}
