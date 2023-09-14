package com.st.modules.roomHistory.controller;

import com.st.common.api.CommonResult;
import com.st.modules.roomHistory.model.RoomHistory;
import com.st.modules.roomHistory.service.RoomHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/room")
public class RoomHistoryController {
    @Autowired
    RoomHistoryService roomHistoryService;

    @GetMapping("/history")
    public CommonResult getRoomHistory(@RequestParam("roomDbId") Integer roomDbId) {
        return CommonResult.success(roomHistoryService.getRoomHistory(roomDbId), "坑位历史数据获取成功");
    }
}
