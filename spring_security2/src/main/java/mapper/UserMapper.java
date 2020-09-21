package mapper;

import java.util.List;
import po.User;

public interface UserMapper {
	List<User> getusers();
	User getUserByid(long userid);
	User getUserByusername(String username);
	void deleteuser(long userid);
	void deleteuserrole(long userid,long roleid);
	void adduserrole(long userid,long roleid);
	void adduser(User user);
	void updateuser(User user);
	void deleteuseroles(long uid);
}
