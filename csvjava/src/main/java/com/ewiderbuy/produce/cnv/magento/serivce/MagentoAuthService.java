package com.ewiderbuy.produce.cnv.magento.serivce;






import com.ewiderbuy.produce.cnv.magento.model.ApiTokenInDto;
import com.ewiderbuy.produce.cnv.magento.model.RequestTokenOutDto;

public interface MagentoAuthService {
    public RequestTokenOutDto getRequestToken(ApiTokenInDto apiTokenInDto);

    public RequestTokenOutDto getAcessToken(ApiTokenInDto apiTokenInDto);
}
