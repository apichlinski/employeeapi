package io.github.apichlinski.employeeapi.employee;

import io.github.apichlinski.employeeapi.company.query.SimpleCompanyQuery;
import io.github.apichlinski.employeeapi.permission.PermissionElectric;
import io.github.apichlinski.employeeapi.permission.PermissionTechnical;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@Table(name="EMPLOYEE")
class EmployeeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String firstName;

    private String lastName;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private SimpleCompanyQuery company;

    @ManyToMany(cascade=CascadeType.ALL)
    private Set<PermissionElectric> permissionsElectric = new HashSet<>();

    @ManyToMany(cascade= CascadeType.ALL)
    private Set<PermissionTechnical> permissionsTechnical = new HashSet<>();

    @PersistenceConstructor
    public EmployeeEntity() {}

    EmployeeEntity(
            String firstName,
            String lastName,
            SimpleCompanyQuery company
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.permissionsTechnical = permissionsTechnical;
        this.permissionsElectric = permissionsElectric;
    }

    Employee toDto() {
        return Employee.builder()
                .id(id)
                .firstName(lastName)
                .lastName(firstName)
                .company(company)
                .permissionsTechnical(permissionsTechnical)
                .permissionsElectric(permissionsElectric)
                .build();
    }

    void addPermissionElectric(PermissionElectric toAdd) {
        permissionsElectric.add(toAdd);
    }

    void removePermissionElectric(PermissionElectric toRemove) { permissionsElectric.remove(toRemove); }

    void addPermissionTechnical(PermissionTechnical toAdd) {
        permissionsTechnical.add(toAdd);
    }

    void removePermissionTechnical(PermissionTechnical toRemove) { permissionsTechnical.remove(toRemove); }
}