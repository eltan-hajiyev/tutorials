package com.github.elten400.tutorials.feignclientloadbalance;

import com.github.elten400.tutorials.feignclientloadbalance.feign.ApiFeignClient;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class FeignClientLoadBalanceApplicationTests {
    @Autowired
    private ApiFeignClient feignClient;
	@SpyBean
	private LoadBalancerClient loadBalancerClient;

    @Captor
    ArgumentCaptor<ServiceInstance> instanceArgumentCaptor;
    @Captor
    ArgumentCaptor<URI> originalArgumentCaptor;

    @Test
    void checkLoadBalanceFeignClient() throws Exception {
        try {
            feignClient.info();
        } catch (Throwable t) {
        }
		try {
			feignClient.info();
		} catch (Throwable t) {
		}

        verify(loadBalancerClient, times(2))
				.reconstructURI(instanceArgumentCaptor.capture(), originalArgumentCaptor.capture());

		List<String> hostList = instanceArgumentCaptor.getAllValues().stream()
				.map(m -> m.getHost()).collect(Collectors.toList());

		assertThat(hostList).contains("service1", "service2");
    }

}
