package com.st.modules.toiletHistory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.st.modules.toiletHistory.mapper.ToiletHistoryMapper;
import com.st.modules.toiletHistory.model.ToiletHistory;
import com.st.modules.toiletHistory.service.ToiletHistoryService;
import com.st.modules.toiletHistory.vo.GetToiletHistoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToiletHistoryServiceImpl extends ServiceImpl<ToiletHistoryMapper, ToiletHistory> implements ToiletHistoryService {
    @Autowired
    ToiletHistoryMapper toiletHistoryMapper;

    @Override
    public GetToiletHistoryVo getToiletHistory(int toiletId, int page, int pageSize) {
        Page<ToiletHistory> toiletHistoryPage = new Page<>(page, pageSize);
        QueryWrapper<ToiletHistory> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        toiletHistoryMapper.selectPage(toiletHistoryPage, queryWrapper);
        List<ToiletHistory> list = toiletHistoryPage.getRecords();
        GetToiletHistoryVo res = new GetToiletHistoryVo();
        res.setPage(page);
        res.setPageSize(pageSize);
        res.setTotal((int) toiletHistoryPage.getTotal());
        res.setList(list);
        return res;
    }
}
