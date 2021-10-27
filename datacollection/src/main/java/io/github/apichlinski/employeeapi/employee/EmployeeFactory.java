package io.github.apichlinski.employeeapi.employee;

import io.github.apichlinski.employeeapi.company.query.SimpleCompanyQuery;
import org.springframework.stereotype.Service;

@Service
class EmployeeFactory {
    EmployeeEntity from(Employee source, SimpleCompanyQuery company) {
        var result = new EmployeeEntity(source.getFirstName(),source.getLastName(), company);
        result.setId(source.getId());

        return result;
    }
}