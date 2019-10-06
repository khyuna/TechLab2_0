package com.example.hyuna.techlab20;

import java.text.DecimalFormat;

public class DataUtil {

    public static String amountFormatter(int amount){
        //금액 포맷터
        String amountFormat =null;

        DecimalFormat format = new DecimalFormat("###,###");
        amountFormat = format.format(amount)+"원";
        return amountFormat;
    }

    public static String getResultKo(String result){
        //수입 지출 나누기
        String result_ko=null;
        try{
            if(result.equals("MINUS")){
                result_ko="지출";
            }else{
                result_ko="수입";
            }
        }catch (Exception e){

        }
        return result_ko;
    }

    public static String getPurposeKo(String purpose){
        String purpose_ko=null;

        try{
            switch (purpose){
                case "eat":
                    purpose_ko="식비";
                    break;
                case "culture":
                    purpose_ko="문화비";
                    break;
                case "hobby":
                    purpose_ko="취미";
                    break;
                case "auto":
                    purpose_ko="자동이체";
                    break;
                case "fee":
                    purpose_ko="공과금";
                    break;
                case "extra":
                    purpose_ko="기타";
                    break;
            }
        }catch(Exception e){

        }
        return purpose_ko;
    }

    public static String getDataFormat(String date){
        //2019.09.20과같은 형식
        String dateFormat;
        
        String year = date.substring(0,3);
        String month = date.substring(4,5);
        String day = date.substring(6,7);
        dateFormat = year+"."+month+"."+day;

        return dateFormat;
    }
}
