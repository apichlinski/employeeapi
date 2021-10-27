package io.github.apichlinski.employeeapi.company;

import io.github.apichlinski.employeeapi.employee.Employee;
import io.github.apichlinski.employeeapi.employee.query.SimpleEmployeeQuery;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class Company {
    private int id;
    private String name;
    private List<SimpleEmployeeQuery> employees = new ArrayList<>();

    public List<SimpleEmployeeQuery> getEmployees() {
        return employees;
    }
    /*
    public void addEmployee(SimpleEmployeeQuery employee) {
        if (employees.contains(employee)) {
            return;
        }
        //employees.add(SimpleEmployeeQuery);
        //employee.setCompany(this);
    }

    public void removeEmployee(SimpleEmployeeQuery employee) {
        if (!employees.contains(employee)) {
            return;
        }
        employees.remove(employee);
        employee.setCompany(null);
    }*/
}
