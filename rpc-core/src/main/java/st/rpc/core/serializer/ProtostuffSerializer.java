package st.rpc.core.serializer;


import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import st.rpc.common.enumeration.SerializerCode;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Protostuff序列化器
 * @author sutian
 * @Date 2020/9/6
 */
public class ProtostuffSerializer implements CommonSerializer {

    private static final Logger logger = LoggerFactory.getLogger(ProtostuffSerializer.class);

    private static Map<Class<?>, Schema<?>> cachedSchema = new ConcurrentHashMap<>();
    private static Objenesis objenesis = new ObjenesisStd(true);

    @SuppressWarnings("unchecked")
    private static <T> Schema<T> getSchema(Class<T> cls) {
        Schema<T> schema = (Schema<T>) cachedSchema.get(cls);
        if (schema == null) {
            schema = RuntimeSchema.createFrom(cls);
            cachedSchema.put(cls, schema);
        }
        return schema;
    }

    @Override
    @SuppressWarnings("unchecked")
    public byte[] serializer(Object obj) {
        Class cls = (Class) obj.getClass();
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            Schema schema = getSchema(cls);
            return ProtostuffIOUtil.toByteArray(obj, schema, buffer);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        } finally {
            buffer.clear();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object deserializer(byte[] bytes, Class<?> clazz) {
        try {
            Object message = objenesis.newInstance(clazz);
            Schema schema = getSchema(clazz);
            ProtostuffIOUtil.mergeFrom(bytes, message, schema);
            return message;
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    @Override
    public int getCode() {
        return SerializerCode.valueOf("PROTOSTUFF").getCode();
    }
}
