package org.dao.convert;

import java.util.ArrayList;
import java.util.List;

import org.dao.pojo.MetalPojo;
import org.dao.pojo.RolePojo;
import org.dao.pojo.UserPojo;
import org.entity.Metal;
import org.entity.Role;
import org.entity.User;

public class ConvetrToClass {

	public static User convetrToUser(UserPojo userPojo) {
		User user = new User();
		user.setiD(userPojo.getiD());
		user.setFirstName(userPojo.getFirstName());
		user.setLastName(userPojo.getLastName());
		user.setLogin(userPojo.getLogin());
		user.setPassword(userPojo.getPassword());
		return user;
	}

	public static UserPojo convetrToUserPojo(User user) {
		UserPojo userPojo = new UserPojo();
		userPojo.setiD(user.getiD());
		userPojo.setFirstName(user.getFirstName());
		userPojo.setLastName(user.getLastName());
		userPojo.setLogin(user.getLogin());
		userPojo.setPassword(user.getPassword());
		return userPojo;
	}

	public static List<User> convetrToUserCollection(List<UserPojo> usersPojo) {
		List users = new ArrayList<User>();
		for (int i = 0; i < usersPojo.size(); i++) {
			users.add(convetrToUser(usersPojo.get(i)));
		}
		return users;
	}
	
	public static MetalPojo convetrToMetalPojo(Metal metal) {
		MetalPojo metalPojo = new MetalPojo();
		metalPojo.setiD(metal.getiD());
		metalPojo.setTitle(metal.getTitle());
		metalPojo.setQuantity(metal.getQuantity());
		metalPojo.setPrice(metal.getPrice());
		return metalPojo;
	}
	
	public static Metal convetrToMetal(MetalPojo metalPojo) {
		Metal metal = new Metal();
		metal.setiD(metalPojo.getiD());
		metal.setTitle(metalPojo.getTitle());
		metal.setQuantity(metalPojo.getQuantity());
		metal.setPrice(metalPojo.getPrice());
		return metal;
	}
	
	public static List<Metal> convetrToMetalCollection(List<MetalPojo> metalsPojo) {
		List metals = new ArrayList<Metal>();
		for (int i = 0; i < metalsPojo.size(); i++) {
			metals.add(convetrToMetal(metalsPojo.get(i)));
		}
		return metals;
	}

	public static RolePojo convetrToRolePojo(Role role) {
		RolePojo rolePojo = new RolePojo ();
		rolePojo.setiD(role.getiD());
		rolePojo.setName(role.getName());
		return rolePojo;
	}
	
	public static Role convetrToRole (RolePojo rolePojo) {
		Role role = new Role ();
		role.setiD(rolePojo.getiD());
		role.setName(rolePojo.getName());
		return role;
	}
	
	public static List<Role> convetrToRoleCollection(List<RolePojo> rolesPojo) {
		List roles = new ArrayList<Role>();
		for (int i = 0; i < rolesPojo.size(); i++) {
			roles.add(convetrToRole(rolesPojo.get(i)));
		}
		return roles;
	}
	
	
}
