package com.ewiderbuy.produce.service;


import com.ewiderbuy.produce.entity.GenerateErrorDto;

import java.util.List;

public interface GenerateErrorService {

    public void addGenerateError(String orderNumber,String err);

    public void adde(int orderNumber,String err);

    List<GenerateErrorDto> findErrOrderNo(GenerateErrorDto indto);

    public void updDelFlag(int generateErrorId);

}
