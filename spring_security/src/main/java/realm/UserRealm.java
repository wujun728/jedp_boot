package realm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import service.UserService;

//用于授权的数据源
@Service
public class UserRealm implements UserDetailsService {
	@Autowired
	UserService userservice;

	public void setUserservice(UserService userservice) {
		this.userservice = userservice;
	}

	public UserService getUserservice() {
		return userservice;
	}

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		po.User user = userservice.findByUsername(username);
		if (user != null) {
			Set<String> roles = userservice.findRoles(user.getUsername());
			ArrayList<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
			Iterator<String> it = roles.iterator();
			while (it.hasNext()) {
				String str = it.next();
				System.out.println(str);
				auths.add(new SimpleGrantedAuthority(str));
			}
			org.springframework.security.core.userdetails.User mu = new org.springframework.security.core.userdetails.User(
					user.getUsername(), user.getPassword(), auths);
			return mu;
		}
		throw new UsernameNotFoundException("no username "+user.getUsername());
	}
}
