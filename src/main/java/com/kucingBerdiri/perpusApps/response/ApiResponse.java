package com.kucingBerdiri.perpusApps.response;

public class ApiResponse<T> {

    private boolean status;
    private T data;

    public ApiResponse() {
    }

    public ApiResponse(boolean status, T data) {
        this.status = status;
        this.data = data;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, data);
    }

    public static <T> ApiResponse<T> fail(T data) {
        return new ApiResponse<>(false, data);
    }

    public boolean isStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setData(T data) {
        this.data = data;
    }
}
