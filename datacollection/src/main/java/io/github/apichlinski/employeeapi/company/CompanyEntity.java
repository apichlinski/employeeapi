package io.github.apichlinski.employeeapi.company;

import io.github.apichlinski.employeeapi.employee.query.SimpleEmployeeQuery;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Entity
@Table(name="COMPANY")
class CompanyEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private Set<SimpleEmployeeQuery> employees = new HashSet<>();

    @PersistenceConstructor
    public CompanyEntity() {}

    public CompanyEntity(String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Company toDto() {
        return Company.builder()
            .id(id)
            .name(name)
            .employees(
                this.getEmployees().stream().map(
                    employee -> new SimpleEmployeeQuery(employee.getId(),employee.getFirstName(),employee.getFirstName())
                ).collect(toList())
            )
            .build();
    }

    Set<SimpleEmployeeQuery> getEmployees() {
        return this.employees;
    }
}