package org.dao.spring;

import org.dao.spring.config.DataConfig;
import org.dao.spring.pojo.UserPojo;
import org.dao.spring.repository.MetalDaoRepository;
import org.dao.spring.repository.RoleDaoRepository;
import org.dao.spring.repository.UserDaoRepository;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;


public class App {
	public static void main(String[] args) {

		AbstractApplicationContext context = new AnnotationConfigApplicationContext(DataConfig.class);

		UserDaoRepository userDaoRepository = (UserDaoRepository) context.getBean("userDaoRepository");
		System.out.println(userDaoRepository.findAll());
		System.out.println(userDaoRepository.findOne(3));
		System.out.println(userDaoRepository.findByLogin("k336699k@mail.ru"));
		System.out.println(userDaoRepository.findByLoginAndPassword("k336699k@mail.ru", "123456"));
		System.out.println(userDaoRepository.getUserByRole("Продавец"));
		UserPojo user = new UserPojo ("Никита", "Батюшков", "nikita@tut.by", "43498");
		userDaoRepository.saveAndFlush(user);
		
		MetalDaoRepository metalDaoRepository = (MetalDaoRepository)context.getBean("metalDaoRepository");
		System.out.println(metalDaoRepository.findByName("Арматура 20"));
		
		RoleDaoRepository roleDaoRepository = (RoleDaoRepository)context.getBean("roleDaoRepository");
		System.out.println(roleDaoRepository.findByName("Продавец"));
		System.out.println(roleDaoRepository.getRoleByUser("k336699k@mail.ru"));
		
		context.close(); 
	}
}
