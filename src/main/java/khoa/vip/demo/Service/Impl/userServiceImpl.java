package khoa.vip.demo.Service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import khoa.vip.demo.Service.userSevice;
import khoa.vip.demo.dao.impl.oderDaoImpl;
import khoa.vip.demo.dao.impl.userDaoImpl;
import khoa.vip.demo.entity.order;
import khoa.vip.demo.entity.user;
import khoa.vip.demo.model.userDto;
@Service
public class userServiceImpl implements userSevice,UserDetailsService {
	@Autowired
	userDaoImpl userDaoImpl;
	
	@Autowired
	oderDaoImpl orderdaoImpl;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		userDto user = selectByUserName(username);
		if (user==null) {
			throw new UsernameNotFoundException("xác thực sai");
		}
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(user.getRole()));
		
		return new User(user.getUserName(),user.getPassWord(),authorities);
	}
	
	@Override
	@CachePut(cacheNames = "user_selectById",key = "#id")
	public userDto selectById(int id) {
		// TODO Auto-generated method stub
		user user = userDaoImpl.selectById(id);
		userDto userdto = new userDto();
		if (user==null)
		return null;
		
		userdto.setId(user.getId());
		userdto.setUserName(user.getUserName());
		userdto.setPassWord(user.getPassWord());
		userdto.setName(user.getName());
		userdto.setDate(user.getDate());
		userdto.setRole(user.getRole());
		userdto.setMail(user.getMail());
		List<Integer> list = new ArrayList<>();
		for (order o: user.getOrders()) {
			list.add(o.getId());
		}
		userdto.setOder_ids(list);
		
		return userdto;
	}

	@Override
	@CachePut(cacheNames = "user_callLogin")
	public userDto callLogin(String username, String passWord) {
		user user = userDaoImpl.callLogin(username, passWord);
		userDto userdto = new userDto();
		if (user==null)
		return null;
		
		userdto.setId(user.getId());
		userdto.setUserName(user.getUserName());
		userdto.setPassWord(user.getPassWord());
		userdto.setName(user.getName());
		userdto.setDate(user.getDate());
		userdto.setRole(user.getRole());
		userdto.setMail(user.getMail());
		List<Integer> list = new ArrayList<>();
		for (order o: user.getOrders()) {
			list.add(o.getId());
		}
		userdto.setOder_ids(list);
		
		return userdto;
	}

	@Override
	@CachePut(cacheNames = "user_selectByDate")
	public List<userDto> selectByDate(Date date) {
		// TODO Auto-generated method stub
		List<userDto> userDtos = new ArrayList<>();
		List<user> users = userDaoImpl.selectByDate(date);
		if (users == null) return null;
		for (user user: users) {
		userDto userdto = new userDto();
		
		
		userdto.setId(user.getId());
		userdto.setUserName(user.getUserName());
		userdto.setPassWord(user.getPassWord());
		userdto.setName(user.getName());
		userdto.setDate(user.getDate());
		userdto.setRole(user.getRole());
		userdto.setMail(user.getMail());
		List<Integer> list = new ArrayList<>();
		for (order o: user.getOrders()) {
			list.add(o.getId());
		}
		userdto.setOder_ids(list);
		
		userDtos.add(userdto);
		}
		return userDtos;
	}
	@Override
	@CachePut(cacheNames = "user_selectByUserName")
	public userDto selectByUserName(String userName) {
		// TODO Auto-generated method stub
		user user = userDaoImpl.selectByUserName(userName);
		userDto userdto = new userDto();
		if (user==null)
		return null;
		
		userdto.setId(user.getId());
		userdto.setUserName(user.getUserName());
		userdto.setPassWord(user.getPassWord());
		userdto.setName(user.getName());
		userdto.setDate(user.getDate());
		userdto.setRole(user.getRole());
		userdto.setMail(user.getMail());
		List<Integer> list = new ArrayList<>();
		for (order o: user.getOrders()) {
			list.add(o.getId());
		}
		userdto.setOder_ids(list);
		
		return userdto;
	}
	@Override
	@CachePut(cacheNames = "user_selectAll")
	public List<userDto> selectAll() {
		// TODO Auto-generated method stub
		List<userDto> userDtos = new ArrayList<>();
		List<user> users = userDaoImpl.selectAll();
		if (users == null) return null;
		for (user user: users) {
		userDto userdto = new userDto();
		
		
		userdto.setId(user.getId());
		userdto.setUserName(user.getUserName());
		userdto.setPassWord(user.getPassWord());
		userdto.setName(user.getName());
		userdto.setDate(user.getDate());
		userdto.setRole(user.getRole());
		userdto.setMail(user.getMail());
		List<Integer> list = new ArrayList<>();
		for (order o: user.getOrders()) {
			list.add(o.getId());
		}
		userdto.setOder_ids(list);
		
		userDtos.add(userdto);
		}
		return userDtos;
	}

	@Override
	@Caching(evict = {
			@CacheEvict(cacheNames = "user_selectById",allEntries = true),
			@CacheEvict(cacheNames = "user_callLogin",allEntries = true),
			@CacheEvict(cacheNames = "user_selectByDate",allEntries = true),
			@CacheEvict(cacheNames = "user_selectAll",allEntries = true),
			@CacheEvict(cacheNames = "user_selectByUserName",allEntries = true)
	})
	@Transactional
	public void insert(userDto userdto) {
		// TODO Auto-generated method stub
		user user = new user();
		user.setId(0);
		user.setUserName(userdto.getUserName());
		user.setPassWord(new BCryptPasswordEncoder().encode(userdto.getPassWord()));
		user.setName(userdto.getName());
		user.setDate(userdto.getDate());
		user.setRole(userdto.getRole());
		user.setMail(userdto.getMail());
//		List<order> lists = new ArrayList<>();
//		for (int i:userdto.getOder_ids()) {
//			order oi = orderdaoImpl.selectById(i);
//			lists.add(oi);
//		}
//		user.setOrders(lists);
		userDaoImpl.insert(user);
		
	}

	@Override
	@Caching(evict = {
			@CacheEvict(cacheNames = "user_selectById",allEntries = true),
			@CacheEvict(cacheNames = "user_callLogin",allEntries = true),
			@CacheEvict(cacheNames = "user_selectByDate",allEntries = true),
			@CacheEvict(cacheNames = "user_selectAll",allEntries = true),
			@CacheEvict(cacheNames = "user_selectByUserName",allEntries = true)
	})
	@Transactional
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		userDaoImpl.deleteById(id);
	}

	



}
