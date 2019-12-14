package com.ewiderbuy.produce.common.tool;

import java.util.HashMap;
import java.util.Map;

public class MYSQLEncoder {

    private static Map<String,String> referencesMap = new HashMap<String,String>();

    static{
        referencesMap.put("'","\\'");
        referencesMap.put("\"","\\\"");
        referencesMap.put("\\","\\\\");

        referencesMap.put(" ","\\ ");
        referencesMap.put("\0","\\\0");
        referencesMap.put("\b","\\\b");
        referencesMap.put(" ","\\ ");
        referencesMap.put("\t","\\\t");
        referencesMap.put("\f","\\\f");
    }

    //escape sql tag with the source code.
    public static String encode(String source) {
        if (source == null)
            return "";

        StringBuffer sbuffer = new StringBuffer(source.length());

        for (int i = 0; i < source.length(); i++) {
            String c = source.substring(i,i+1);

            //System.out.println("c=" + c);
            //System.out.println(referencesMap.get(c));

            if (referencesMap.get(c) != null) {
                sbuffer.append(referencesMap.get(c));
            } else {
                sbuffer.append(c);
            }
        }
        return sbuffer.toString();
    }

  /*  public static void main(String[] args){
        String tt = encode("They'are \"ok\". \\a");
        System.out.print(tt);
    }*/
}
