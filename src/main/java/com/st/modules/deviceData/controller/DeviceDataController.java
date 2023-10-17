package com.st.modules.deviceData.controller;

import com.st.common.api.CommonResult;
import com.st.modules.deviceData.dto.UpdateDeviceDataDto;
import com.st.modules.deviceData.service.DeviceDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/device")
public class DeviceDataController {
    @Autowired
    DeviceDataService deviceDataService;

    @PostMapping("/update")
    public CommonResult updateDevice(@RequestBody UpdateDeviceDataDto updateDeviceDataDto) {
        deviceDataService.updateDeviceData(updateDeviceDataDto);
        return CommonResult.success(null, "更新设备信息成功");
    }
}
