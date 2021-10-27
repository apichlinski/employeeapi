package io.github.apichlinski.employeeapi;

import org.springframework.data.jpa.repository.JpaRepository;

interface PermissionTechnicalRepository extends JpaRepository<PermissionTechnical, Integer> {
}
