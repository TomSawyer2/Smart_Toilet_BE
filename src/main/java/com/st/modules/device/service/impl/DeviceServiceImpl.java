package com.st.modules.device.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.st.modules.device.mapper.DeviceMapper;
import com.st.modules.device.model.Device;
import com.st.modules.device.service.DeviceService;
import org.springframework.stereotype.Service;

@Service
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements DeviceService {
}
