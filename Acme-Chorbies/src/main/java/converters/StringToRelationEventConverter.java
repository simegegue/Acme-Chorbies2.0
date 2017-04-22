package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.RelationEventRepository;
import domain.RelationEvent;

@Component
@Transactional
public class StringToRelationEventConverter implements Converter<String, RelationEvent>{
	
	@Autowired
	RelationEventRepository		relationEventRepository;


	@Override
	public RelationEvent convert(final String text) {
		RelationEvent result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.relationEventRepository.findOne(id);
			}
		} catch (final Exception oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
