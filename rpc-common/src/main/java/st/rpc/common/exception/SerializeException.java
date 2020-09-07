package st.rpc.common.exception;


/**
 * 序列化异常
 * @author sutian
 * @Date 2020/9/6
 */
public class SerializeException extends RuntimeException {
    public SerializeException(String msg) {
        super(msg);
    }
}
