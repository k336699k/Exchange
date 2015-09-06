package org.dao.database.test;

import java.sql.SQLException;

import org.dao.MetalDao;
import org.dao.RoleDao;
import org.dao.UserDao;
import org.dao.iterface.GenericDao;
import org.dao.iterface.MetalDaoInterface;
import org.dao.iterface.RoleDaoInterface;
import org.dao.iterface.UserDaoInterface;
import org.entity.Metal;
import org.entity.Role;
import org.entity.User;

public class TestDao {

	public static void main(String[] args) throws SQLException {

		System.out.println("Тестирование класса User");
		User user = new User("Никита", "Батюшков", "nikita@tut.by", "43498");
		UserDaoInterface userDao = UserDao.getInstance();
		GenericDao<User> userGenericDao = UserDao.getInstance();
	    userDao.addSubstance(user);
	    userDao.updateSubstance("nikita@tut.by", "Продавец");

		System.out.println(userDao.getUserByRole("Продавец"));
		System.out.println(userDao.getUser("k336699k@mail.ru", "123456"));
		System.out.println(userGenericDao.readSubstances());
		System.out.println(userGenericDao.findSubstance("h_v_e@mail.ru"));
		userGenericDao.removeSubstance("nikita@tut.by");
	

		System.out.println("Тестирование класса Metal");
		Metal metal = new Metal("Арматура 34", "2 т", 3000);
		MetalDaoInterface metalDao = MetalDao.getInstance();
		GenericDao<Metal> metalGenericDao = MetalDao.getInstance();
		metalDao.addSubstance(metal);
		System.out.println(metalGenericDao.findSubstance("Арматура 20"));
		System.out.println(metalGenericDao.readSubstances());
		metalGenericDao.removeSubstance("Арматура 34");

		System.out.println("Тестирование класса Role");
		Role role = new Role("Начальник");
		RoleDaoInterface roleDao = RoleDao.getInstance();
		GenericDao<Role> roleGenericDao = RoleDao.getInstance();
		roleDao.addSubstance(role);
		System.out.println(roleGenericDao .findSubstance("Администратор"));
		System.out.println(roleGenericDao .readSubstances());
		roleGenericDao .removeSubstance("Начальник");
		System.out.println(roleDao.getRoleByUser("k336699k@mail.ru"));

	}

}
