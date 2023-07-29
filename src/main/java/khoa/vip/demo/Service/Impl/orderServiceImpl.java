package khoa.vip.demo.Service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import khoa.vip.demo.Service.orderService;
import khoa.vip.demo.dao.impl.oderDaoImpl;
import khoa.vip.demo.dao.impl.productDaoImpl;
import khoa.vip.demo.dao.impl.userDaoImpl;
import khoa.vip.demo.entity.order;
import khoa.vip.demo.model.orderDto;

@Service
public class orderServiceImpl implements orderService{
	
	@Autowired
	oderDaoImpl orderDaoImpl;
	@Autowired
	productDaoImpl productDaoImpl;
	@Autowired
	userDaoImpl userDaoImpl;
	
	@Override
	@CachePut(cacheNames = "order_selectById",key = "#id")
	public orderDto selectById(int id) {
		// TODO Auto-generated method stub
		order order = orderDaoImpl.selectById(id);
		if (order==null) return null;
		orderDto orderDto = new orderDto();
		orderDto.setId(order.getId());
		orderDto.setProduct_id(order.getProducts().getId());
		orderDto.setUser_id(order.getUsers().getId());
		return orderDto;
	}

	@Override
	@CachePut(cacheNames = "order_selectByProduct")
	public List<orderDto> selectByProduct(int product_id) {
		List<order> orders = orderDaoImpl.selectByProduct(productDaoImpl.selectById(product_id));
		if (orders==null) return null;
		List<orderDto>lists = new ArrayList<>();
		for (order order:orders) {
			orderDto orderDto = new orderDto();
			orderDto.setId(order.getId());
			orderDto.setProduct_id(order.getProducts().getId());
			orderDto.setUser_id(order.getUsers().getId());
			lists.add(orderDto);
		}
		return lists;
	}

	@Override
	@CachePut(cacheNames = "order_selectByUser")
	public List<orderDto> selectByUser(int user_id) {
		List<order> orders = orderDaoImpl.selectByUser(userDaoImpl.selectById(user_id));
		if (orders==null) return null;
		List<orderDto>lists = new ArrayList<>();
		for (order order:orders) {
			orderDto orderDto = new orderDto();
			orderDto.setId(order.getId());
			orderDto.setProduct_id(order.getProducts().getId());
			orderDto.setUser_id(order.getUsers().getId());
			lists.add(orderDto);
		}
		return lists;
	}

	@Override
	@Caching(evict = {
			@CacheEvict(cacheNames = "order_selectById",allEntries = true),
			@CacheEvict(cacheNames = "order_selectByProduct",allEntries = true),
			@CacheEvict(cacheNames = "order_selectByUser",allEntries = true)
	})
	@Transactional
	public void insert(orderDto orderdto) {
		// TODO Auto-generated method stub
		order order = new order();
		order.setId(0);
		order.setUsers(userDaoImpl.selectById(orderdto.getUser_id()));
		order.setProducts(productDaoImpl.selectById(orderdto.getProduct_id()));
		orderDaoImpl.insert(order);
	}

	@Override
	@Caching(evict = {
			@CacheEvict(cacheNames = "order_selectById",allEntries = true),
			@CacheEvict(cacheNames = "order_selectByProduct",allEntries = true),
			@CacheEvict(cacheNames = "order_selectByUser",allEntries = true)
	})
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		orderDaoImpl.deleteById(id);
		
	}

}
