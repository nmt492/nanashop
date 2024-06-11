package vn.devpro.personalproject.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import vn.devpro.personalproject.model.Role;
import vn.devpro.personalproject.model.User;

@Service
public class RoleService extends BaseService<Role> {

	@Override
	public Class<Role> clazz() {
		
		return Role.class;
	}
	
	public List<Role> findAllActive(){
		return super.executeNativeSql("SELECT * FROM tbl_role WHERE status=1");
	}
	
	@Transactional
	public void deleteRoleById (int id) {
		super.deleteById(id);
	}
	
	public void inactiveRole(Role role) {
		super.saveOrUpdate(role);
	}
	
	public Role getRoleByName (String name) {
		String sql = "select * from tbl_role where name='"+ name + "'";
		List<Role> roles = super.executeNativeSql(sql);
		if(roles.size() > 0) {
			return roles.get(0);
		}
		else {
			return new Role();
		}
	}
}
