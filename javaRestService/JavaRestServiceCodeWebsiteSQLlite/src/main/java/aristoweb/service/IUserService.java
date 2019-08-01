package aristoweb.service;


public interface IUserService {

	/*public List<UserLogin> getAllUsers();
	
	public void save(UserLogin person);*/
    
    public UserLoginModel getUser(LoginModel loginModel);
}
