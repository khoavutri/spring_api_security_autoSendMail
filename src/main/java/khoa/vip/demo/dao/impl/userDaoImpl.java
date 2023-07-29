package khoa.vip.demo.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;
import khoa.vip.demo.dao.userDao;
import khoa.vip.demo.entity.user;

@Component
public class userDaoImpl {
	@Autowired
	userDao userdao;
	

	public user selectById(int id) {
		return userdao.findById(id).orElse(null);
	}
	
	
	public user callLogin(String username,String passWord) {
		return userdao.searchByUserNameAndPassWord(username, passWord);
	}
	

	public List<user> selectByDate(Date date){
		return userdao.findByDate(date);
	}
	
	
	public List<user> selectAll(){
		return userdao.findAll();
	}

	public user selectByUserName(String name) {
		return userdao.findByUserName(name);
	}
	@Transactional
	public void insert(user use) {
		userdao.save(use);
	}
	

	@Transactional
	public void deleteById(int id) {
		userdao.deleteById(id);
	}
}
