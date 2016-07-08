package com.sevixoo.goposdemo.domain.interactor;

import android.util.Log;

import com.sevixoo.goposdemo.data.mapper.CategoryItemMapper;
import com.sevixoo.goposdemo.domain.Interactor;
import com.sevixoo.goposdemo.domain.entity.CategoryItem;
import com.sevixoo.goposdemo.domain.entity.CategoryListItem;
import com.sevixoo.goposdemo.domain.entity.SignUpCredentials;
import com.sevixoo.goposdemo.domain.executor.PostExecutionThread;
import com.sevixoo.goposdemo.domain.executor.ThreadExecutor;
import com.sevixoo.goposdemo.domain.repository.ICategoriesRepository;
import com.sevixoo.goposdemo.domain.service.IAuthenticateService;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Seweryn on 2016-07-06.
 */
public class LoadCategoriesInteractor extends Interactor {

    private ICategoriesRepository   mCategoriesRepository;
    private int                     mOffset;
    private int                     mLimit;
    private CategoryItemMapper      mMapper;

    public LoadCategoriesInteractor(ICategoriesRepository mCategoriesRepository , ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.mCategoriesRepository = mCategoriesRepository;
        mMapper = new CategoryItemMapper();
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return Observable.create(new Observable.OnSubscribe<List<CategoryListItem>>() {
            @Override
            public void call(Subscriber<? super List<CategoryListItem>> subscriber) {
                try{
                    List<CategoryItem> data = mCategoriesRepository.list(mOffset,mLimit);
                    List<CategoryListItem> items = new ArrayList<CategoryListItem>();
                    Log.e("LoadCategoriesInterac " , "data"+ data.size() );
                    for(CategoryItem item : data){
                        items.add( mMapper.transformToListItem(item) );
                    }
                    subscriber.onNext(items);
                    subscriber.onCompleted();
                }catch (Exception e){
                    subscriber.onError(e);
                }
            }
        });
    }

    public void execute(int offset , int limit , Subscriber UseCaseSubscriber) {
        mOffset = offset;
        mLimit = limit;
        super.execute(UseCaseSubscriber);
    }
}
