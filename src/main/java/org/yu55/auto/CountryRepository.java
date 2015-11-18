package org.yu55.auto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.yu55.Country;

@Component
@CacheConfig(cacheNames = "countries")
public class CountryRepository {

	private static final Logger logger = LoggerFactory.getLogger(CountryRepository.class);
	@Cacheable
	public Country findByCode(String code) {
		logger.info("\tCache miss. Loading country with code '{}'", code);
		return new Country(code);
	}

}
