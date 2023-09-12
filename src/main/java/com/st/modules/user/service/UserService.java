package com.st.modules.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.st.modules.user.dto.LoginDto;
import com.st.modules.user.dto.RegisterDto;
import com.st.modules.user.model.User;
import com.st.modules.user.vo.GetUserInfoVo;

public interface UserService extends IService<User> {
    public void register(RegisterDto registerDto);
    public String login(LoginDto loginDto);
    public GetUserInfoVo getUserInfo();
}
