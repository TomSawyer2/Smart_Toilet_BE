package com.st.modules.roomHistory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.st.modules.room.model.Room;
import com.st.modules.roomHistory.mapper.RoomHistoryMapper;
import com.st.modules.roomHistory.model.RoomHistory;
import com.st.modules.roomHistory.service.RoomHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomHistoryServiceImpl extends ServiceImpl<RoomHistoryMapper, RoomHistory> implements RoomHistoryService {
    @Autowired
    RoomHistoryMapper roomHistoryMapper;

    @Override
    public List<RoomHistory> getRoomHistory(int roomDbId) {
        QueryWrapper<RoomHistory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("room_db_id", roomDbId);
        return roomHistoryMapper.selectList(queryWrapper);
    }
}
