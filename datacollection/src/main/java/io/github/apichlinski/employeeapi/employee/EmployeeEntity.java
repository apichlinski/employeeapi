package io.github.apichlinski.employeeapi.employee;

import io.github.apichlinski.employeeapi.company.query.SimpleCompanyQuery;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Getter
@Setter
@Table(name="EMPLOYEE")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String firstName;

    private String lastName;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private SimpleCompanyQuery company;

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
    }

    public Employee toDto() {
        return Employee.builder()
                .id(id)
                .firstName(lastName)
                .lastName(firstName)
                .company(company)
                .build();
    }
}