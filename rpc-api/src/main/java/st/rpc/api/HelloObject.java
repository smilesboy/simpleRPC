package st.rpc.api;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;


/**
 * @author sutian
 * @Date 2020/8/17
 */


@Data
@AllArgsConstructor
public class HelloObject implements Serializable {

    private Integer id;
    private String message;

}
