package Mlem.com.Common.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import Mlem.com.Common.Entity.Provider;
import Mlem.com.Common.Entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	@Query("SELECT u FROM User u WHERE u.email = :email AND u.provider = :auth_provider")
	public User getUserByEmail(@Param("email") String email,
			                   @Param("auth_provider") Provider provider);


}