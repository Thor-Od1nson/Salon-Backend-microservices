package com.vishal.payment_service.client;


import com.vishal.payment_service.payload.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "api-gateway", path = "/api/v1/user", contextId = "userServiceClient")
public interface UserServiceClient {

    @GetMapping("/email")
    UserDto findUserByEmail(@RequestParam("email") String email);
}
