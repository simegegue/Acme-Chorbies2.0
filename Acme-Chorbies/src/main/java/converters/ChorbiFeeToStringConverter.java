package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.ChorbiFee;

@Component
@Transactional
public class ChorbiFeeToStringConverter implements Converter<ChorbiFee, String>{
	
	@Override
	public String convert(final ChorbiFee chorbiFee) {
		String result;

		if (chorbiFee == null)
			result = null;
		else
			result = String.valueOf(chorbiFee.getId());

		return result;
	}

}
