package st.rpc.core.provider;


/**
 * 保存和提供服务实例对象
 * @author sutian
 * @Date 2020/9/7
 */
public interface ServiceProvider {

    <T> void addServiceProvider(T service, Class<T> serviceClass);

    Object getServiceProvider(String serviceName);

}
