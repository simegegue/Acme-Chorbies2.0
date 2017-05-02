
package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.KindRelationshipRepository;
import domain.KindRelationship;

@Component
@Transactional
public class StringToKindRelationshipConverter implements Converter<String, KindRelationship> {

	@Autowired
	KindRelationshipRepository	kindRelationshipRepository;


	@Override
	public KindRelationship convert(final String text) {
		KindRelationship result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.kindRelationshipRepository.findOne(id);
			}
		} catch (final Exception oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
