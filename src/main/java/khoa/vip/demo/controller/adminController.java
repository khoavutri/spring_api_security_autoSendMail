package khoa.vip.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import khoa.vip.demo.Service.Impl.productServiceImpl;

import khoa.vip.demo.model.productDto;
import khoa.vip.demo.model.responeDto;

@RestController
@RequestMapping("/admin")
public class adminController {
	@Autowired
	productServiceImpl productserviceImpl;
	
	//thao tác với sản phẩm
	@PostMapping("/themsanpham")
	public responeDto<productDto> themsanpham(@ModelAttribute @Valid productDto productDto) {
		if (productserviceImpl.selectById(productDto.getId())!=null) {
			return responeDto.<productDto>builder().status(400).msg("Bad request")
					.build();
		}
		productserviceImpl.insert(productDto);
		return responeDto.<productDto>builder().status(201).msg("Created")
				.build();
	}
	

	@DeleteMapping("/xoasanpham")
	public responeDto<String> xoasanpham(@RequestParam("id")int id){
		productserviceImpl.deleteById(id);
		return responeDto.<String>builder().status(200).msg("Xóa Thành Công sp").build();
	}
	
	
	@PostMapping("/updateProduct")
	public responeDto<String>updateProduct(@ModelAttribute @Valid productDto productDto){
		if (productserviceImpl.selectById(productDto.getId())==null) {
			return responeDto.<String>builder().status(400).msg("Bad request")
					.build();
		}
		productserviceImpl.insert(productDto);
		return responeDto.<String>builder().status(200).msg("updated")
				.build();
	}
	@PostMapping("selectById")
	public responeDto<productDto> selectById(@RequestParam("id") int id){
		return responeDto.<productDto>builder().status(200).msg("OK")
				.data(productserviceImpl.selectById(id)).build();
	}
	
	
}
