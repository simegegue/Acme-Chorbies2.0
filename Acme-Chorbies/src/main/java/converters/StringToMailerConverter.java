package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.MailerRepository;
import domain.Mailer;

@Component
@Transactional
public class StringToMailerConverter implements Converter<String, Mailer>{
	
	@Autowired
	MailerRepository	sendersRepository;


	@Override
	public Mailer convert(final String text) {
		Mailer result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = sendersRepository.findOne(id);
			}
		} catch (Exception oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
