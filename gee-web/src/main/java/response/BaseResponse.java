package response;

import io.swagger.annotations.*;

/**
 * @Description 响应数据
 * @Author  qiuzx
 * @Date   2019-09-03 09:42
 */
@ApiModel(value = "请求响应对象")
public class BaseResponse<T> {
    @ApiModelProperty("请求响应码")
    private Integer code; //状态码code
    @ApiModelProperty("状态码对应的描述信息")
    private String msg;  //状态码对应的描述信息msg
    @ApiModelProperty("请求响应数据")
    private T data; //响应数据
    public BaseResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public BaseResponse(StatusCode statusCode) {
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
    }
    public BaseResponse(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
}
