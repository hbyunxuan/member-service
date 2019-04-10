package com.kykj.tesla.member.Service;

import com.kykj.tesla.application.service.ApplicationAppletWeixinInterface;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "application-service")
public interface ApplicationAppletWeixinService extends ApplicationAppletWeixinInterface {
}
