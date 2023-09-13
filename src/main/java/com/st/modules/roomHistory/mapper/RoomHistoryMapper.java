package com.st.modules.roomHistory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.st.modules.roomHistory.model.RoomHistory;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RoomHistoryMapper extends BaseMapper<RoomHistory> {
}
