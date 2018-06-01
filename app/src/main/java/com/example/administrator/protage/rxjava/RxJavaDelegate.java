package com.example.administrator.protage.rxjava;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.administrator.protage.R;
import com.example.administrator.protage.util.L;
import com.example.fox_core.fragment.LatteDelegate;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by alan on 2018/6/1.
 * @author alan 
 */

public class RxJavaDelegate extends LatteDelegate {

    private Button mBtnClick;
    private ImageView mIvShow;


    @Override
    public Object getLayout() {
        return R.layout.delegate_re_java;
    }

    @Override
    public void onBindView(View rootView) throws Exception {

        //绑定View
        mBtnClick = rootView.findViewById(R.id.bt_image);
        mIvShow = rootView.findViewById(R.id.iv_show);
        test();

        initEvents();

    }

    private void initEvents() {
        mBtnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changImageView();
            }
        });
    }

    private void changImageView() {
        final int id = R.drawable.a01;

        Observable.create(new Observable.OnSubscribe<Drawable>() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                Drawable drawable = getContext().getTheme().getDrawable(id);
                subscriber.onNext(drawable);
                subscriber.onCompleted();
            }
        })
                // 指定 Subscriber 的回调发生在主线程
                .subscribeOn(AndroidSchedulers.mainThread())
                // 指定 subscribe() 发生在 IO 线程
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<Drawable>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Drawable drawable) {
                        mIvShow.setImageDrawable(drawable);
                    }
                });
    }

    private void test() {
        //创建被观察者
        L.e("start");
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("start");
                subscriber.onCompleted();
            }
        });

        //创建观察者
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                L.e("=====" + s);
            }

            @Override
            public void onStart() {
                super.onStart();
                L.e("start");
            }
        };

        //订阅事件
        observable.subscribe(subscriber);

        //线程控制
        Observable.just(1, 2, 3, 4)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer integer) {

                    }
                });
    }
}
