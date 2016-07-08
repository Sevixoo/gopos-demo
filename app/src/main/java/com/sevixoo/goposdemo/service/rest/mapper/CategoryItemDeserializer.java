package com.sevixoo.goposdemo.service.rest.mapper;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.sevixoo.goposdemo.domain.entity.CategoryItem;
import com.sevixoo.goposdemo.service.rest.pojo.CategoryItemsResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Seweryn on 2016-07-08.
 */
public class CategoryItemDeserializer implements JsonDeserializer<CategoryItemsResponse> {

    @Override
    public CategoryItemsResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        CategoryItemsResponse items = new CategoryItemsResponse();

        JsonArray array = json.getAsJsonObject().get("rows").getAsJsonArray();
        items.items = new ArrayList<>();

        for(int i = 0 ; i < array.size() ; i++){
            JsonElement el = array.get(i);

            String name = el.getAsJsonObject().get("name").getAsString();
            String color = el.getAsJsonObject().get("color").getAsString();
            int id = el.getAsJsonObject().get("id").getAsInt();
            String path = "";
            if( el.getAsJsonObject().get("image").isJsonNull() ){
                path = "";
            }else{
                path = el.getAsJsonObject().get("image").getAsJsonObject().get("filePath").getAsString();
            }


            CategoryItem item = new CategoryItem();
            item.setName(name);
            item.setRemoteId(id);
            item.setImagePath(path);
            items.items.add(item);
        }
        return items;
    }
}
