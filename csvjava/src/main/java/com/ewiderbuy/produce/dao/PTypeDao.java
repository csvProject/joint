package com.ewiderbuy.produce.dao;





import com.ewiderbuy.produce.entity.PTypeDto;
import com.ewiderbuy.produce.entity.PTypeDto;

import java.util.List;

public interface PTypeDao {

    //多条件查询
    List<PTypeDto> findPTypeByCondi(PTypeDto indto);

    //名称，code同查
    List<PTypeDto> findPTypeByCodenm(String cdnm);
}
