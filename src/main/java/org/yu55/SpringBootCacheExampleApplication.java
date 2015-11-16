package org.yu55;

import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;
import org.infinispan.spring.provider.SpringEmbeddedCacheManager;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableCaching
@EnableScheduling
@SpringBootApplication
public class SpringBootCacheExampleApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder().sources(SpringBootCacheExampleApplication.class)
				.profiles("app").run(args);
	}

	@Bean
	public CacheManager cacheManager() {
		return new SpringEmbeddedCacheManager(infinispanCacheManager());
	}

	private EmbeddedCacheManager infinispanCacheManager() {
		return new DefaultCacheManager();
	}
}
