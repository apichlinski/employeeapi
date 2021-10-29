package io.github.apichlinski.employeeapi.employee;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
    void deleteById(int id);

    @Query(value = "SELECT e FROM EmployeeEntity e " +
            "WHERE " +
            "( :ownTechnicalPermission = false AND :ownElectricPermission = false) " +
            "OR " +
            "( :ownTechnicalPermission = true AND EXISTS (SELECT permissionsElectric FROM e.permissionsTechnical permissionsElectric)) " +
            "OR " +
            "( :ownElectricPermission = true AND EXISTS (SELECT permissionsElectric FROM e.permissionsElectric permissionsElectric)) ")
    Page<EmployeeEntity> findAllEmployees(
            @Param("ownElectricPermission") boolean ownElectricPermission,
            @Param("ownTechnicalPermission") boolean ownTechnicalPermission,
            Pageable pageable);
}