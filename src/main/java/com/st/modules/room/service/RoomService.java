package com.st.modules.room.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.st.modules.room.dto.AddRoomDto;
import com.st.modules.room.dto.DeleteRoomDto;
import com.st.modules.room.dto.UpdateRoomDto;
import com.st.modules.room.model.Room;

public interface RoomService extends IService<Room> {
    void addRoom(AddRoomDto addRoomDto);
    void updateRoom(UpdateRoomDto updateRoomDto);
    void deleteRoom(DeleteRoomDto deleteRoomDto);
}
