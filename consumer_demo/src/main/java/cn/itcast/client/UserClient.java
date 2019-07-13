package cn.itcast.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: 萧一旬
 * @Description:
 * @Date: Create in 17:12 2019/4/11
 */
@FeignClient(value = "user-service",fallback = UserClientFallBack.class)
public interface UserClient  {

    // localhost:port/user/null
    @GetMapping("/user/{id}")
    String query(@PathVariable("id") int id);
}
