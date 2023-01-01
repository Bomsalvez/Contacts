package dev.senzalla.contacts.repository;

import dev.senzalla.contacts.model.permission.entity.EPermission;
import dev.senzalla.contacts.model.permission.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Permission findByNamePermission(EPermission permission);
}