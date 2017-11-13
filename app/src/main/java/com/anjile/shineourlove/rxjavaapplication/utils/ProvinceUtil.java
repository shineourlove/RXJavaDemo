package com.anjile.shineourlove.rxjavaapplication.utils;

import android.app.Activity;

import com.anjile.shineourlove.rxjavaapplication.R;
import com.anjile.shineourlove.rxjavaapplication.entity.ProvinceEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/11/13.
 */

public class ProvinceUtil {
    public ArrayList<ProvinceEntity> getProvinceList(Activity activity) {
        try {
            InputStream is = activity.getResources().openRawResource(R.raw.province);
            String text = readTextFromSDcard(is);
            ArrayList<ProvinceEntity> provinceList = new Gson().fromJson(text, new TypeToken<ArrayList<ProvinceEntity>>() {
            }.getType());
            return provinceList;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<ProvinceEntity>();
        }
    }

    private String readTextFromSDcard(InputStream is) throws Exception {
        InputStreamReader reader = new InputStreamReader(is);
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuffer buffer = new StringBuffer("");
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            buffer.append(str);
            buffer.append("\n");
        }
        return buffer.toString();
    }
}
