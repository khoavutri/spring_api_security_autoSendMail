package khoa.vip.demo.Service;

import java.util.List;


import khoa.vip.demo.model.productDto;

public interface productSevice {
	public productDto selectById(int id);
	
	public List<productDto> selectByName(String name);

	public List<productDto> selectAll();
	
	public void insert(productDto productDto);
	
	
	public void deleteById(int id);
		
}
