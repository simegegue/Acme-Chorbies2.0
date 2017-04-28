package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.SendersRepository;
import domain.Senders;

@Component
@Transactional
public class StringToSendersConverter implements Converter<String, Senders>{
	
	@Autowired
	SendersRepository	sendersRepository;


	@Override
	public Senders convert(final String text) {
		Senders result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.sendersRepository.findOne(id);
			}
		} catch (final Exception oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
