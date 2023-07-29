package khoa.vip.demo.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.security.access.AccessDeniedException;

import khoa.vip.demo.model.responeDto;

@RestControllerAdvice
public class restControllerEception {
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public responeDto<String> NoHandlerFoundException(){
		return responeDto.<String>builder().status(404).msg("not found").build();
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public responeDto<String> BadCredentialsException(){
		return responeDto.<String>builder().status(401).msg("xác thực không thành công").build();
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public responeDto<String> MethodArgumentNotValidException(){
		return responeDto.<String>builder().status(400).msg("bad request").build();
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public responeDto<String> AccessDeniedException(){
		return responeDto.<String>builder().status(403).msg("không đủ phân quyền").build();
	}
	
	@ExceptionHandler(Exception.class)
	public responeDto<String> Exception(){
		return responeDto.<String>builder().status(500).msg("lỗi server").build();
	}
}
