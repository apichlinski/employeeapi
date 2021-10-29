package io.github.apichlinski.employeeapi.v1;

import io.github.apichlinski.employeeapi.employee.Employee;
import io.github.apichlinski.employeeapi.employee.EmployeeFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/employee")
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
    List<Employee> list(
            @RequestParam(required = false) Integer page,
            Sort.Direction sort,
            @RequestParam(required = false) boolean ownElectricPermission,
            @RequestParam(required = false) boolean ownTechnicalPermission
    ) {
        int pageNumber = page!=null && page >= 0 ? page : 0;

        return employeeFacade.list(pageNumber, sort, ownElectricPermission, ownTechnicalPermission);
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
