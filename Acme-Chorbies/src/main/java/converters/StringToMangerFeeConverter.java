package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.ManagerFeeRepository;
import domain.ManagerFee;

@Component
@Transactional
public class StringToMangerFeeConverter implements Converter<String, ManagerFee>{
	
	@Autowired
	ManagerFeeRepository	 managerFeeRepository;


	@Override
	public ManagerFee convert(final String text) {
		ManagerFee result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.managerFeeRepository.findOne(id);
			}
		} catch (final Exception oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
