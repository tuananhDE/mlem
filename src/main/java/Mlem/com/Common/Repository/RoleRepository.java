package Mlem.com.Common.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Mlem.com.Common.Entity.Role;


@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
//	@Query("Select role_name from RoleUser where role_id = :roleId")
//	String getRoleName(@Param("roleId") int roleId);
}
