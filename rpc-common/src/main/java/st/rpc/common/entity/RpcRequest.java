package st.rpc.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.io.Serializable;


/**
 * 客户端向服务端发送的请求对象
 * @author sutian
 * @Date 2020/8/19
 */

@Data
@AllArgsConstructor
public class RpcRequest implements Serializable {

    public RpcRequest() {}

    /**
     * 待调用接口名称
     */
    private String interfaceName;

    /**
     * 待调用的方法名称
     */
    private String methodName;

    /**
     * 调用方法的参数
     */
    private Object[] parameters;

    /**
     * 由于方法可能会出现重载，所以这里要指明参数类型
     */
    private Class<?>[] paramTypes;

}
