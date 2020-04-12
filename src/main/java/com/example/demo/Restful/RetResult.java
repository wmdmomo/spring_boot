package com.example.demo.Restful;

public class RetResult {
    private Integer code;
    private String msg;
    private Object data;
    public RetResult(){
        this.code=Integer.valueOf(0);
        this.msg="";
        this.data=null;
    }
    public RetResult(Integer code,String msg,Object data){
        this.code=code;
        this.msg="";
        this.data=null;
    }
    public RetResult(Integer code,String msg){
        this.code=code;
        this.msg=msg;
        this.data=null;
    }
    public RetResult(Integer code,Object data){
        this.code=code;
        this.msg="";
        this.data=data;
    }
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}
