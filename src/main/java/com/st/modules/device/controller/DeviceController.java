package com.st.modules.device.controller;

import com.st.common.api.CommonResult;
import com.st.modules.device.dto.UpdateDeviceDto;
import com.st.modules.device.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/device")
public class DeviceController {
    @Autowired
    DeviceService deviceService;

    @PostMapping("/update")
    public CommonResult updateDevice(@RequestBody UpdateDeviceDto updateDeviceDto) {
        deviceService.updateDevice(updateDeviceDto);
        return CommonResult.success(null, "更新设备信息成功");
    }
}
