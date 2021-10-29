package io.github.apichlinski.employeeapi.permission;

import org.springframework.data.jpa.repository.JpaRepository;

interface PermissionTechnicalRepository extends JpaRepository<PermissionTechnical, Integer> {
}
