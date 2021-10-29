package io.github.apichlinski.employeeapi.permission;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PermissionFacade {
    private final PermissionTechnicalRepository permissionTechnicalRepository;
    private final PermissionElectricRepository permissionElectricRepository;

    public PermissionFacade(
            PermissionTechnicalRepository permissionTechnicalRepository,
            PermissionElectricRepository permissionElectricRepository

    ) {
        this.permissionTechnicalRepository = permissionTechnicalRepository;
        this.permissionElectricRepository = permissionElectricRepository;
    }

    public void delete(final int permissionId) {
        permissionElectricRepository.deleteById(permissionId);
    }

    public Optional<PermissionElectric> get(final int permissionId) {
        return permissionElectricRepository.findById(permissionId);
    }
}
