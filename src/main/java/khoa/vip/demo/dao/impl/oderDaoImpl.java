package khoa.vip.demo.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;
import khoa.vip.demo.dao.oderDao;
import khoa.vip.demo.entity.order;
import khoa.vip.demo.entity.product;
import khoa.vip.demo.entity.user;

@Component
public class oderDaoImpl {
	@Autowired
	oderDao dao;
	
	
	public order selectById(int id) {
		return dao.findById(id).orElse(null);
	}
	
	public List<order> selectByProduct(product produc){
		return dao.findByProducts(produc);
	}

	public List<order> selectByUser(user user){
		return dao.findByUsers(user);
	}

	@Transactional
	public void insert(order order) {
		dao.save(order);
	}
	
	
	@Transactional
	public void deleteById(int id) {
		dao.deleteById(id);
	}
}
