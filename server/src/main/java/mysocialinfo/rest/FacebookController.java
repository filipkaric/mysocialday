package mysocialinfo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mysocialinfo.service.FacebookService;

@RestController
@RequestMapping("/facebook")
public class FacebookController {
	
	@Autowired
	FacebookService facebookService;
	
	@RequestMapping("/tests")
	public String tests() {
		return "filip";
	}

	@RequestMapping("/test/{username}")
	public String test(@PathVariable String username) {
		return username;
	}
	


	@RequestMapping("/posts")
	public String posts() {
		try {
			return facebookService.getPosts();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
