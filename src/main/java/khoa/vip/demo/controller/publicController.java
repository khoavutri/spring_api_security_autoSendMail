package khoa.vip.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import khoa.vip.demo.Service.Impl.JwtTokenService;
import khoa.vip.demo.Service.Impl.productServiceImpl;
import khoa.vip.demo.Service.Impl.userServiceImpl;
import khoa.vip.demo.model.productDto;
import khoa.vip.demo.model.responeDto;
import khoa.vip.demo.model.userDto;

@RestController
@RequestMapping("/public")
public class publicController {
	@Autowired
	productServiceImpl productserviceImpl;
	
	@Autowired
	userServiceImpl userserviceImpl;
	
	@PostMapping("/xemfullsanpham")
	public responeDto<List<productDto>> xemfullsp(){
		return responeDto.<List<productDto>>builder().status(200)
				.msg("OK").data(productserviceImpl.selectAll()).build();
	}
	
	@PostMapping("/searchNameProduct")
	public responeDto<List<productDto>> searchNameProduct(@RequestParam("name") String name){
		
		return responeDto.<List<productDto>>builder().status(200).msg("OK")
				.data(productserviceImpl.selectByName(name)).build();
	}
	
	@PostMapping("/SignUp")
	public responeDto<userDto> SignUp(@ModelAttribute @Valid userDto userdto){
		if (userserviceImpl.selectByUserName(userdto.getUserName())!=null) {
			return responeDto.<userDto>builder().status(400).msg("bad request").build();
		}
			userdto.setRole("USER");
			userserviceImpl.insert(userdto);
		return responeDto.<userDto>builder().status(201).msg("Created").
				data(userdto).build();
	}
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtTokenService jwtTokenService;
	
	@PostMapping("/login")
	public responeDto<String> login(@RequestParam("username") String username,
			@RequestParam("password") String password){
		authenticationManager.authenticate(new 
				UsernamePasswordAuthenticationToken(username, password));
		
		
		return responeDto.<String>builder().status(200)
				.msg("success").data(jwtTokenService.creatToken(username)).build();
	}
	
	@PostMapping("/show")
	public responeDto<String> show(@RequestParam("token") String token
		){

		return responeDto.<String>builder().status(200)
				.msg("success").data(jwtTokenService.getUserName(token)).build();
	}
	
}
