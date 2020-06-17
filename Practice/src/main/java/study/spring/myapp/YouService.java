package study.spring.myapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class YouService implements IMyservice{

	@Autowired
	IMyRepository myRepository;
	
	@Override
	public String bye(String name) {
		return myRepository.bye(name);
	}

	@Override
	public String tosay(String message) {
		return myRepository.tosay("you"+message);
	}

	
	
}
