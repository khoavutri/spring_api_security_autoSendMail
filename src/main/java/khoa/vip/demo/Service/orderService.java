package khoa.vip.demo.Service;

import java.util.List;

import khoa.vip.demo.model.orderDto;

public interface orderService {
	public orderDto selectById(int id);

	public List<orderDto> selectByProduct(int product_id);
	
	public List<orderDto> selectByUser(int user_id);

	public void insert(orderDto orderdto);
	
	public void deleteById(int id);
	
}
