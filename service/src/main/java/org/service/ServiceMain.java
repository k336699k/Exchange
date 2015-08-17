package org.service;

import java.util.ArrayList;

import org.dao.database.dao.MetalDao;
import org.dao.database.dao.RoleDao;
import org.entity.Metal;
import org.entity.Role;
import org.entity.User;

public class ServiceMain {

	public static void main(String[] args) {
		System.out.println("Тестирование класса User");
		User user = new User("Никита", "Батюшков", "nikita@tut.by", "43498");
		UserService userService = new UserService();
		userService.addUser("Никита", "Батюшков", "nikita@tut.by", "43498");
		System.out.println("users = " + userService.getUser("k336699k@mail.ru", "123456"));
	//	userService.deleteUser("nikita@tut.by");

		System.out.println("Тестирование класса Metal");
		Metal metal = new Metal("Арматура 14", "2 т", 3000);
		MetalService metalService = new MetalService();
		metalService.addMetal("Арматура 14", "2 т", 3000);
		System.out.println("metals = " + metalService.findSubstance("Арматура 14"));
		ArrayList<Metal> metals = (ArrayList<Metal>) metalService.readSubstances();
		for (Metal met : metals) {
			System.out.println("metals = " + met);
		}
		MetalDao.getInstance().removeSubstance("Арматура 14");
		
		System.out.println("Тестирование класса Role");
		Role role = new Role("Администратор");
		RoleService roleService = new RoleService();
		roleService.addRole("Администратор");
        System.out.println("roles = " + roleService.findRole("Продавец"));
		ArrayList<Role> roles = (ArrayList<Role>) roleService.readRole();
		for (Role rol : roles) {
			System.out.println("roles = " + rol);
		}
		
		roleService.removeRole("Администратор");

	}

}
