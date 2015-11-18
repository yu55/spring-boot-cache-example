package org.yu55;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableCaching
@EnableScheduling
@SpringBootApplication
public class SpringBootCacheExampleApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder().sources(SpringBootCacheExampleApplication.class)
				.profiles("manual").run(args);
//				.profiles("auto").run(args);
	}

}
