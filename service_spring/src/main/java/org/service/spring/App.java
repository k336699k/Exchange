package org.service.spring;

import org.service.spring.config.ConfigService;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.security.core.userdetails.UserDetailsService;

public class App 
{
    public static void main( String[] args ) throws Exception
    {
    	AbstractApplicationContext context = new AnnotationConfigApplicationContext(ConfigService.class);

    	UserService userService =  (UserService) context.getBean("userServiceImpl");
    	System.out.println(userService.getUser ("k336699k@mail.ru", "123456"));
    	System.out.println(userService.getUserByLogin("k336699k@mail.ru"));
    	System.out.println(userService.checkLogin("k336699k@mail.ru", "123456"));
    	userService.addUser("Никита", "Батюшков", "nikita@tut.by", "43498");
    	System.out.println(userService.getAllUsers());
//    	userService.deleteUser("nikita@tut.by");
    	System.out.println(userService.getUserByRole("Продавец"));
    	
    	RoleService roleService =  (RoleService) context.getBean("roleServiceImpl");
    	System.out.println(roleService.getRole("Продавец"));
    	roleService.addRole("Трудоголик");
    	roleService.deleteRole("Трудоголик");
    	System.out.println(roleService.getAllRoles());
    	System.out.println(roleService.getRoleByUser("k336699k@mail.ru"));
    	
    	MetalService metaService =  (MetalService) context.getBean("metalServiceImpl");
    	System.out.println(metaService.getMetal("Арматура 20"));
    	metaService.addMetal("Арматура 14", "2 т", "3000");
    	metaService.deleteMetal("Арматура 14");
    	System.out.println(metaService.getAllMetals());
    	
    	UserDetailsService userDetailsService =  (UserDetailsService) context.getBean("userDetailsServiceImpl");
    	System.out.println(userDetailsService.loadUserByUsername("k336699k@mail.ru"));
    	
    	System.out.println( "Hello World!" );
    }
}
