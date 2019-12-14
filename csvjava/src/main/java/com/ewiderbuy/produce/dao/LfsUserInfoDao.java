package com.ewiderbuy.produce.dao;





import com.ewiderbuy.produce.entity.GenerateErrorDto;
import com.ewiderbuy.produce.entity.LfsUserInfoDto;

import java.util.List;

public interface LfsUserInfoDao {

    //获取登录信息
    List<LfsUserInfoDto> loginForGetUserInfo(LfsUserInfoDto into);

}
