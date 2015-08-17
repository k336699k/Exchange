package org.dao.database.test;

import java.util.ArrayList;

import org.dao.database.dao.MetalDao;
import org.dao.database.dao.RoleDao;
import org.dao.database.dao.UserDao;
import org.entity.Metal;
import org.entity.Role;
import org.entity.User;
import org.resource.SqlManager;


public class TestDao {

	public static void main(String[] args) {

		System.out.println("Тестирование класса Role");

		Role role = new Role("Администратор");
		RoleDao.getInstance().addSubstance(role);

		ArrayList<Role> roles = (ArrayList<Role>) RoleDao.getInstance().readSubstances();
		for (Role rol : roles) {
			System.out.println("roles = " + rol);
		}
		System.out.println("roles = " + RoleDao.getInstance().findSubstance("Продавец"));
		RoleDao.getInstance().removeSubstance("Администратор");

		System.out.println("Тестирование класса Metal");
		Metal metal = new Metal("Арматура 14", "2 т", 3000);
		MetalDao.getInstance().addSubstance(metal);

		ArrayList<Metal> metals = (ArrayList<Metal>) MetalDao.getInstance().readSubstances();
		for (Metal met : metals) {
		System.out.println("metals = " + met);
		}
		System.out.println("metals = " + MetalDao.getInstance().findSubstance("Арматура 20"));
		MetalDao.getInstance().removeSubstance("Арматура 14");

		System.out.println("Тестирование класса User");
		User user = new User("Никита", "Батюшков", "nikita@tut.by", "43498");
		UserDao.getInstance().addSubstance(user);

		ArrayList<User> users = (ArrayList<User>) UserDao.getInstance().readSubstances();
		for (User us : users) {
			System.out.println("users = " + us);
		}
		System.out.println("users = " + UserDao.getInstance().findSubstance("k336699k@mail.ru"));
		System.out.println("users = " + UserDao.getInstance().getUser("k336699k@mail.ru", "123456"));
		UserDao.getInstance().removeSubstance("nikita@tut.by");
  	      	  System.out.println(SqlManager.getProperty("SQL_INSERT_METAL"));
	}
}
