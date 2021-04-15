package chap09.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;

@Controller
public class HelloController {
	//http:/host:port/sp5/hello?name=?
	@GetMapping("/hello")
	public String hello(Model model,
			@RequestParam(value = "name", required = false) String name) {
		model.addAttribute("greeting", "안녕하세요, " + name); //HttpServletRequest에 greeting으로 전달
		return "hello"; //ViewResolverRegistry의 registry 파라미터값으로 hello를 전달
	}
}
