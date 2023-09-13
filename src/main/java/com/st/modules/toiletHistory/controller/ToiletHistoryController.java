package com.st.modules.toiletHistory.controller;

import com.st.common.annotation.LoginRequired;
import com.st.common.api.CommonResult;
import com.st.common.enums.Permission;
import com.st.modules.toiletHistory.service.ToiletHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/toilet")
public class ToiletHistoryController {
    @Autowired
    ToiletHistoryService toiletHistoryService;

    @GetMapping("/history")
    @LoginRequired(needPermission = Permission.ADMIN)
    public CommonResult getToiletHistory(@RequestParam("toiletId") Integer toiletId) {
        if (toiletId == null) {
            return CommonResult.failed("参数错误");
        }
        return CommonResult.success(toiletHistoryService.getToiletHistory(toiletId), "获取厕所历史数据成功");
    }
}
