package khoa.vip.demo.model;


import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class userDto {
	private int id;
	private String userName;
	private String passWord;
	private String name;
	private String role;
	private String mail;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy",timezone = "Asia/Ho_chi_minh")
	private Date date;  
	private List<Integer> oder_ids;
}

