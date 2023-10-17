package com.st.common.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.st.modules.deviceData.dto.UpdateDeviceDataDto;
import com.st.modules.deviceData.mapper.DeviceDataMapper;
import com.st.modules.deviceData.model.DeviceData;
import com.st.modules.deviceData.service.DeviceDataService;
import com.st.modules.room.mapper.RoomMapper;
import com.st.modules.room.model.Room;
import com.st.modules.toilet.mapper.ToiletMapper;
import com.st.modules.toilet.model.Toilet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ScheduleJob {

    @Autowired
    RoomMapper roomMapper;

    @Autowired
    ToiletMapper toiletMapper;

    @Autowired
    DeviceDataMapper deviceDataMapper;

    @Autowired
    DeviceDataService deviceDataService;

    @Scheduled(fixedDelay = 1000 * 5)
    public void updateDeviceDataByFixedDelayTask() {
        // 从两个mapper中获取所有sn值，并去重
        // 从roomMapper中获取sn的所有值，并去重
        List<String> snList = roomMapper.selectList(null).stream().map(Room::getSn).distinct().collect(Collectors.toList());
        // 从toiletMapper中获取所有sn值，并去重
        snList.addAll(toiletMapper.selectList(null).stream().map(Toilet::getSn).distinct().collect(Collectors.toList()));
        // 去重
        snList = snList.stream().distinct().filter(sn -> sn.length() > 0).collect(Collectors.toList());
        // 遍历snList
        for (String sn : snList) {
            // 调用http://120.27.27.42:9001/stm32/device_data/get_device_data?sn=280379760059967接口，打印返回值
            String url = "http://120.27.27.42:9001/stm32/device_data/get_device_data?sn=" + sn;
            String result = HttpUtil.get(url);
            DeviceDataFormat deviceDataFormat = JSON.parseObject(result, DeviceDataFormat.class);
            String time = deviceDataFormat.getUpdateTime();
            time = time.substring(0, time.length() - 10);
            // 将time中的T替换成空格
            time = time.replace("T", " ");
            Date date = DateUtil.parse(time);
            // 从deviceDataMapper中获取当前sn的最新一条数据
            QueryWrapper<DeviceData> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("sn", sn);
            queryWrapper.orderByDesc("update_time");
            queryWrapper.last("limit 1");
            DeviceData newestDeviceData = deviceDataMapper.selectOne(queryWrapper);
            // 如果当前sn的最新一条数据的update_time字段值不等于当前时间的值
            if (newestDeviceData == null || newestDeviceData.getUpdateTime().getTime() != date.getTime()) {
                UpdateDeviceDataDto updateDeviceDataDto = new UpdateDeviceDataDto();
                updateDeviceDataDto.setSn(sn);
                updateDeviceDataDto.setValue(deviceDataFormat.getValue());
                deviceDataService.updateDeviceData(updateDeviceDataDto);
            }
        }
    }
}
