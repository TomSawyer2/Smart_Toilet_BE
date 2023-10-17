package com.st.modules.deviceData.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.st.modules.deviceData.dto.UpdateDeviceDataDto;
import com.st.modules.deviceData.model.DeviceData;

public interface DeviceDataService extends IService<DeviceData> {
    void updateDeviceData(UpdateDeviceDataDto updateDeviceDataDto);
}
