package khoa.vip.demo.jobScheduler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import khoa.vip.demo.Service.Impl.sendMail;
import khoa.vip.demo.Service.Impl.userServiceImpl;
import khoa.vip.demo.model.userDto;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class jobShedule {
	
	@Autowired
	sendMail mail;
	
	@Autowired
	userServiceImpl impl;
	//giây phút giờ ngày tháng thứ
	@Scheduled(cron = "0 30 7 * * *")
	public void autoGoodMorning() {
		List<userDto> lists = impl.selectAll();
		for (userDto dto:lists) {
			if (!dto.getRole().equals("ADMIN"))
				if (dto.getMail()!=null) {
			mail.sendMail(dto.getMail(), "Chào buổi sáng "+dto.getName(), "Chúc bạn một buổi sáng vui vẻ!");
		}
		}
		
	}
}
