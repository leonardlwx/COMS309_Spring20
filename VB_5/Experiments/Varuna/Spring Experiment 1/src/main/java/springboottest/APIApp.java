package springboottest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages= {"hello"})
@SpringBootApplication
public class APIApp 
{
	public static void main(String[] args) 
	{
		SpringApplication.run(APIApp.class, args);
	}
}
