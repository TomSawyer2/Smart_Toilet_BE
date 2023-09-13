package com.st.modules.toilet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.st.modules.toilet.model.Toilet;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ToiletMapper extends BaseMapper<Toilet> {
}
