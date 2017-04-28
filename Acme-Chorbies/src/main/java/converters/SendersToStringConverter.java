package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Senders;

@Component
@Transactional
public class SendersToStringConverter implements Converter<Senders, String>{
	
	@Override
	public String convert(final Senders senders) {
		String result;

		if (senders == null)
			result = null;
		else
			result = String.valueOf(senders.getId());

		return result;
	}

}
