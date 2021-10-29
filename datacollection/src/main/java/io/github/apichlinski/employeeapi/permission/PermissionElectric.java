package io.github.apichlinski.employeeapi.permission;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
public class PermissionElectric extends PermissionTechnical{
    private int id;
    private ZonedDateTime expirationDate;
}
