package com.st.modules.toiletHistory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.st.modules.toiletHistory.model.ToiletHistory;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ToiletHistoryMapper extends BaseMapper<ToiletHistory> {
}
