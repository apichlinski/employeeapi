package io.github.apichlinski.employeeapi.company.query;

import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="COMPANY")
public class SimpleCompanyQuery {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;
    private String name;

    public SimpleCompanyQuery(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    @PersistenceConstructor
    public SimpleCompanyQuery() {}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
