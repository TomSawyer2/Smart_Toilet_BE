package com.st.modules.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.st.common.api.ResultCode;
import com.st.common.exception.Asserts;
import com.st.common.interceptor.AuthInterceptor;
import com.st.common.util.JwtUtils;
import com.st.modules.user.dto.LoginDto;
import com.st.modules.user.dto.RegisterDto;
import com.st.modules.user.mapper.UserMapper;
import com.st.modules.user.model.User;
import com.st.modules.user.service.UserService;
import com.st.modules.user.vo.GetUserInfoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    JwtUtils jwtUtils;

    @Override
    public void register(RegisterDto registerDto) {
        User user = new User();
        BeanUtils.copyProperties(registerDto, user);
        String username = user.getUsername();
        String pwd = user.getPassword();
        int count = baseMapper.selectCount(new QueryWrapper<User>().eq("username", username));
        if (count == 0) {
            // 进入注册逻辑
            user.setPassword(DigestUtils.md5DigestAsHex(pwd.getBytes()));
            baseMapper.insert(user);
        } else if (count > 0) {
            Asserts.fail(ResultCode.USERNAME_EXIST);
        } else {
            Asserts.fail(ResultCode.FAILED);
        }
    }

    @Override
    public String login(LoginDto loginDto) {
        User userCount = baseMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, loginDto.getUsername()));
        if (userCount == null) Asserts.fail(ResultCode.USER_NOT_EXIST);
        if (userCount.getUsername().equals(loginDto.getUsername())) {
            if(userCount.getPassword().equals(DigestUtils.md5DigestAsHex(loginDto.getPassword().getBytes()))) {
                String token = jwtUtils.generateJwtToken(userCount.getId());
                return token;
            } else {
                Asserts.fail(ResultCode.PWD_ERR);
                return null;
            }
        }
        return null;
    }

    @Override
    public GetUserInfoVo getUserInfo() {
        User user = AuthInterceptor.getCurrentUser();
        if (user == null) Asserts.fail(ResultCode.USER_NOT_EXIST);

        GetUserInfoVo getUserInfoVo = new GetUserInfoVo();
        BeanUtils.copyProperties(user, getUserInfoVo);

        return getUserInfoVo;
    }
}
