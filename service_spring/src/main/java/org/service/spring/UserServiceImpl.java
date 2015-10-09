package org.service.spring;



import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.dao.spring.pojo.UserPojo;
import org.dao.spring.repository.UserDaoRepository;
import org.entity.User;

import org.service.spring.convert.ConvetrToClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userServiceImpl")
@Transactional
public class UserServiceImpl implements UserService {
	private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);
	@Autowired
	private UserDaoRepository userDaoRepository;

	public UserDaoRepository getUserDaoRepository() {
		return userDaoRepository;
	}

	public void setUserDaoRepository(UserDaoRepository userDaoRepository) {
		this.userDaoRepository = userDaoRepository;
	}

	@Override
	public User getUser(String login, String password) {
		LOGGER.info("launched method getUser() in UserService");
		return ConvetrToClass.convetrToUser(userDaoRepository.findByLoginAndPassword(login, password));
	}

	@Override
	public User getUserByLogin(String login) {
		LOGGER.info("launched method  getUserByLogin() in UserService");
		return ConvetrToClass.convetrToUser(userDaoRepository.findByLogin(login));
	}

	@Override
	public boolean checkLogin(String enterLogin, String enterPass) throws ClassNotFoundException {
		boolean count = false;
		User user = null;
		user = getUser(enterLogin, enterPass);
		if (user.getLogin() != null) {
			count = true;
		}
		 LOGGER.info("User login is checked: " + count);
		return count;
	}

	@Override
	public void deleteUser(String login) {
		User user = null;
		user = getUserByLogin(login);
		if (user.getLogin() != null) {
		userDaoRepository.delete(user.getiD());
		}
		 LOGGER.info("launched method deleteUser() in UserService");
	}

	@Override
	public boolean addUser(String firstName, String lastName, String login, String password) {
		boolean flag = false;
		User user = getUser (login, password);
		if (user.getLogin() == null) {
		User newUser = new User (firstName,lastName,login,getEncryptUser(password));
		userDaoRepository.saveAndFlush(ConvetrToClass.convetrToUserPojo(newUser));
		flag = true;
		}
		
		 LOGGER.info("launched method addUser() in UserService");
		
		return flag;
	}

	@Override
	public List<User> getAllUsers() {
		LOGGER.info("launched method getAllUsers() in UserService");
		return ConvetrToClass.convetrToUserCollection(userDaoRepository.findAll());
	}

	@Override
	public List<User> getUserByRole (String roleName) {
		LOGGER.info("launched method getUserByRole() in UserService");
		return ConvetrToClass.convetrToUserCollection((List<UserPojo>) userDaoRepository.getUserByRole(roleName));
	}
	
	private static String getEncryptUser(String message) {
		String md5Hex = DigestUtils.sha1Hex(message).toString();
		return md5Hex;
	}

	

}
