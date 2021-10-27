package io.github.apichlinski.employeeapi.company;

import io.github.apichlinski.employeeapi.company.query.SimpleCompanyQuery;
import io.github.apichlinski.employeeapi.employee.Employee;
import io.github.apichlinski.employeeapi.employee.EmployeeFacade;
import io.github.apichlinski.employeeapi.employee.query.SimpleEmployeeQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class CompanyFacade {
    private final CompanyRepository companyRepository;
    private final EmployeeFacade employeeFacade;

    public CompanyFacade(
            final CompanyRepository companyRepository,
            final EmployeeFacade employeeFacade
    ) {
        this.companyRepository = companyRepository;
        this.employeeFacade = employeeFacade;
    }

    public List<Company> list() {
        return companyRepository
                .findAll()
                .stream()
                .map(entity -> Company.builder()
                        .id(entity.getId())
                        .name(entity.getName())
                        .employees(
                                entity.getEmployees().stream().map(
                                        employee -> new SimpleEmployeeQuery(employee.getId(),employee.getFirstName(),employee.getFirstName())
                                ).collect(toList())
                        )
                        .build()
                ).collect(toList());
    }

    public Optional<Company> get(int id) {
        return companyRepository.findById(id).map(CompanyEntity::toDto);
    }

    public Company save(SimpleCompanyQuery toSave) {
        return
                companyRepository.save(
                        companyRepository.findById(toSave.getId())
                                .map(existingCompany -> {
                                    //update
                                    existingCompany.setId(toSave.getId());
                                    existingCompany.setName(toSave.getName());
                                    return existingCompany;
                                }).orElseGet(() -> {
                                    var result = new CompanyEntity(toSave.getName());
                                    return result;
                                })
                ).toDto();
    }

    public void delete(int id) {
        companyRepository.deleteById(id);
    }

    public Optional<Company> createEmployee(int companyId, String firstName, String lastName) {
        return companyRepository.findById(companyId).map(company -> {

                    employeeFacade.save(
                            Employee.builder()
                                .firstName(firstName)
                                .lastName(lastName)
                                .company(new SimpleCompanyQuery(companyId, company.getName()))
                                .build()
                    );
                    return company.toDto();
                }
        );
    }
}
