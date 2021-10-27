package io.github.apichlinski.employeeapi;

import io.github.apichlinski.employeeapi.company.Company;
import io.github.apichlinski.employeeapi.company.CompanyFacade;
import io.github.apichlinski.employeeapi.employee.Employee;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Profile("!prod")
@Component("company Warmup")
public class CompanyWarmup implements ApplicationListener<ContextRefreshedEvent> {
    private final CompanyFacade companyFacade;

    CompanyWarmup(final CompanyFacade companyFacade) {
        this.companyFacade = companyFacade;
    }

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent contextRefreshedEvent) {
        /*
        Company company = companyFacade.save(Company
                .builder()
                //.id(1)
                .employees(
                        {
                                Employee.builder()
                                        .firstName()
                                        .lastName()
                                        .build()
                        }
                )
                .name("Company "+Math.random())
                .build()
        );
        */
        /*
        companyFacade.createEmployee(
                company.getId(),
                "Alan"+Math.random(),
                "Bekax"+Math.random()
        );*/
    }
}
