package com.st.modules.toiletHistory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.st.modules.toiletHistory.mapper.ToiletHistoryMapper;
import com.st.modules.toiletHistory.model.ToiletHistory;
import com.st.modules.toiletHistory.service.ToiletHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToiletHistoryServiceImpl extends ServiceImpl<ToiletHistoryMapper, ToiletHistory> implements ToiletHistoryService {
    @Autowired
    ToiletHistoryMapper toiletHistoryMapper;

    @Override
    public List<ToiletHistory> getToiletHistory(int toiletId) {
        // 从mapper中选取toiletId对应的所有数据
        QueryWrapper<ToiletHistory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("toilet_id", toiletId);
        return toiletHistoryMapper.selectList(queryWrapper);
    }
}
