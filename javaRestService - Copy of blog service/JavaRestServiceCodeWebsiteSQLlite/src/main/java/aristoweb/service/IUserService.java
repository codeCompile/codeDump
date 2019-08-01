package aristoweb.service;

import aristoweb.models.LoginModel;
import aristoweb.models.UserLoginModel;


public interface IUserService {

	/*public List<UserLogin> getAllUsers();
	
	public void save(UserLogin person);*/
    
    public UserLoginModel getUser(LoginModel loginModel);
}
