package khoa.vip.demo.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import khoa.vip.demo.entity.user;

public interface userDao extends JpaRepository<user, Integer> {
	
	@Query("SELECT u FROM user u WHERE u.userName LIKE :s AND u.passWord LIKE :x")
	user searchByUserNameAndPassWord(@Param("s") String username,@Param("x")String password);
	
	List<user> findByDate(Date date);
	user findByUserName(String name);
}
