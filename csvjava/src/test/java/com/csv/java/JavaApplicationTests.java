package com.csv.java;

import com.csv.java.dao.CustomDao;
import com.csv.java.dao.PTypeDao;
import com.csv.java.dao.PlatformInfoDao;
import com.csv.java.entity.PlatformInfoDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JavaApplicationTests {
    @Resource
    private CustomDao customDao;

    @Resource
    private PlatformInfoDao platformInfoDao;

    @Test
    public void contextLoads() {
        String sql = "SELECT\n" +
                "            PLATFORM_ID platformId,\n" +
                "            PLATFORM_NM platformNm,\n" +
                "            PLATFORM_TYPE platformType,\n" +
                "            MEMO memo\n" +
                "        FROM T_PLATFORM_INFO\n" ;

        List<Map<String,Object>> list = customDao.customSelect(sql);for (Map<String,Object> a: list
             ) {
            for (String s : a.keySet()) {
                System.out.print(s+":" + a.get(s).toString()+",");
            }
            System.out.println();
        }


        PlatformInfoDto platformInfoDto = new PlatformInfoDto();
        platformInfoDto.setMemo("w");
        platformInfoDto.setPlatformNm("苏宁");
        platformInfoDto.setPlatformType("1");
        platformInfoDao.insertPlatformInfo(platformInfoDto);

        System.out.println(platformInfoDto.toString());

    }

}
