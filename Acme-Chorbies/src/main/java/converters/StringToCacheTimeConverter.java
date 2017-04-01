
package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.CacheTimeRepository;
import domain.CacheTime;

@Component
@Transactional
public class StringToCacheTimeConverter implements Converter<String, CacheTime> {

	@Autowired
	CacheTimeRepository	cacheTimeRepository;


	@Override
	public CacheTime convert(final String text) {
		CacheTime result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.cacheTimeRepository.findOne(id);
			}
		} catch (final Exception oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
