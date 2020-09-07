package st.rpc.core.serializer;

/**
 * 通用的序列化反序列化接口
 * @author sutian
 * @Date 2020/9/1
 */

public interface CommonSerializer {

    byte[] serializer(Object obj);

    Object deserializer(byte[] bytes, Class<?> clazz);

    int getCode();

    static CommonSerializer getByCode(int code) {
        switch (code) {
            case 0:
                return new KryoSerializer();
            case 1:
                return new JsonSerializer();
            case 2:
                return new ProtostuffSerializer();
            default:
                return  null;
        }
    }

}
