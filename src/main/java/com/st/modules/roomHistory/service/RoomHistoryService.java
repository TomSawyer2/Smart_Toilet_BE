package com.st.modules.roomHistory.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.st.modules.roomHistory.model.RoomHistory;

import java.util.List;

public interface RoomHistoryService extends IService<RoomHistory> {
    List<RoomHistory> getRoomHistory(int roomDbId);
}
