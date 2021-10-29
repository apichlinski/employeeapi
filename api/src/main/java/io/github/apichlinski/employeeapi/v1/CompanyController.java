package io.github.apichlinski.employeeapi.v1;

import io.github.apichlinski.employeeapi.company.Company;
import io.github.apichlinski.employeeapi.company.CompanyFacade;
import io.github.apichlinski.employeeapi.company.query.SimpleCompanyQuery;
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
@RequestMapping("v1/company")
class CompanyController {
    private final CompanyFacade companyFacade;

    CompanyController(CompanyFacade companyFacade) {
        this.companyFacade = companyFacade;
    }

    @GetMapping(produces = "application/json")
    @Operation(summary = "Get company list", description = "", tags = { "Company" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful responsed"),
    })
    List<Company> list() {
        return companyFacade.list();
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    @Operation(summary = "Add company", description = "", tags = { "Company" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful created"),
            @ApiResponse(responseCode = "400", description = "Bad request nody")
    })
    ResponseEntity<Company> create(@RequestBody SimpleCompanyQuery toCreate) {
        Company result = companyFacade.save(toCreate);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @Operation(summary = "Get company by id", description = "", tags = { "Company" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful created"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    ResponseEntity<Company> get(@PathVariable int id) {
        return companyFacade.get(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/employee")
    @Operation(summary = "Create employee", description = "", tags = { "Company" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful created"),
    })
    ResponseEntity<Company> createEmployee(@PathVariable int id, @RequestBody SimpleEmployeeQuery employee) {
        return companyFacade.createEmployee(id, employee.getFirstName(), employee.getLastName())
                .map(body -> ResponseEntity.created(URI.create("/" + id)).body(body))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    @Operation(summary = "Remove company by id", description = "", tags = { "Company" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful removed"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    ResponseEntity<Company> delete(@PathVariable int id) {
        companyFacade.delete(id);
        return ResponseEntity.ok().build();
    }
}