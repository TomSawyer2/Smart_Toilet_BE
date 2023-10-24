package com.st.modules.deviceData.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.st.common.api.ResultCode;
import com.st.common.enums.FanStatus;
import com.st.common.enums.Occupied;
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

        String _value = updateDeviceDataDto.getValue();
        //判断数据类型选项字段
        String opt = updateDeviceDataDto.getValue().substring(6, 8);
        //计算数据部分的字节数
        String len = _value.substring(4, 6);
        int length = 0;
        if (len.equals("10")) {
            length = 2;
        } else if (len.equals("11")) {
            length = 4;
        }
        _value = _value.substring(8, 8 + length);
        int data = 0;
        for(int i = 0; i < _value.length(); i++) {
            data *= 10;
            data += (_value.charAt(i) - '0');
        }

        if (toilet != null) {
            // 有信息匹配上了，调用更新toilet信息的服务
            UpdateToiletDto updateToiletDto = new UpdateToiletDto();
            BeanUtils.copyProperties(toilet, updateToiletDto);
            // 如果value字符串开头为00，就是温度数据
            // 如果value字符串开头为01，就是湿度数据
            // 如果value字符串开头为11，就是继电器数据
            // 如果value字符串开头为10，就是红外数据
            if (opt.equals("00")) {
                // todo: 温度数据的处理
                updateToiletDto.setTemperature((float) data / 100);
            } else if (opt.equals("01")) {
                // todo: 湿度数据的处理
                updateToiletDto.setHumidity((float) data / 100);
            } else if (opt.equals("11")) {
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
                if (opt.equals("10")) {
                    // 占用传感器数据的处理
                    _value = _value.substring(8, 8 + length);
                    if (_value.equals("10")) {
                        // 10表示当前坑位没有人
                        updateRoomDto.setOccupied(Occupied.EMPTY.getCode());
                    } else {
                        // 否则当前坑位有人使用
                        updateRoomDto.setOccupied(Occupied.OCCUPIED.getCode());
                    }
                }
                // 再去调用更新room信息的服务
                roomService.updateRoom(updateRoomDto);
            } else {
                Asserts.fail(ResultCode.DEVICE_MATCH_FAILED);
            }
        }
    }
}
