package khoa.vip.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import khoa.vip.demo.entity.order;
import khoa.vip.demo.entity.product;
import khoa.vip.demo.entity.user;

public interface oderDao extends JpaRepository<order, Integer> {
	List<order> findByUsers(user user);
	List<order> findByProducts(product product);
	
}
