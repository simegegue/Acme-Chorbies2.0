package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.ManagerFee;

@Component
@Transactional
public class ManagerFeeToStringConverter implements Converter<ManagerFee, String>{
	
	@Override
	public String convert(final ManagerFee managerFee) {
		String result;

		if (managerFee == null)
			result = null;
		else
			result = String.valueOf(managerFee.getId());

		return result;
	}

}
