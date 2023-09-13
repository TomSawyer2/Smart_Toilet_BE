package com.st.modules.toilet.controller;

import com.st.common.annotation.LoginRequired;
import com.st.common.api.CommonResult;
import com.st.common.enums.Permission;
import com.st.modules.toilet.dto.AddToiletDto;
import com.st.modules.toilet.dto.DeleteToiletDto;
import com.st.modules.toilet.dto.RefreshAirDto;
import com.st.modules.toilet.dto.UpdateToiletDto;
import com.st.modules.toilet.service.ToiletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/toilet")
public class ToiletController {
    @Autowired
    ToiletService toiletService;

    @PostMapping("/add")
    @LoginRequired(needPermission = Permission.ADMIN)
    public CommonResult addToilet(@RequestBody AddToiletDto addToiletDto) {
        toiletService.addToilet(addToiletDto);
        return CommonResult.success(null, "新增厕所信息成功");
    }

    @GetMapping("/list")
    public CommonResult getToiletList() {
        return CommonResult.success(toiletService.getToiletList(), "获取厕所列表成功");
    }

    @PostMapping("/delete")
    @LoginRequired(needPermission = Permission.ADMIN)
    public CommonResult deleteToilet(@RequestBody DeleteToiletDto deleteToiletDto) {
        toiletService.deleteToilet(deleteToiletDto);
        return CommonResult.success(null, "删除厕所信息成功");
    }

    @PostMapping("/update")
//    @LoginRequired(needPermission = Permission.ADMIN)
    public CommonResult updateToilet(@RequestBody UpdateToiletDto updateToiletDto) {
        toiletService.updateToilet(updateToiletDto);
        return CommonResult.success(null, "更新厕所信息成功");
    }

    @PostMapping("/refreshAir")
    @LoginRequired(needPermission = Permission.ADMIN)
    public CommonResult refreshAir(@RequestBody RefreshAirDto refreshAirDto) {
        toiletService.refreshAir(refreshAirDto);
        return CommonResult.success(null, "换气成功");
    }
}
