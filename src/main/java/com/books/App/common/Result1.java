package com.books.App.common;


public class Result1<T>{
    private String code;
    private String msg;
    private T data;

    public String getCode(){
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Result1(){}

    public Result1(T data){this.data=data;}

    public static Result1 success(){
        Result1 result=new Result1<>();
        result.setCode("0");
        result.setMsg("成功");
        return result;
    }

    public static <T> Result1<T> success(T data){

        Result1<T> result=new Result1<>(data);
        result.setCode("0");
        result.setMsg("成功");
        return result;
    }

    public static Result1 error(String code, String msg){
        Result1 result=new Result1();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }


}
