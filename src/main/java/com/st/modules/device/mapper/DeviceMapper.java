package com.st.modules.device.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.st.modules.device.model.Device;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DeviceMapper extends BaseMapper<Device>  {
}
