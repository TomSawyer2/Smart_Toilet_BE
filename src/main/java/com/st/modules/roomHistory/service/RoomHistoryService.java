package com.st.modules.roomHistory.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.st.modules.roomHistory.model.RoomHistory;
import com.st.modules.roomHistory.vo.GetRoomHistoryVo;

public interface RoomHistoryService extends IService<RoomHistory> {
    GetRoomHistoryVo getRoomHistory(int roomDbId, int page, int pageSize);
}
