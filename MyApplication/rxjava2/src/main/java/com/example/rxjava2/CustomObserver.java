package com.example.rxjava2;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class CustomObserver implements Observer<ResponseResult> {

    public abstract void success(SuccessBean successBean);
    public abstract void error(String message);

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(ResponseResult responseResult) {
        if(responseResult.getData() == null){
            error(responseResult.getMessage() + "请求失败");
        }else{
            success(responseResult.getData());
        }
    }

    @Override
    public void onError(Throwable e) {
        error(e.getMessage() + "错误详情");
    }

    @Override
    public void onComplete() {

    }
}
