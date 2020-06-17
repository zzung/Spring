package study.spring.myapp;
public class MyDate {

	@MyAnnotation
	String name;
	
	@MyAnnotation(name="어노테이션")
	String toSay;
}
