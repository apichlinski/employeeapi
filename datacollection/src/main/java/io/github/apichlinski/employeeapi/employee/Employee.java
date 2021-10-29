package io.github.apichlinski.employeeapi.employee;

import io.github.apichlinski.employeeapi.company.query.SimpleCompanyQuery;
import io.github.apichlinski.employeeapi.permission.PermissionElectric;
import io.github.apichlinski.employeeapi.permission.PermissionTechnical;
import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private SimpleCompanyQuery company;

    private Set<PermissionElectric> permissionsElectric = new HashSet<>();
    private Set<PermissionTechnical> permissionsTechnical = new HashSet<>();
}
