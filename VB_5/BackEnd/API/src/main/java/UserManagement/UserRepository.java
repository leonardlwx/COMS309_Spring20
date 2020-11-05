package userManagement;

import org.springframework.data.repository.CrudRepository;

/**
 * The User repository contains inherited methods from the CrudRepsoitory for
 * database access
 * 
 * @author Varun
 */
public interface UserRepository extends CrudRepository<User, String> {

}
