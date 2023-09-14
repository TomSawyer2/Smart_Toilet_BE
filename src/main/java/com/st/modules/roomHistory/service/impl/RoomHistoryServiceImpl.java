package com.st.modules.roomHistory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.st.modules.roomHistory.mapper.RoomHistoryMapper;
import com.st.modules.roomHistory.model.RoomHistory;
import com.st.modules.roomHistory.service.RoomHistoryService;
import com.st.modules.roomHistory.vo.GetRoomHistoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomHistoryServiceImpl extends ServiceImpl<RoomHistoryMapper, RoomHistory> implements RoomHistoryService {
    @Autowired
    RoomHistoryMapper roomHistoryMapper;

    @Override
    public GetRoomHistoryVo getRoomHistory(int roomDbId, int page, int pageSize) {
        Page<RoomHistory> roomHistoryPage = new Page<>(page, pageSize);
        QueryWrapper<RoomHistory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("room_db_id", roomDbId);
        roomHistoryMapper.selectPage(roomHistoryPage, queryWrapper);
        List<RoomHistory> list = roomHistoryPage.getRecords();
        GetRoomHistoryVo res = new GetRoomHistoryVo();
        res.setPage(page);
        res.setPageSize(pageSize);
        res.setTotal((int) roomHistoryPage.getTotal());
        res.setList(list);
        return res;
    }
}
