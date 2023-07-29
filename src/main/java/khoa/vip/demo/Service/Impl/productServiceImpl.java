package khoa.vip.demo.Service.Impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import khoa.vip.demo.Service.productSevice;
import khoa.vip.demo.dao.impl.productDaoImpl;
import khoa.vip.demo.entity.product;
import khoa.vip.demo.model.productDto;

@Service
public class productServiceImpl implements productSevice{

	@Autowired
	productDaoImpl productDaoImpl;
	
	@Override
	@CachePut(cacheNames = "product_selectById",key = "#id")
	public productDto selectById(int id) {
		// TODO Auto-generated method stub
		product product = productDaoImpl.selectById(id);
		productDto productdto = new productDto();
		if (product == null) return null;
		productdto.setId(product.getId());
		productdto.setName(product.getName());
		return productdto;
	}

	@Override
	@CachePut(cacheNames = "product_selectByName")
	public List<productDto> selectByName(String name) {
		// TODO Auto-generated method stub
		List<product> lists = productDaoImpl.selectByName(name);
		if (lists == null) return null;
		List<productDto> productDtos = new ArrayList<>();
		for (product product:lists) {
			productDto productdto = new productDto();
			productdto.setId(product.getId());
			productdto.setName(product.getName());
			productDtos.add(productdto);
		}
		
		return productDtos;
	}

	@Override
	@CachePut(cacheNames = "product_selectAll")
	public List<productDto> selectAll() {
		// TODO Auto-generated method stub
		List<product> lists = productDaoImpl.selectAll();
		if (lists == null) return null;
		List<productDto> productDtos = new ArrayList<>();
		for (product product:lists) {
			productDto productdto = new productDto();
			productdto.setId(product.getId());
			productdto.setName(product.getName());
			productDtos.add(productdto);
		}
		
		return productDtos;
	}

	@Override
	@Caching(evict = {
		@CacheEvict(cacheNames = "product_selectById",allEntries = true),
		@CacheEvict(cacheNames = "product_selectByName",allEntries = true),
		@CacheEvict(cacheNames = "product_selectAll",allEntries = true)
	})
	@Transactional
	public void insert(productDto productDto) {
		// TODO Auto-generated method stub
		product product = new product();
		product.setId(0);
		product.setName(productDto.getName());
		productDaoImpl.insert(product);
	}

	@Override
	@Caching(evict = {
			@CacheEvict(cacheNames = "product_selectById",allEntries = true),
			@CacheEvict(cacheNames = "product_selectByName",allEntries = true),
			@CacheEvict(cacheNames = "product_selectAll",allEntries = true)
		})
	@Transactional
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		productDaoImpl.deleteById(id);
	}

}
