package com.st.modules.device.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.st.common.api.ResultCode;
import com.st.common.exception.Asserts;
import com.st.modules.device.dto.UpdateDeviceDto;
import com.st.modules.device.mapper.DeviceMapper;
import com.st.modules.device.model.Device;
import com.st.modules.device.service.DeviceService;
import com.st.modules.room.dto.UpdateRoomDto;
import com.st.modules.room.mapper.RoomMapper;
import com.st.modules.room.model.Room;
import com.st.modules.room.service.RoomService;
import com.st.modules.toilet.dto.UpdateToiletDto;
import com.st.modules.toilet.mapper.ToiletMapper;
import com.st.modules.toilet.model.Toilet;
import com.st.modules.toilet.service.ToiletService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements DeviceService {
    @Autowired
    DeviceMapper deviceMapper;

    @Autowired
    ToiletMapper toiletMapper;

    @Autowired
    RoomMapper roomMapper;

    @Autowired
    ToiletService toiletService;

    @Autowired
    RoomService roomService;

    @Override
    public void updateDevice(UpdateDeviceDto updateDeviceDto) {
        Device device = new Device();
        BeanUtils.copyProperties(updateDeviceDto, device);
        device.setUpdateTime(new Date());
        deviceMapper.insert(device);

        // 通过sn号先去toilet表中查找对应的项目，只要temp_sensor_sn、air_sensor_sn有一个匹配上就可以
        QueryWrapper<Toilet> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("temp_sensor_sn", updateDeviceDto.getSn())
                .or()
                .eq("air_sensor_sn", updateDeviceDto.getSn());
        Toilet toilet = toiletMapper.selectOne(queryWrapper);
        if (toilet != null) {
            // 有信息匹配上了，调用更新toilet信息的服务
            UpdateToiletDto updateToiletDto = new UpdateToiletDto();
            BeanUtils.copyProperties(toilet, updateToiletDto);
            // 首先确定是哪个传感器匹配上了
            if (updateDeviceDto.getSn().equals(toilet.getTempSensorSn())) {
                // todo: 温度数据的处理
                updateToiletDto.setTemperature((float) 1.22);
            } else if (updateDeviceDto.getSn().equals(toilet.getAirSensorSn())) {
                // todo: 空气传感器数据的处理
                updateToiletDto.setAirStatus((float) 12.89);
            }
            // 再去调用更新toilet信息的服务
            toiletService.updateToilet(updateToiletDto);
        } else {
            // 如果没有匹配上，就去room表中查找对应的项目，只要occupied_sensor_sn匹配上就可以
            QueryWrapper<com.st.modules.room.model.Room> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("occupied_sensor_sn", updateDeviceDto.getSn());
            Room room = roomMapper.selectOne(queryWrapper1);
            if (room != null) {
                // 有信息匹配上了，调用更新room信息的服务
                UpdateRoomDto updateRoomDto = new UpdateRoomDto();
                BeanUtils.copyProperties(room, updateRoomDto);
                updateRoomDto.setRoomDbId(room.getId());
                // todo: 占用传感器数据的处理
                updateRoomDto.setOccupied(1);
                // 再去调用更新room信息的服务
                roomService.updateRoom(updateRoomDto);
            } else {
                Asserts.fail(ResultCode.DEVICE_MATCH_FAILED);
            }
        }
    }
}
