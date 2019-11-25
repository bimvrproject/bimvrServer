package com.jhbim.bimvr.stauts.enums;


/**
 * 请求处理状态枚举
 *
 * @author wsh
 */
public enum StatusEnum {

    SUCCESS("200", "请求处理成功"),
    FAIL("400", "请求处理失败"),
    ERROR("500", "请求处理错误"),

    SAVE_SUCCESS("200", "数据保存成功"),
    ADD_SUCCESS("200", "数据添加成功"),
    AUTH_SUCCESS("200","授权成功"),
    CANCEL_SUCCESS("200","取消授权成功"),
    UPDATE_SUCCESS("200", "数据更新成功"),
    DELETE_SUCCESS("200", "数据删除成功"),
    SELECT_SUCCESS("200", "数据查询成功"),

    SAVE_FAILURE("400", "数据保存失败"),
    ADD_FAILURE("400", "数据添加失败"),
    AUTH_FAILURE("400","授权失败"),
    CANCEL_FAILURE("400","取消授权失败"),
    UPDATE_FAILURE("400", "数据更新失败"),
    DELETE_FAILURE("400", "数据删除失败"),
    SELECT_FAILURE("400", "数据查询失败"),


    INVALID_PARAM("400", "非法请求");

    private String code;
    private String msg;

    StatusEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
