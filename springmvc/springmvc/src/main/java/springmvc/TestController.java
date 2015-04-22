package springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("test.do")
public class TestController {
	@RequestMapping(params = "method=add")
	public String add(String add){  
		return "success";  
	}  

}
