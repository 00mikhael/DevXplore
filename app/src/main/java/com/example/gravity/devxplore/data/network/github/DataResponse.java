package com.example.gravity.devxplore.data.network.github;

import java.util.List;

/**
 * Created by gravity on 8/28/17.
 */

public class DataResponse<T> {
    private List<T> dataList;
    private T data;
    private Throwable error;

    public DataResponse(List<T> dataList) {
        this.dataList = dataList;
        this.error = null;
    }

    public DataResponse(T data) {
        this.data = data;
        this.error = null;
    }

    public DataResponse(Throwable error) {
        this.error = error;
        this.data = null;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public T getData() {
        return data;
    }

    public Throwable getError() {
        return error;
    }
}
