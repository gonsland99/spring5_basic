package chap05.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import chap05.spring.MemberDao;
import chap05.spring.MemberPrinter;
import chap05.spring.MemberSummaryPrinter;
import chap05.spring.VersionPrinter;

@Configuration
@ComponentScan(basePackages = {"spring", "spring2" }, 
	//스캔 제외하기1: 정규표현식 spring아래 Dao클래스 제외
	//excludeFilters = @Filter(type = FilterType.REGEX, pattern = "spring\\..*Dao"))
	//스캔 제외하기2: AspectJ를 이용한 모듈 추가
	excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = ManualBean.class))
	//스캔 제외하기3: 제외할 클래스 지정
	//excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = MemberDao.class))

public class AppCtxWithExclude {
	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
	
	@Bean
	@Qualifier("printer")
	public MemberPrinter memberPrinter1() {
		return new MemberPrinter();
	}

	@Bean
	@Qualifier("summaryPrinter")
	public MemberSummaryPrinter memberPrinter2() {
		return new MemberSummaryPrinter();
	}

	@Bean
	public VersionPrinter versionPrinter() {
		VersionPrinter versionPrinter = new VersionPrinter();
		versionPrinter.setMajorVersion(5);
		versionPrinter.setMinorVersion(0);
		return versionPrinter;
	}
}
