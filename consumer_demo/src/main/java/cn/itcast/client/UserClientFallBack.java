package cn.itcast.client;

import org.springframework.stereotype.Component;

/**
 * @Author: 萧一旬
 * @Description:
 * @Date: Create in 16:16 2019/4/12
 */
@Component
public class UserClientFallBack implements UserClient {
    @Override
    public String query(int id) {
        return "请求失败";
    }
}
