package org.yu55;

import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;
import org.infinispan.spring.provider.SpringEmbeddedCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheManagerConfiguration {

	@Bean
	public CacheManager cacheManager() {
//		return new SpringEmbeddedCacheManager(infinispanCacheManager());
		return jdkBasedCacheManager();
	}

	private EmbeddedCacheManager infinispanCacheManager() {
		return new DefaultCacheManager();
	}

	private CacheManager jdkBasedCacheManager() {
		return new ConcurrentMapCacheManager();
	}
}
