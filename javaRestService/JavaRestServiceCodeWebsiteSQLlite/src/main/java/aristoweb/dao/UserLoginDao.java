package aristoweb.dao;

import org.springframework.data.repository.CrudRepository;

import aristoweb.entities.UserLogin;

public interface UserLoginDao extends CrudRepository<UserLogin, Long> {

   UserLogin findUserLoginByUserNameAndPassword(String username,String password);
}
