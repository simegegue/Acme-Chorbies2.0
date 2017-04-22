package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.ChorbiFeeRepository;
import domain.ChorbiFee;

@Component
@Transactional
public class StringToChorbiFeeConverter implements Converter<String, ChorbiFee>{
	
	@Autowired
	ChorbiFeeRepository	chorbiFeeRepository;


	@Override
	public ChorbiFee convert(final String text) {
		ChorbiFee result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.chorbiFeeRepository.findOne(id);
			}
		} catch (final Exception oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
