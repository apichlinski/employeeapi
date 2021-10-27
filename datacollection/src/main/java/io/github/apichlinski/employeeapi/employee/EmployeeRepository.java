package io.github.apichlinski.employeeapi.employee;

import org.springframework.data.jpa.repository.JpaRepository;

interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
    void deleteById(int id);
}
