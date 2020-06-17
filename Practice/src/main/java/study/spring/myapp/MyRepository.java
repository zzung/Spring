package study.spring.myapp;

import org.springframework.stereotype.Repository;

@Repository
public class MyRepository implements IMyRepository{

	public String bye(String name) {
		return "bye "+name;
	}

	@Override
	public String tosay(String message) {
		return "you"+message;
	}
	
	
}
