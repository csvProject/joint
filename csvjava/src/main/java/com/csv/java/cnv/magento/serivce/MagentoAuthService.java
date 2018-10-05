package com.csv.java.cnv.magento.serivce;






import com.csv.java.cnv.magento.model.ApiTokenInDto;
import com.csv.java.cnv.magento.model.RequestTokenOutDto;

public interface MagentoAuthService {
    public RequestTokenOutDto getRequestToken(ApiTokenInDto apiTokenInDto);

    public RequestTokenOutDto getAcessToken(ApiTokenInDto apiTokenInDto);
}
