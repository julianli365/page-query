package com.julian.page.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 响应结果集
 *
 * @param <T>
 * @author lijs
 * @since 2025-1-9 15:18:48
 */
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -1491079685346295891L;

    public static final String SUCCESS = "0000";
    public static final String FAIL = "9999";

    public static final String SUCCESS_MSG = "success";

    private String code;
    private String msg;
    private T body;

    protected Result() {
        code = SUCCESS;
        msg = SUCCESS_MSG;
    }

    protected Result(String message) {
        code = FAIL;
        this.msg = message;
        this.body = null;
    }

    protected Result(String code, String message) {
        this.code = code;
        this.msg = message;
    }

    protected Result(T data) {
        this();
        this.body = data;
    }

    public static Result success() {
        return new Result<>();
    }

    public static <T> Result success(String msg) {
        return new Result(SUCCESS, msg);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(data);
    }

    public static Result fail() {
        return new Result(FAIL, null);
    }

    public static Result fail(String message) {
        return new Result(message);
    }

    public static Result fail(String code, String message) {
        return new Result(code, message);
    }

}
