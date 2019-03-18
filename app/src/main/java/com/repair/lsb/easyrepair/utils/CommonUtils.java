package com.repair.lsb.easyrepair.utils;

/**
 * Created by L'S'B 于 2019/1/28
 */

public class CommonUtils {
    public static String getBirth4Card(String idCard){
        if(idCard == null || "".equals(idCard)){
            return "" ;
        }
        return String.format("%s年%s月%s日",idCard.substring(6,10),idCard.substring(10,12),idCard.substring(12,14)) ;
    }
    public static String getSex4Card(String idCard){
        if(idCard == null || "".equals(idCard)){
            return "" ;
        }
        String sex = idCard.substring(16,17);
        if(Integer.parseInt(sex)/2 == 0){
            return "女" ;
        }else{
            return "男" ;
        }
    }



    public int stringToInteger(String value){
        return Integer.parseInt(value);
    }
}
