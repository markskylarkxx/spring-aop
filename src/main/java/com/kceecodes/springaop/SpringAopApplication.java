package com.kceecodes.springaop;

import com.kceecodes.springaop.config.BeanConfig;
import com.kceecodes.springaop.service.TransactionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class SpringAopApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAopApplication.class, args);


//		ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
//
//
//		TransactionService service = context.getBean(TransactionService.class);
//		service.checkout();
}

}
