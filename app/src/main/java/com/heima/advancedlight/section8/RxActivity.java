package com.heima.advancedlight.section8;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.heima.advancedlight.R;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RxActivity extends AppCompatActivity {
    private static final String TAG = "HMXJ";
    @BindView(R.id.bt_test1)
    Button btTest1;
    @BindView(R.id.bt_test2)
    Button btTest2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_test);
        ButterKnife.bind(this);
    }

    private  void test(){
        //https://www.jianshu.com/p/823252f110b0
        //1.定时发送
        Observable.interval(3, TimeUnit.SECONDS).subscribe(System.out::println);
        //2.从5开始的连续10个整数
        Observable.range(5, 10).subscribe(i -> System.out.println("1: " + i));
        //3.create
        Observable.create((ObservableOnSubscribe<Integer>) observableEmitter -> {
            observableEmitter.onNext(1);
            observableEmitter.onNext(2);
            observableEmitter.onComplete();
        }).subscribe(System.out::println);//加入Observer
        //4.defer 到有观察者订阅时才创建Observable，并且为每个观察者创建一个新的Observable
        Observable<Long> observable = Observable.defer(
                (Callable<ObservableSource<Long>>) () -> Observable.just(System.currentTimeMillis()));
        observable.subscribe(System.out::print);
        System.out.println();
        observable.subscribe(System.out::print);
        // 5. empty never error
        Observable.empty().subscribe(i->System.out.print("next"),i->System.out.print("error"),()->System.out.print("complete"));
        Observable.never().subscribe(i->System.out.print("next"),i->System.out.print("error"),()->System.out.print("complete"));
        Observable.error(new Exception()).subscribe(i->System.out.print("next"),i->System.out.print("error"),()->System.out.print("complete"));

    }


    private void test1() {
        // 1.初始化Observable 被观察者
        Observable.create((ObservableOnSubscribe<Integer>) emitter -> {//回调发射器
           // new ObservableOnSubscribe<Integer>(){
            Log.e(TAG, "Observable emit 1" + ":" + emitter.isDisposed());
            emitter.onNext(1);
            Log.e(TAG, "Observable emit 2" + ":" + emitter.isDisposed());
            emitter.onNext(2);
            Log.e(TAG, "Observable emit 3" + ":" + emitter.isDisposed());
            emitter.onNext(3);
            Log.e(TAG, "Observable emit 4" + ":" + emitter.isDisposed());
            emitter.onNext(4);
            Log.e(TAG, "Observable emit 5" + ":" + emitter.isDisposed());
            emitter.onComplete();
        }).subscribe(new Observer<Integer>() {// 3. 观察者订阅
                    // 2.初始化Observer
                    private Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.e(TAG, "onNext:" + integer);
                        if (integer == 2) {
                            disposable.dispose();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError : value : " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete");
                    }
                });
    }

    /**
     * 简单订阅
     */
    private void test2(){
        Observable.create((ObservableOnSubscribe<Integer>) e -> {
            Log.e(TAG, "Observable thread is : " + Thread.currentThread().getName());
            e.onNext(2);
            e.onComplete();
        }).subscribeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.io())//发射线程 subscribe() 时所发生的线程
                .observeOn(AndroidSchedulers.mainThread())//接收线程 订阅者接收 Observer 回调发生的线程
                //.doOnNext(new Consumer<Integer>() {
                .doOnNext(integer -> Log.e(TAG, integer+"\t"+"After observeOn(mainThread)，Current thread is " + Thread.currentThread().getName()))
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())//多次调用-第一次生效
                //.subscribe(new Consumer<Integer>() { //Consumer消费者，用于接收单个值，BiConsumer 则是接收两个值
                .subscribe(integer -> Log.e(TAG, integer+"\t"+"After observeOn(io)，Current thread is " + Thread.currentThread().getName()));

    }


    @OnClick({R.id.bt_test1, R.id.bt_test2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_test1:
                test1();
                break;
            case R.id.bt_test2:
                test2();
                break;
        }
    }
}
