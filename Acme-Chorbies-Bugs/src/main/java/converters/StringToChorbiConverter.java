
package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.ChorbiRepository;
import domain.Chorbi;

@Component
@Transactional
public class StringToChorbiConverter implements Converter<String, Chorbi> {

	@Autowired
	ChorbiRepository	chorbiRepository;


	@Override
	public Chorbi convert(final String text) {
		Chorbi result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.chorbiRepository.findOne(id);
			}
		} catch (final Exception oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
