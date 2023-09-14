package com.st.modules.admin.controller;

import com.st.common.annotation.LoginRequired;
import com.st.common.api.CommonResult;
import com.st.common.enums.Permission;
import com.st.modules.admin.dto.UpdatePermissionDto;
import com.st.modules.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @GetMapping("/feedback")
    @LoginRequired(needPermission = Permission.ADMIN)
    public CommonResult getFeedbackList(@RequestParam Integer page, @RequestParam Integer pageSize) {
        return CommonResult.success(adminService.getFeedbackList(page, pageSize), "获取反馈列表成功");
    }

    @GetMapping("/device")
    @LoginRequired(needPermission = Permission.ADMIN)
    public CommonResult getDeviceList(@RequestParam Integer page, @RequestParam Integer pageSize) {
        return CommonResult.success(adminService.getDeviceList(page, pageSize), "获取设备列表成功");
    }

    @GetMapping("/user")
    @LoginRequired(needPermission = Permission.ADMIN)
    public CommonResult getUserList(@RequestParam Integer page, @RequestParam Integer pageSize) {
        return CommonResult.success(adminService.getUserList(page, pageSize), "获取用户列表成功");
    }

    @PostMapping("/permission")
    @LoginRequired(needPermission = Permission.SUPER_ADMIN)
    public CommonResult updatePermission(@RequestBody UpdatePermissionDto updatePermissionDto) {
        adminService.updatePermission(updatePermissionDto);
        return CommonResult.success(null, "设置权限成功");
    }
}
