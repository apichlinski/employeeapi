package io.github.apichlinski.employeeapi;

import io.github.apichlinski.employeeapi.employee.Employee;
import io.github.apichlinski.employeeapi.employee.EmployeeFacade;
import io.github.apichlinski.employeeapi.employee.query.SimpleEmployeeQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/employee")
class EmployeeController {
    private final EmployeeFacade employeeFacade;

    EmployeeController(EmployeeFacade employeeFacade) {
        this.employeeFacade = employeeFacade;
    }

    @GetMapping(produces = "application/json")
    @Operation(summary = "Get employees list", description = "", tags = { "Employee" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful responsed"),
    })
    List<Employee> list() {
        return employeeFacade.list();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @Operation(summary = "Get employee by id", description = "", tags = { "Employee" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful created"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    ResponseEntity<Employee> get(@PathVariable int id) {
        return employeeFacade.get(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    @Operation(summary = "Add/update employee", description = "", tags = { "Employee" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful created"),
            @ApiResponse(responseCode = "400", description = "Bad request nody")
    })
    ResponseEntity<Employee> create(@RequestBody SimpleEmployeeQuery toCreate) {
        Employee result = employeeFacade.save(toCreate);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    @Operation(summary = "Remove employee by id", description = "", tags = { "Employee" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful removed"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    ResponseEntity<Employee> delete(@PathVariable int id) {
        employeeFacade.delete(id);
        return ResponseEntity.ok().build();
    }
}
