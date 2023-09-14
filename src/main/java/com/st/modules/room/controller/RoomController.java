package com.st.modules.room.controller;

import com.st.common.annotation.LoginRequired;
import com.st.common.api.CommonResult;
import com.st.common.enums.Permission;
import com.st.modules.room.dto.AddRoomDto;
import com.st.modules.room.dto.DeleteRoomDto;
import com.st.modules.room.dto.UpdateRoomDto;
import com.st.modules.room.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/room")
public class RoomController {
    @Autowired
    RoomService roomService;

    @PostMapping("/add")
    @LoginRequired(needPermission = Permission.ADMIN)
    public CommonResult addRoom(@RequestBody AddRoomDto addRoomDto) {
        roomService.addRoom(addRoomDto);
        return CommonResult.success(null, "新增坑位信息成功");
    }

    @PostMapping("/update")
    @LoginRequired(needPermission = Permission.ADMIN)
    public CommonResult updateRoom(@RequestBody UpdateRoomDto updateRoomDto) {
        roomService.updateRoom(updateRoomDto);
        return CommonResult.success(null, "更新坑位信息成功");
    }

    @PostMapping("/delete")
    @LoginRequired(needPermission = Permission.ADMIN)
    public CommonResult deleteRoom(@RequestBody DeleteRoomDto deleteRoomDto) {
        roomService.deleteRoom(deleteRoomDto);
        return CommonResult.success(null, "删除坑位信息成功");
    }
}
