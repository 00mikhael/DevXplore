package com.example.gravity.devxplore.data.db;

import android.arch.persistence.room.util.StringUtil;

import java.util.Collections;
import java.util.List;

/**
 * Created by gravity on 8/26/17.
 */

public class TypeConverter {
    @android.arch.persistence.room.TypeConverter
    public static List<Integer> stringToIntList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }
        return StringUtil.splitToIntList(data);
    }

    @android.arch.persistence.room.TypeConverter
    public static String intListToString(List<Integer> ints) {
        return StringUtil.joinIntoString(ints);
    }
}
