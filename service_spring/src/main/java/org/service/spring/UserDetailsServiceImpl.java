package org.service.spring;

import org.entity.Role;
import org.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

@Service("userDetailsServiceImpl")
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Inject
    private UserService userService;
    @Inject
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.getUserByLogin(email);
        Set<GrantedAuthority> roles = new HashSet();
        if (user.getLogin() != null) {
        	roleService.getRoleByUser(email);
        	List<Role> rolesList = new ArrayList ();
        	rolesList = roleService.getRoleByUser(email);
        	for (Role rol : rolesList) {
        		roles.add(new SimpleGrantedAuthority(rol.getName()));
        	
        	}
        }
        
        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(user.getLogin(), 
                                                                       user.getPassword(), 
                                                                       roles);
        return userDetails;
    }
}