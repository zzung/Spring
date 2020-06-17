package study.spring.myapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyService implements IMyservice {

	@Autowired
	IMyRepository myRepository;
	
	@Override
	public String bye(String name) {
		return myRepository.bye(name);
	}

	@Override
	public String tosay(String message) {
		return myRepository.tosay(message);
	}

}
