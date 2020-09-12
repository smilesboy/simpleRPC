package st.test.server;

import st.rpc.api.ByeService;
import st.rpc.core.annotation.Service;


/**
 * @author sutian
 * @Date 2020/9/12
 */
@Service
public class ByeServiceImpl implements ByeService {
    @Override
    public String bye(String name) {
        return "bye, " + name;
    }
}
