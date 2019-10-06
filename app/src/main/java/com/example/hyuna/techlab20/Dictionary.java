package com.example.hyuna.techlab20;

public class Dictionary {
    private String date;
    private String purpose;
    private String amount;
    private String result;

    public void setDate(String dt){
        date = dt;
    }

    public void setPurpose(String ppe){
        purpose = ppe;
    }

    public void setAmount(String amt){
        amount=amt;
    }

    public void setResult(String rt){
        result=rt;
    }

    public String getDate(){
        return date;
    }

    public String getResult(){ return result; }

    public String getPurpose(){
        return purpose;
    }

    public String getAmount(){
        return amount;
    }

    public Dictionary(String date, String purpose, String amount, String result){
        this.date = date;
        this.purpose = purpose;
        this.amount = amount;
        this.result = result;
    }
}
