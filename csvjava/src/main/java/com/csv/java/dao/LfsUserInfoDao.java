package com.csv.java.dao;





import com.csv.java.entity.GenerateErrorDto;
import com.csv.java.entity.LfsUserInfoDto;

import java.util.List;

public interface LfsUserInfoDao {

    //获取登录信息
    List<LfsUserInfoDto> loginForGetUserInfo(LfsUserInfoDto into);

}
