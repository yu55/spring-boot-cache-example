package org.yu55.auto;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.yu55.Country;

@Component
@CacheConfig(cacheNames = "countries")
public class CountryRepository {

	@Cacheable
	public Country findByCode(String code) {
		System.out.println("\tCache miss. Loading country with code '" + code + "'");
		return new Country(code);
	}

}
