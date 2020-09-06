package st.rpc.common.entity;

import lombok.Data;
import st.rpc.common.enumeration.ResponseCode;

import java.io.Serializable;

/**
 * 服务端执行完成或者出错向客户端返回结果对象
 * @author sutian
 * @Date 2020/8/19
 */

@Data
public class RpcResponse<T> implements Serializable {

    public RpcResponse() {}

    /**
     * 响应状态码
     */
    private Integer statusCode;

    /**
     * 响应状态补充信息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    public static <T> RpcResponse<T> success(T data) {
        RpcResponse<T> response = new RpcResponse<>();
        response.setStatusCode(ResponseCode.SUCCESS.getCode());
        response.setData(data);
        return response;
    }

    public static <T> RpcResponse<T> fail(ResponseCode code) {
        RpcResponse<T> response = new RpcResponse<>();
        response.setStatusCode(code.getCode());
        response.setMessage(code.getMessage());
        return response;
    }

}
