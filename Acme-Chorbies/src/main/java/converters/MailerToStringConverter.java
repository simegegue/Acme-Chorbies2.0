package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Mailer;

@Component
@Transactional
public class MailerToStringConverter implements Converter<Mailer, String>{
	
	@Override
	public String convert(final Mailer senders) {
		String result;

		if (senders == null)
			result = null;
		else
			result = String.valueOf(senders.getId());

		return result;
	}

}
