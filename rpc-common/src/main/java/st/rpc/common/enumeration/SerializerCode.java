package st.rpc.common.enumeration;


import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * 字节流中标识序列化和反序列化器
 * @author sutian
 * @Date 2020/9/1
 */
@AllArgsConstructor
@Getter
public enum SerializerCode {

    KRYO(0),
    JSON(1),
    PROTOSTUFF(2);

    private final int code;

}
