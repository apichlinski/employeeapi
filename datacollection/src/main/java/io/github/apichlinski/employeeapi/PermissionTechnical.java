package io.github.apichlinski.employeeapi;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
class PermissionTechnical {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
}
