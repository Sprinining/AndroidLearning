package com.example.rxjava2;

import io.reactivex.Observable;

public class LoginEngine {

    public static Observable<ResponseResult> login(String name, String pwd){
        ResponseResult responseResult = new ResponseResult();

        if("wmj".equals(name) && "666".equals(pwd)){
            // 成功Bean
            SuccessBean successBean = new SuccessBean();
            successBean.setId(11111);
            successBean.setName("wmj登录成功");

            responseResult.setData(successBean);
            responseResult.setCode(200);
            responseResult.setMessage("登录成功");
        }else{
            responseResult.setData(null);
            responseResult.setCode(404);
            responseResult.setMessage("登录失败");
        }

        // 返回被观察者
        return Observable.just(responseResult);
    }
}
