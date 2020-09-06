package st.rpc.common.enumeration;


import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * 网络中数据包的类型（请求和回应）
 * @author sutian
 * @Date 2020/9/1
 */
@AllArgsConstructor
@Getter
public enum  PackageType {

    REQUEST_PACK(0),
    RESPONSE_PACK(1);

    private final int code;
}
