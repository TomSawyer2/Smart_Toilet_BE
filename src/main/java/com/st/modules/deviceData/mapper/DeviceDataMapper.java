package com.st.modules.deviceData.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.st.modules.deviceData.model.DeviceData;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DeviceDataMapper extends BaseMapper<DeviceData>  {
}
