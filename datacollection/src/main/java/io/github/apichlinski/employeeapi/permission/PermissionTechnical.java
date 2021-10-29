package io.github.apichlinski.employeeapi.permission;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class PermissionTechnical extends AbstractPermission{
    private int id;
    private String code;
}
