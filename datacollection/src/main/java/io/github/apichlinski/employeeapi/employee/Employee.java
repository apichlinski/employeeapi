package io.github.apichlinski.employeeapi.employee;

import io.github.apichlinski.employeeapi.company.Company;
import io.github.apichlinski.employeeapi.company.query.SimpleCompanyQuery;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private SimpleCompanyQuery company;
}
