package st.rpc.common.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * RPC调用过程中的错误信息
 * @author sutian
 * @Date 2020/8/27
 */


@AllArgsConstructor
@Getter
public enum RpcError {

    SERVICE_INVOCATION_FAILURE("服务调用出现失败"),
    SERVICE_NOT_FOUND("找不到对应的服务"),
    SERVICE_NOT_IMPLEMENT_ANY_INTERFACE("服务未继承任何借口"),
    UNKNOWN_PROTOCOL("未知的协议"),
    UNKNOWN_PACKAGE_TYPE("未知的数据包类型"),
    UNKNOWN_SERIALIZER("未知的序列化");

    private final String message;

}
