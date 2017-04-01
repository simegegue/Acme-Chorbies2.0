
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.KindRelationship;

@Component
@Transactional
public class KindRelationshipToStringConverter implements Converter<KindRelationship, String> {

	@Override
	public String convert(final KindRelationship kindRelationship) {
		String result;

		if (kindRelationship == null)
			result = null;
		else
			result = String.valueOf(kindRelationship.getId());

		return result;
	}

}
