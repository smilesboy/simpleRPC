package st.rpc.core.serializer;

/**
 * 通用的序列化反序列化接口
 * @author sutian
 * @Date 2020/9/1
 */

public interface CommonSerializer {

    Integer KRYO_SERIALIZER = 0;
    Integer JSON_SERIALIZER = 1;
    Integer PROTOBUF_SERIALIZER = 2;
    //Integer HESSIAN_SERIALIZER = 2;

    Integer DEFAULT_SERIALIZER = KRYO_SERIALIZER;

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
