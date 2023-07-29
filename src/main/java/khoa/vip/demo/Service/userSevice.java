package khoa.vip.demo.Service;

import java.util.Date;
import java.util.List;


import khoa.vip.demo.model.userDto;

public interface userSevice {
	public userDto selectById(int id);
	public userDto selectByUserName(String userName);
	public userDto callLogin(String username,String passWord);
	
	
	public List<userDto> selectByDate(Date date);
	
	
	public List<userDto> selectAll();
	
	public void insert(userDto userdto);
		
	public void deleteById(int id);
	
}
