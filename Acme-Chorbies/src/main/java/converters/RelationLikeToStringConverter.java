
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.RelationLike;

@Component
@Transactional
public class RelationLikeToStringConverter implements Converter<RelationLike, String> {

	@Override
	public String convert(final RelationLike relationLike) {
		String result;

		if (relationLike == null)
			result = null;
		else
			result = String.valueOf(relationLike.getId());

		return result;
	}

}
