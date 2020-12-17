package kybmig.ssm.service;


import kybmig.ssm.mapper.UserMapper;
import kybmig.ssm.model.UserModel;
import kybmig.ssm.model.UserRole;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserService {
    private UserMapper userMapper;
    
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public String selectUsernameByTopicId(Integer id) {
        return userMapper.selectUsernameByTopicId(id);
    }
    
    public UserModel add(String username, String password, String email) {
        UserModel model = new UserModel();
        model.setUsername(username);
        model.setPassword(password);
        //从业务需求的角度出发,添加的用户都默认是normal身份
        model.setRole(UserRole.normal);
        model.setEmail(email);
        this.userMapper.insertUser(model);
        
        
        return model;
    }
    
    public void deleteById(Integer id) {
        this.userMapper.deleteUser(id);;
    }
    
    public UserModel update(Integer id, String username, String password) {
        UserModel model = new UserModel();
        model.setId(id);
        model.setUsername(username);
        model.setPassword(password);
        
        this.userMapper.updateUser(model);
        return model;
    }
    
    public UserModel findById(Integer id) {
        return this.userMapper.selectUser(id);
    }
    
    
    public List<UserModel> all() {
        return this.userMapper.selectAllUser();
    }

    public UserModel guest() {
        UserModel user = new UserModel();
        user.setRole(UserRole.guest);
        user.setId(-1);
        user.setUsername("游客");
        user.setPassword("游客");
        return user;
    }
    
    public UserModel findByUsername(String username) {
        return userMapper.selectOneByUsername(username);
    }
    
    
    public boolean validLogin(String username, String password) {
        UserModel userModel = userMapper.selectOneByUsername(username);
        
        if (userModel != null && userModel.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }
    
    
    public UserModel currentUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer id = (Integer) session.getAttribute("user_id");
        if (id == null) {
            return this.guest();
        }
        
        UserModel userModel = userMapper.selectUser(id);
        if (userModel == null) {
            userModel = this.guest();
        }
        
        return userModel;
    }
}
