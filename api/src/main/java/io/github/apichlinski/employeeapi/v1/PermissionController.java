package io.github.apichlinski.employeeapi.v1;

import io.github.apichlinski.employeeapi.employee.Employee;
import io.github.apichlinski.employeeapi.employee.EmployeeFacade;
import io.github.apichlinski.employeeapi.permission.PermissionElectric;
import io.github.apichlinski.employeeapi.permission.PermissionFacade;
import io.github.apichlinski.employeeapi.permission.PermissionTechnical;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/employee")
class PermissionController {
    private final EmployeeFacade employeeFacade;
    private final PermissionFacade permissionFacade;

    PermissionController(
            PermissionFacade permissionFacade,
            EmployeeFacade employeeFacade
    ) {
        this.permissionFacade = permissionFacade;
        this.employeeFacade = employeeFacade;
    }

    @PostMapping(value = "/{id}/permission/electric", produces = "application/json", consumes = "application/json")
    @Operation(summary = "Add/update employee", description = "", tags = { "Permission" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful added"),
            @ApiResponse(responseCode = "400", description = "Bad request nody")
    })
    ResponseEntity<Employee> addTechnicalPermission(@PathVariable int id, @RequestBody PermissionTechnical toAdd) {

        return employeeFacade.addTechnicalPermission(id, toAdd)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/{id}/permission/technical", produces = "application/json", consumes = "application/json")
    @Operation(summary = "Add/update employee", description = "", tags = { "Permission" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful added"),
            @ApiResponse(responseCode = "400", description = "Bad request nody")
    })
    ResponseEntity<Employee> addElectricPermission(@PathVariable int id, @RequestBody PermissionElectric toAdd) {
        return employeeFacade.addElectricPermission(id, toAdd)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/{id}/permission/electric/{permissionId}", produces = "application/json")
    @Operation(summary = "Add/update employee", description = "", tags = { "Permission" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful added"),
            @ApiResponse(responseCode = "400", description = "Bad request nody")
    })
    ResponseEntity<Employee> addTechnicalPermission(
            @PathVariable int id,
            @PathVariable int permissionId
    ) {
        permissionFacade.delete(permissionId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{id}/permission/technical/{permissionId}", produces = "application/json")
    @Operation(summary = "Add/update employee", description = "", tags = { "Permission" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful added"),
            @ApiResponse(responseCode = "400", description = "Bad request nody")
    })
    ResponseEntity<Employee> addElectricPermission(
            @PathVariable int id,
            @PathVariable int permissionId
    ) {
        permissionFacade.delete(permissionId);
        return ResponseEntity.ok().build();
    }
}