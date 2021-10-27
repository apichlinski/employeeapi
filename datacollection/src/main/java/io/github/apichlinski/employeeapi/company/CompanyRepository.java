package io.github.apichlinski.employeeapi.company;

import org.springframework.data.jpa.repository.JpaRepository;

interface CompanyRepository extends JpaRepository<CompanyEntity, Integer> {
}