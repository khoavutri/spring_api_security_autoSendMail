package khoa.vip.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import khoa.vip.demo.entity.product;


/**/
public interface productDao extends JpaRepository<product, Integer> {
	List<product> findByName(String name );
}
