package com.st.modules.room.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.st.modules.room.model.Room;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RoomMapper extends BaseMapper<Room> {
}
