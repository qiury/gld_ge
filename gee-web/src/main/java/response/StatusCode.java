package response;

/**
 * @Description 请求状态响应
 * @Author  qiuzx
 * @Date   2019-09-03 09:41
 */
public enum StatusCode {
    Success(0,"成功"),
    Fail(-1,"失败"),
    InvalidParams(201,"非法的参数!"),
    UserNotLogin(202,"用户没登录");

    private Integer code; //状态码code
    private String msg;  //状态码描述信息msg

    StatusCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
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
}
