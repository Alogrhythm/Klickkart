package com.shop.app.server.businessservice.appbasicsetup.userrolemanagement;
import com.shop.app.server.repository.appbasicsetup.userrolemanagement.RolesRepository;
import com.shop.app.shared.appbasicsetup.userrolemanagement.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class RolesBusinessService {

    @Autowired
    private RolesRepository rolesRepository;

    public void update(Roles entity) throws Exception {
        if (entity.isHardDelete()) {
            rolesRepository.delete(entity.getRoleId());
        } else {
            rolesRepository.deleteRoleMenuBridge(entity.getDeletedRoleMenuBridgeList());
            rolesRepository.update(entity);
        }
    }

    public void update(List<Roles> entity) throws Exception {
        for (Roles _roles : entity) {
            if (_roles.isHardDelete()) {
                rolesRepository.delete(_roles.getRoleId());
            } else {
                rolesRepository.deleteRoleMenuBridge(_roles.getDeletedRoleMenuBridgeList());
                rolesRepository.update(_roles);
            }
        }
    }
}
