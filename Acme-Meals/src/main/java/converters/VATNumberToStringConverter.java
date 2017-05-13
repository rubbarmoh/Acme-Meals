
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.VATNumber;

@Component
@Transactional
public class VATNumberToStringConverter implements Converter<VATNumber, String> {

	@Override
	public String convert(final VATNumber vatNumber) {
		String result;

		if (vatNumber == null)
			result = null;
		else
			result = String.valueOf(vatNumber.getId());

		return result;
	}

}
