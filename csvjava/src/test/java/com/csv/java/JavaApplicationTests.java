package com.csv.java;

import com.csv.java.common.tool.StringFormatForSQL;
import com.csv.java.dao.CustomDao;
import com.csv.java.dao.PTypeDao;
import com.csv.java.dao.PlatformInfoDao;
import com.csv.java.entity.CsvTemplateDetailDto;
import com.csv.java.entity.PlatformInfoDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.*;

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

        List<LinkedHashMap<String,Object>> list = customDao.customSelect(sql);for (Map<String,Object> a: list
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

    @Test
    public void sqlSrc(){
        String sql = "";
        List<CsvTemplateDetailDto> list = new ArrayList<>();
        CsvTemplateDetailDto csv = new CsvTemplateDetailDto();
        csv.setFieldKey("name");
        csv.setFieldValue("wkm name：${:setCn}");
        CsvTemplateDetailDto csv2 = new CsvTemplateDetailDto();
        csv2.setFieldKey("age");
        csv2.setFieldValue("wkm age：{:INFO_ID}");
        CsvTemplateDetailDto csv3 = new CsvTemplateDetailDto();
        csv3.setFieldKey("sex");
        csv3.setFieldValue("${:pm_cn}");
        list.add(csv);
        list.add(csv2);
        list.add(csv3);

        Map<String,String> arguments = new HashMap<String, String>(){{
            put("setCn", "set_cn");
            put("pm_cn", "pm_cn");
        }};

        sql = StringFormatForSQL.fieldListFormat(list,arguments);
        System.out.println(sql);
        List<LinkedHashMap<String,Object>> list2 = customDao.customSelect(sql);
        for (Map<String,Object> a: list2
                ) {
            for (String s : a.keySet()) {
                System.out.print(s+":" + a.get(s).toString()+",");
            }
            System.out.println();
        }
    }

}
