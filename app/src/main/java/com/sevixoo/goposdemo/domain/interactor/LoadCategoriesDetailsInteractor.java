package com.sevixoo.goposdemo.domain.interactor;

import android.util.Log;

import com.sevixoo.goposdemo.data.mapper.CategoryItemMapper;
import com.sevixoo.goposdemo.domain.Interactor;
import com.sevixoo.goposdemo.domain.entity.CategoryItem;
import com.sevixoo.goposdemo.domain.entity.CategoryListItem;
import com.sevixoo.goposdemo.domain.executor.PostExecutionThread;
import com.sevixoo.goposdemo.domain.executor.ThreadExecutor;
import com.sevixoo.goposdemo.domain.repository.ICategoriesRepository;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Seweryn on 2016-07-06.
 */
public class LoadCategoriesDetailsInteractor extends Interactor {

    private ICategoriesRepository   mCategoriesRepository;
    private long                    mID;
    private CategoryItemMapper      mMapper;

    public LoadCategoriesDetailsInteractor(ICategoriesRepository mCategoriesRepository , ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.mCategoriesRepository = mCategoriesRepository;
        mMapper = new CategoryItemMapper();
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return Observable.create(new Observable.OnSubscribe<CategoryListItem>() {
            @Override
            public void call(Subscriber<? super CategoryListItem> subscriber) {
                try{
                    Log.e("mID" , "" + mID);
                    CategoryItem data = mCategoriesRepository.load( mID );
                    subscriber.onNext(mMapper.transformToListItem(data));
                    subscriber.onCompleted();
                }catch (Exception e){
                    subscriber.onError(e);
                }
            }
        });
    }

    public void execute( long id , Subscriber UseCaseSubscriber) {
        mID = id;
        super.execute(UseCaseSubscriber);
    }
}
