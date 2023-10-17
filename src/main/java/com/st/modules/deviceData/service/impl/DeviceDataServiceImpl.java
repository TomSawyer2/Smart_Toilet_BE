package com.st.modules.deviceData.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.st.common.api.ResultCode;
import com.st.common.enums.FanStatus;
import com.st.common.exception.Asserts;
import com.st.modules.deviceData.dto.UpdateDeviceDataDto;
import com.st.modules.deviceData.mapper.DeviceDataMapper;
import com.st.modules.deviceData.model.DeviceData;
import com.st.modules.deviceData.service.DeviceDataService;
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
public class DeviceDataServiceImpl extends ServiceImpl<DeviceDataMapper, DeviceData> implements DeviceDataService {
    @Autowired
    DeviceDataMapper deviceDataMapper;

    @Autowired
    ToiletMapper toiletMapper;

    @Autowired
    RoomMapper roomMapper;

    @Autowired
    ToiletService toiletService;

    @Autowired
    RoomService roomService;

    @Override
    public void updateDeviceData(UpdateDeviceDataDto updateDeviceDataDto) {
        DeviceData deviceData = new DeviceData();
        BeanUtils.copyProperties(updateDeviceDataDto, deviceData);
        deviceData.setUpdateTime(new Date());
        deviceDataMapper.insert(deviceData);

        // 通过sn号先去toilet表中查找对应的项目
        QueryWrapper<Toilet> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sn", updateDeviceDataDto.getSn());
        Toilet toilet = toiletMapper.selectOne(queryWrapper);
        if (toilet != null) {
            // 有信息匹配上了，调用更新toilet信息的服务
            UpdateToiletDto updateToiletDto = new UpdateToiletDto();
            BeanUtils.copyProperties(toilet, updateToiletDto);
            // 如果value字符串开头为1x，就是温度数据
            if (updateDeviceDataDto.getValue().startsWith("1x")) {
                // todo: 温度数据的处理
                updateToiletDto.setTemperature((float) 1.22);
            } else if (updateDeviceDataDto.getValue().startsWith("2x")) {
                // todo: 湿度数据的处理
                updateToiletDto.setHumidity((float) 12.89);
            } else if (updateDeviceDataDto.getValue().startsWith("4x")) {
                // todo: 继电器数据的处理
                updateToiletDto.setFanStatus(FanStatus.RUNNING.getCode());
            }
            // 再去调用更新toilet信息的服务
            toiletService.updateToilet(updateToiletDto);
        } else {
            // 如果没有匹配上，就去room表中查找对应的项目
            QueryWrapper<Room> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("sn", updateDeviceDataDto.getSn());
            Room room = roomMapper.selectOne(queryWrapper1);
            if (room != null) {
                // 有信息匹配上了，调用更新room信息的服务
                UpdateRoomDto updateRoomDto = new UpdateRoomDto();
                BeanUtils.copyProperties(room, updateRoomDto);
                updateRoomDto.setRoomDbId(room.getId());
                if (updateDeviceDataDto.getValue().startsWith("3x")) {
                    // todo: 占用传感器数据的处理
                    updateRoomDto.setOccupied(1);
                }
                // 再去调用更新room信息的服务
                roomService.updateRoom(updateRoomDto);
            } else {
                Asserts.fail(ResultCode.DEVICE_MATCH_FAILED);
            }
        }
    }
}
