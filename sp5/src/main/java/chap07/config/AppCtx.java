package chap07.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import chap07.aop.Calculator;
import chap07.aop.RecCalculator;
import chap07.aspect.ExeTimeAspect;

@Configuration
//@Aspect이 붙은 클래스를 공통기능으로 적용하기 위하 어노테이션
@EnableAspectJAutoProxy
public class AppCtx {
	@Bean
	public ExeTimeAspect exeTimeAspect() {
		return new ExeTimeAspect();
	}

	@Bean
	public Calculator calculator() {
		return new RecCalculator();
	}

}
