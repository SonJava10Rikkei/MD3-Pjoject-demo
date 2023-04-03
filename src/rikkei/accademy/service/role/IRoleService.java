package rikkei.accademy.service.role;

import rikkei.accademy.model.role.Role;
import rikkei.accademy.model.role.RoleName;

import java.util.List;

public interface IRoleService {
    List<Role> findAll();
    void save (Role role);
    Role findByRoleName(RoleName roleName);
}
