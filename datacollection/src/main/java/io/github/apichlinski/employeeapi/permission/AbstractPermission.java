package io.github.apichlinski.employeeapi.permission;

import io.github.apichlinski.employeeapi.employee.query.SimpleEmployeeQuery;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@MappedSuperclass
@Table(name="PERMISSIONS")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class AbstractPermission {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<SimpleEmployeeQuery> employees = new HashSet<>();
}
