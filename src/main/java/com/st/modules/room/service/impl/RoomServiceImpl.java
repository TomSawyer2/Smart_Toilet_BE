package com.st.modules.room.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.st.modules.room.mapper.RoomMapper;
import com.st.modules.room.model.Room;
import com.st.modules.room.service.RoomService;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements RoomService {
}
