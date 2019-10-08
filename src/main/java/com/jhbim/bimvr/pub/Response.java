package com.jhbim.bimvr.pub;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.github.pagehelper.util.StringUtil;
import com.jhbim.bimvr.stauts.enums.StatusEnum;


/**
 * 功能：定义各种通用的常量值、保存各种参数便于前后台交互的各个环节使用 1、保存各工作环境的各种变量值包括：年度、单位、账套、期间、登陆人、业务日期等
 * 2、定义各种通用变量值
 *
 * @author wsh
 */
public class Response implements Serializable {

    private static final long serialVersionUID = 47067542629985123L;

    public final static String SUCCESS_CODE = "200";
    
    /**
     * 业务处理成功，超出了license使用限制
     */
    public final static String LICENSE_VALIDATE_FAIL_CODE = "201";

    public final static String BATCH_PARTIAL_SUCCESS_CODE = "206";
    /**
     * 多条数据批处理，业务处理部分数据处理成功，部分业务处理失败；
     */

    public final static String BATCH_WHOLE_FAIL_CODE = "207";
    /**
     * 连接成功，多条数据批处理，业务处理全部失败；
     */

    public final static String BUSINESS_FAIL_CODE = "400";

    public final static String FAIL_CODE = "500";
    
    public final static String LICENSE_FAIL_CODE = "501";
    /**
     * 业务标记码-需要前台再次确认情形
     */
    public static final String STATUS_BIZ_NEED_CONFIRM = "409";

    private String code;

    private String msg;

    private Object data;

    public Response() {
        super();
    }

    public String getCode() {
        return code;
    }

    public Response setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Response setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Response setData(Object data) {
        this.data = data;
        return this;
    }

    @JsonProperty(access = Access.WRITE_ONLY)
    public boolean isSuccess() {
        return this.code.startsWith("2");
    }

    public static Response success() {
        return new SuccessResponse();
    }

    public static Response fail() {
        return new FailResponse();
    }

    public static Response businessFailResponse() {
        return new BusinessFailResponse();
    }

    public static Response businessFailResponse(String msg) {
        return new BusinessFailResponse(msg);
    }

    public static Response businessFailResponse(String errCode, String errMsg) {
        return new BusinessFailResponse(errCode, errMsg);
    }

    public static Response commonResponse(StatusEnum statusEnum) {
        return new CommonResponse(statusEnum);
    }

    public static Response commonResponse(StatusEnum statusEnum, String msg) {
        return new CommonResponse(statusEnum, msg);
    }

    public static Response commonResponse(String code, String msg) {
        return new CommonResponse(code, msg);
    }

    public static Response commonResponse(String msg) {
        return new CommonResponse(msg);
    }

    /**
     * fail
     */
    public static class FailResponse extends Response {
        private static final long serialVersionUID = -2485130096025044970L;

        public FailResponse() {
            super();
            this.setCode(FAIL_CODE);
            this.setMsg("fail");
        }
    }

    /**
     * success
     *
     * @author liubo
     */
    public static class SuccessResponse extends Response {
        private static final long serialVersionUID = -2395996558104816956L;

        public SuccessResponse() {
            super();
            this.setCode(SUCCESS_CODE);
            this.setMsg("成功");
        }
    }
    
    public static class BusinessFailResponse extends Response {
        private static final long serialVersionUID = -5238070512611403402L;

        public BusinessFailResponse() {
            super();
            this.setCode(BUSINESS_FAIL_CODE);
            this.setMsg("业务处理失败");
        }

        public BusinessFailResponse(String code, String msg) {
            super();
            this.setCode(code);
            this.setMsg(msg);
        }

        public BusinessFailResponse(String errMsg) {
            super();
            this.setCode(BUSINESS_FAIL_CODE);
            this.setMsg(errMsg);
        }
    }

    public static Response commonResponse(boolean status) {
        return commonResponse(status, null, null);
    }

    public static Response commonResponse(boolean status, String msg) {
        return commonResponse(status, msg, null);
    }


    public static Response commonResponse(boolean status, String msg, Object data) {
        Response res = Response.success();
        if (status == false) res = Response.fail();
        if (StringUtil.isNotEmpty(msg)) res.setMsg(msg);
        if (null != data) res.setData(data);
        return res;
    }

    public static class CommonResponse extends Response {
        private static final long serialVersionUID = -687461638087689607L;

        public CommonResponse(StatusEnum statusEnum) {
            super();
            this.setCode(statusEnum.getCode());
            this.setMsg(statusEnum.getMsg());
            this.setData("");
        }

        public CommonResponse(String code, String msg) {
            super();
            this.setCode(code);
            this.setMsg(msg);
            this.setData("");
        }

        public CommonResponse(String msg) {
            super();
            this.setCode(BUSINESS_FAIL_CODE);
            this.setMsg(msg);
            this.setData("");
        }

        public CommonResponse(StatusEnum statusEnum, String msg) {
            super();
            this.setCode(statusEnum.getCode());
            this.setMsg(statusEnum.getMsg() + ":" + msg);
            this.setData("");
        }
    }
}
