package com.st.modules.room.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.st.common.api.ResultCode;
import com.st.common.exception.Asserts;
import com.st.modules.room.dto.AddRoomDto;
import com.st.modules.room.dto.DeleteRoomDto;
import com.st.modules.room.dto.UpdateRoomDto;
import com.st.modules.room.mapper.RoomMapper;
import com.st.modules.room.model.Room;
import com.st.modules.room.service.RoomService;
import com.st.modules.roomHistory.mapper.RoomHistoryMapper;
import com.st.modules.roomHistory.model.RoomHistory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements RoomService {
    @Autowired
    RoomMapper roomMapper;

    @Autowired
    RoomHistoryMapper roomHistoryMapper;

    @Override
    public void addRoom(AddRoomDto addRoomDto) {
        // 首先检查mapper中是否有相同roomId和toiletId的坑位
        QueryWrapper<Room> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("room_id", addRoomDto.getRoomId());
        queryWrapper.eq("toilet_id", addRoomDto.getToiletId());
        Room roomDB = roomMapper.selectOne(queryWrapper);
        if (roomDB == null) {
            Room room = new Room();
            BeanUtils.copyProperties(addRoomDto, room);
            room.setUpdateTime(new Date());
            roomMapper.insert(room);
        } else {
            Asserts.fail(ResultCode.ROOM_EXIST);
        }
    }

    @Override
    public void updateRoom(UpdateRoomDto updateRoomDto) {
        // 1. 首先判断该坑位是否存在
        Room roomDB = roomMapper.selectById(updateRoomDto.getRoomDbId());
        if (roomDB == null) {
            Asserts.fail(ResultCode.ROOM_NOT_EXIST);
        } else {
            // 2. 如果坑位存在，如果occupied、status为null，则不更新
            if (updateRoomDto.getOccupied() == null && updateRoomDto.getStatus() == null) {
                // 如果数值信息不变则不写入历史表
            } else {
                RoomHistory roomHistory = new RoomHistory();
                roomHistory.setRoomId(roomDB.getRoomId());
                roomHistory.setToiletId(roomDB.getToiletId());
                roomHistory.setOccupied(updateRoomDto.getOccupied());
                roomHistory.setStatus(updateRoomDto.getStatus());
                roomHistory.setUpdateTime(new Date());
                roomHistory.setRoomDbId(updateRoomDto.getRoomDbId());
                roomHistoryMapper.insert(roomHistory);
            }
            // 3. 更新坑位信息
            Room room = new Room();
            BeanUtils.copyProperties(updateRoomDto, room);
            room.setId(updateRoomDto.getRoomDbId());
            room.setUpdateTime(new Date());
            roomMapper.updateById(room);
        }
    }

    @Override
    public void deleteRoom(DeleteRoomDto deleteRoomDto) {
        // 先判断坑位是否存在
        Room roomDB = roomMapper.selectById(deleteRoomDto.getRoomDbId());
        if (roomDB == null) {
            Asserts.fail(ResultCode.ROOM_NOT_EXIST);
        } else {
            // 如果坑位存在，则删除
            roomMapper.deleteById(deleteRoomDto.getRoomDbId());
        }
    }
}
