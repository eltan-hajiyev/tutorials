package com.github.elten400.tutorials.aoptransactional;

import com.github.elten400.tools.ThreadExecutorInfo;
import com.github.elten400.tools.ThreadPoolExecutorForInfo;
import com.github.elten400.tutorials.aoptransactional.service.StudentService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.Duration;

import static com.github.elten400.tutorials.aoptransactional.config.AspectConfig.AOP_IMPACT_SECOND;

@SpringBootTest
@TestPropertySource(properties = {
		"spring.datasource.hikari.maximum-pool-size=1"
})
class AopTransactionalApplicationTests {
	@Autowired
	private StudentService studentPureSelectQueryService;

	ThreadPoolExecutorForInfo threadPoolExecutorForInfo = new ThreadPoolExecutorForInfo();

	/**
	 * AOP procedures will slow down your @Transactional methods
	 */
	@Test
	void aop_procedure_keeps_open_transactional_session() {
		ThreadExecutorInfo threadExecutorInfo = threadPoolExecutorForInfo.execute(5, () -> {
			return studentPureSelectQueryService.getStudentTransactional(1);
		});

		System.out.println(threadExecutorInfo);

		long difference = Math.abs(threadExecutorInfo.getMaxTime() - threadExecutorInfo.getMinTime());
		Assertions.assertThat(Duration.ofMillis(difference)).isGreaterThan(Duration.ofSeconds(AOP_IMPACT_SECOND));
	}

	/**
	 * AOP will not impact to your none @Transactional methods
	 */
	@Test
	void aop_procedure_not_keeps_none_transactional_session() {
		ThreadExecutorInfo threadExecutorInfo = threadPoolExecutorForInfo.execute(5, () -> {
			return studentPureSelectQueryService.getStudentDefault(1);
		});

		System.out.println(threadExecutorInfo);
		long difference = Math.abs(threadExecutorInfo.getMaxTime() - threadExecutorInfo.getMinTime());
		Assertions.assertThat(Duration.ofMillis(difference)).isLessThan(Duration.ofSeconds(1));
	}

}
