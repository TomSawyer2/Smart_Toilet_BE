package com.st.modules.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.st.common.api.ResultCode;
import com.st.common.exception.Asserts;
import com.st.common.interceptor.AuthInterceptor;
import com.st.modules.admin.dto.UpdatePermissionDto;
import com.st.modules.admin.service.AdminService;
import com.st.modules.admin.vo.GetDeviceListVo;
import com.st.modules.admin.vo.GetFeedbackListVo;
import com.st.modules.admin.vo.GetUserListVo;
import com.st.modules.deviceData.mapper.DeviceDataMapper;
import com.st.modules.deviceData.model.DeviceData;
import com.st.modules.feedback.mapper.FeedbackMapper;
import com.st.modules.feedback.model.Feedback;
import com.st.modules.user.mapper.UserMapper;
import com.st.modules.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    FeedbackMapper feedbackMapper;

    @Autowired
    DeviceDataMapper deviceDataMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public GetFeedbackListVo getFeedbackList(int page, int pageSize) {
        Page<Feedback> feedbackPage = new Page<>(page, pageSize);
        QueryWrapper<Feedback> queryWrapper = new QueryWrapper<>();
        feedbackMapper.selectPage(feedbackPage, queryWrapper);
        List<Feedback> codes = feedbackPage.getRecords();
        GetFeedbackListVo res = new GetFeedbackListVo();
        res.setPage(page);
        res.setPageSize(pageSize);
        res.setTotal((int) feedbackPage.getTotal());
        res.setList(codes);
        return res;
    }

    @Override
    public GetDeviceListVo getDeviceList(int page, int pageSize) {
        Page<DeviceData> devicePage = new Page<>(page, pageSize);
        QueryWrapper<DeviceData> queryWrapper = new QueryWrapper<>();
        deviceDataMapper.selectPage(devicePage, queryWrapper);
        List<DeviceData> codes = devicePage.getRecords();
        GetDeviceListVo res = new GetDeviceListVo();
        res.setPage(page);
        res.setPageSize(pageSize);
        res.setTotal((int) devicePage.getTotal());
        res.setList(codes);
        return res;
    }

    @Override
    public GetUserListVo getUserList(int page, int pageSize) {
        Page<User> userPage = new Page<>(page, pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        userMapper.selectPage(userPage, queryWrapper);
        List<User> codes = userPage.getRecords();
        GetUserListVo res = new GetUserListVo();
        res.setPage(page);
        res.setPageSize(pageSize);
        res.setTotal((int) userPage.getTotal());
        res.setList(codes);
        return res;
    }

    @Override
    public void updatePermission(UpdatePermissionDto updatePermissionDto) {
        User user = userMapper.selectById(updatePermissionDto.getUserId());
        if (user == null) {
            Asserts.fail(ResultCode.USER_NOT_EXIST);
        } else if (user.getId() == AuthInterceptor.getCurrentUser().getId()) {
            Asserts.fail(ResultCode.CANNOT_UPDATE_SELF_PERMISSION);
        } else {
            user.setPermission(updatePermissionDto.getPermission());
            userMapper.updateById(user);
        }
    }
}
