package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.RelationEvent;

@Component
@Transactional
public class RelationEventToStringConverter implements Converter<RelationEvent, String>{
	
	@Override
	public String convert(final RelationEvent manager) {
		String result;

		if (manager == null)
			result = null;
		else
			result = String.valueOf(manager.getId());

		return result;
	}

}
