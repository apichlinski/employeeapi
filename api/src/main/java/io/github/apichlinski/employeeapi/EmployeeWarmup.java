package io.github.apichlinski.employeeapi;

import io.github.apichlinski.employeeapi.company.Company;
import io.github.apichlinski.employeeapi.employee.Employee;
import io.github.apichlinski.employeeapi.employee.EmployeeFacade;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Profile("!prod")
@Component("employee Warmup")
public class EmployeeWarmup implements ApplicationListener<ContextRefreshedEvent> {
    private final EmployeeFacade employeeFacade;

    EmployeeWarmup(final EmployeeFacade employeeFacade) {
        this.employeeFacade = employeeFacade;
    }

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent contextRefreshedEvent) {
        //employee.setCompany(new Company("test"));
        /*employeeFacade.save(
                Employee
                        .builder()
                        .firstName("Alan"+Math.random())
                        .lastName("Bekax"+Math.random())
                        .company(Company.builder().name("dsfsdfds").build())
                        .build()
        );*/
    }
}