package chap05.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import chap05.spring.MemberDao;
import chap05.spring2.MemberRegisterService;
@Configuration
public class AppCtxManual1 {
	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}

	@Bean
	public MemberRegisterService memberRegisterService() {
		return new MemberRegisterService();
	}
}
