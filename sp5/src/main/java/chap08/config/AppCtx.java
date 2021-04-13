package chap08.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import chap08.spring.ChangePasswordService;
import chap08.spring.MemberDao;
import chap08.spring.MemberInfoPrinter;
import chap08.spring.MemberListPrinter;
import chap08.spring.MemberPrinter;
import chap08.spring.MemberRegisterService;

@Configuration
@EnableTransactionManagement
public class AppCtx {

	@Bean(destroyMethod = "close")	//커넥션풀에 보관된 Connection을 닫음
	public DataSource dataSource() {
		DataSource ds = new DataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/testDB?useUnicode=true&amp");
		ds.setUsername("root");
		ds.setPassword("1234");
		//유휴상태: 커넥션 요청 시 활성상태, 반환시 유휴상태
		ds.setInitialSize(2);	//초기화시 생성할 커넥션 개수(기본값 10)
		ds.setMaxActive(10);	//가져올수 있는 최대 커넥션 개수
		ds.setTestWhileIdle(true);	//유휴 상태 시 검사여부 지정(기본값 false)
		ds.setMinEvictableIdleTimeMillis(1000 * 60 * 3);//유휴 상태 유지시간 3분
		ds.setTimeBetweenEvictionRunsMillis(10 * 1000);	//유휴 커넥션 검사주기 10초추가
		return ds;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager tm = new DataSourceTransactionManager();
		tm.setDataSource(dataSource());
		return tm;
	}

	@Bean
	public MemberDao memberDao() {
		return new MemberDao(dataSource());
	}

	@Bean
	public MemberRegisterService memberRegSvc() {
		return new MemberRegisterService(memberDao());
	}

	@Bean
	public ChangePasswordService changePwdSvc() {
		ChangePasswordService pwdSvc = new ChangePasswordService();
		pwdSvc.setMemberDao(memberDao());
		return pwdSvc;
	}

	@Bean
	public MemberPrinter memberPrinter() {
		return new MemberPrinter();
	}

	@Bean
	public MemberListPrinter listPrinter() {
		return new MemberListPrinter(memberDao(), memberPrinter());
	}

	@Bean
	public MemberInfoPrinter infoPrinter() {
		MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
		infoPrinter.setMemberDao(memberDao());
		infoPrinter.setPrinter(memberPrinter());
		return infoPrinter;
	}
}
