
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Promote;

@Component
@Transactional
public class PromoteToStringConverter implements Converter<Promote, String> {

	@Override
	public String convert(final Promote promote) {
		String result;

		if (promote == null)
			result = null;
		else
			result = String.valueOf(promote.getId());

		return result;
	}

}
