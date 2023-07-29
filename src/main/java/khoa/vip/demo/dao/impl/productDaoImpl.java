package khoa.vip.demo.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;
import khoa.vip.demo.dao.productDao;
import khoa.vip.demo.entity.product;

@Component
public class productDaoImpl {
	@Autowired
	productDao productdao;
	
	
	public product selectById(int id) {
		return productdao.findById(id).orElse(null);
	}
	
	
	public List<product> selectByName(String name){
		return productdao.findByName(name);
	}
	
	
	public List<product> selectAll(){
		return productdao.findAll();
	}
	
	
	@Transactional
	public void insert(product product) {
		productdao.save(product);
	}
	
	@Transactional
	public void deleteById(int id) {
		productdao.deleteById(id);
	}
}
