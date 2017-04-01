
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.CacheTime;

@Component
@Transactional
public class CacheTimeToStringConverter implements Converter<CacheTime, String> {

	@Override
	public String convert(final CacheTime cacheTime) {
		String result;

		if (cacheTime == null)
			result = null;
		else
			result = String.valueOf(cacheTime.getId());

		return result;
	}

}
