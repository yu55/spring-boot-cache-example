package org.yu55.manual;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.yu55.Country;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
@Profile("manual")
public class ManualCacheFill {

	private static final Logger logger = LoggerFactory.getLogger(ManualCacheFill.class);

	private static final List<String> SAMPLE_COUNTRY_CODES = Arrays.asList("AF", "AX",
			"AL", "DZ", "AS", "AD", "AO", "AI", "AQ", "AG", "AR", "AM", "AW", "AU", "AT",
			"AZ", "BS", "BH", "BD", "BB", "BY", "BE", "BZ", "BJ", "BM", "BT", "BO", "BQ",
			"BA", "BW", "BV", "BR", "IO", "BN", "BG", "BF", "BI", "KH", "CM", "CA", "CV",
			"KY", "CF", "TD", "CL", "CN", "CX", "CC", "CO", "KM", "CG", "CD", "CK", "CR",
			"CI", "HR", "CU", "CW", "CY", "CZ", "DK", "DJ", "DM", "DO", "EC", "EG", "SV",
			"GQ", "ER", "EE", "ET", "FK", "FO", "FJ", "FI", "FR", "GF", "PF", "TF", "GA",
			"GM", "GE", "DE", "GH", "GI", "GR", "GL", "GD", "GP", "GU", "GT", "GG", "GN",
			"GW", "GY", "HT", "HM", "VA", "HN", "HK", "HU", "IS", "IN", "ID", "IR", "IQ",
			"IE", "IM", "IL", "IT", "JM", "JP", "JE", "JO", "KZ", "KE", "KI", "KP", "KR",
			"KW", "KG", "LA", "LV", "LB", "LS", "LR", "LY", "LI", "LT", "LU", "MO", "MK",
			"MG", "MW", "MY", "MV", "ML", "MT", "MH", "MQ", "MR", "MU", "YT", "MX", "FM",
			"MD", "MC", "MN", "ME", "MS", "MA", "MZ", "MM", "NA", "NR", "NP", "NL", "NC",
			"NZ", "NI", "NE", "NG", "NU", "NF", "MP", "NO", "OM", "PK", "PW", "PS", "PA",
			"PG", "PY", "PE", "PH", "PN", "PL", "PT", "PR", "QA", "RE", "RO", "RU", "RW",
			"BL", "SH", "KN", "LC", "MF", "PM", "VC", "WS", "SM", "ST", "SA", "SN", "RS",
			"SC", "SL", "SG", "SX", "SK", "SI", "SB", "SO", "ZA", "GS", "SS", "ES", "LK",
			"SD", "SR", "SJ", "SZ", "SE", "CH", "SY", "TW", "TJ", "TZ", "TH", "TL", "TG",
			"TK", "TO", "TT", "TN", "TR", "TM", "TC", "TV", "UG", "UA", "AE", "GB", "US",
			"UM", "UY", "UZ", "VU", "VE", "VN", "VG", "VI", "WF", "EH", "YE", "ZM", "ZW");

	private final Random random;

	private final CacheManager cacheManager;

	@Autowired
	public ManualCacheFill(CacheManager cacheManager) {
		random = new Random();
		this.cacheManager = cacheManager;
	}

	@Scheduled(fixedDelay = 500)
	public void retrieveCountry() {
		String randomCode = SAMPLE_COUNTRY_CODES
				.get(this.random.nextInt(SAMPLE_COUNTRY_CODES.size()));

		Cache cache = cacheManager.getCache("countries");
		logger.info("Looking for country with code '{}'", randomCode);
		if (cache.get(randomCode) == null) { // cache.putIfAbsent also possible here!
			logger.info("\tCache miss. Adding to cache.");
			cache.put(randomCode, new Country(randomCode));
		}
		logger.info("Cache hit: {}", cache.get(randomCode).get());
	}
}
