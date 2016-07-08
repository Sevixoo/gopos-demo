package com.sevixoo.goposdemo.data.repository;

import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.sevixoo.goposdemo.data.GoPOSDatabaseHelper;
import com.sevixoo.goposdemo.data.entity.CategoryEntity;
import com.sevixoo.goposdemo.data.mapper.CategoryItemMapper;
import com.sevixoo.goposdemo.domain.entity.CategoryItem;
import com.sevixoo.goposdemo.domain.repository.ICategoriesRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Seweryn on 2016-07-08.
 */
public class CategoriesRepository implements ICategoriesRepository {

    GoPOSDatabaseHelper mGoPOSDatabaseHelper;
    Dao<CategoryEntity, Long> mDao;
    CategoryItemMapper mMapper;

    public CategoriesRepository(GoPOSDatabaseHelper mGoPOSDatabaseHelper) {
        this.mGoPOSDatabaseHelper = mGoPOSDatabaseHelper;
        mMapper = new CategoryItemMapper();
    }

    @Override
    public void insert(CategoryItem item)throws SQLException {
        mDao = mGoPOSDatabaseHelper.getDao();
        mDao.createOrUpdate( mMapper.transform(item) );
        Log.e( "insert" , "size" + mDao.countOf() );
    }

    @Override
    public CategoryItem load(long _id)throws SQLException {
        mDao = mGoPOSDatabaseHelper.getDao();
        CategoryEntity entity = mDao.queryBuilder().where().eq("_id",_id).queryForFirst();
        if(entity==null){
            return null;
        }
        return mMapper.transform(entity);
    }

    @Override
    public List<CategoryItem> list(int offset, int limit) throws SQLException{
        mDao = mGoPOSDatabaseHelper.getDao();
        List<CategoryItem> data = new ArrayList<>();
        List<CategoryEntity> entities = mDao.queryBuilder().limit((long)limit).offset((long)offset).query();
        if(entities==null)return null;
        for (CategoryEntity entity : entities){
            data.add(mMapper.transform(entity));
        }
        return data;
    }

    @Override
    public void delete(CategoryItem item)throws SQLException {
        mDao = mGoPOSDatabaseHelper.getDao();
        mDao.delete( mMapper.transform(item));
    }

    @Override
    public void update(CategoryItem item) throws SQLException{
        mDao = mGoPOSDatabaseHelper.getDao();
        mDao.createOrUpdate( mMapper.transform(item) );
    }

    @Override
    public boolean exists(CategoryItem item) throws SQLException {
        mDao = mGoPOSDatabaseHelper.getDao();
        return mDao.idExists(item.getID());
    }

    @Override
    public CategoryItem getByRemoteID(int remoteID) throws SQLException {
        mDao = mGoPOSDatabaseHelper.getDao();
        CategoryEntity data = mDao.queryBuilder().where().eq("remoteId",remoteID).queryForFirst();
        if(data==null){
            return null;
        }
        return mMapper.transform( data );
    }
}
