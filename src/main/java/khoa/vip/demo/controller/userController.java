package khoa.vip.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import khoa.vip.demo.Service.Impl.orderServiceImpl;
import khoa.vip.demo.Service.Impl.userServiceImpl;
import khoa.vip.demo.model.orderDto;
import khoa.vip.demo.model.responeDto;
import khoa.vip.demo.model.userDto;

@RestController
@RequestMapping("/user")
public class userController {
	@Autowired
	userServiceImpl userserviceImpl;
	
	@Autowired
	orderServiceImpl orderserviceImpl;
	
	@PostMapping("/taoDonHang")
	public responeDto<orderDto> taoDonHang(@ModelAttribute @Valid orderDto orderdto)
	{
		orderserviceImpl.insert(orderdto);
		return responeDto.<orderDto>builder().status(201).msg("Created")
				.data(orderdto).build();
	}
	@PostMapping("/xoaDonHang")
	public responeDto<orderDto> xoaDonHang(@RequestParam("id") int id)
	{
		orderserviceImpl.deleteById(id);
		return responeDto.<orderDto>builder().status(200).msg("deleted")
				.build();
	}
	
	@PostMapping("/xemGioHang")
	public responeDto<List<orderDto>> xemGioHang(@RequestParam("user_id") int id)
	{
		
		return responeDto.<List<orderDto>>builder().status(200).msg("OK")
				.data(orderserviceImpl.selectByUser(id)).build();
	}
	
	@PostMapping("/suaThongTin")
	public responeDto<userDto> suaThongTin(@ModelAttribute @Valid userDto userdto)
	{	
		if (userserviceImpl.selectById(userdto.getId())==null) return responeDto.<userDto>builder()
				.status(400).msg("bad request").build();
		userserviceImpl.insert(userdto);
		return responeDto.<userDto>builder().status(200).msg("OK")
				.data(userdto).build();
	}
	
}
