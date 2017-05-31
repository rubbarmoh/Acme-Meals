
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Reporter;

@Component
@Transactional
public class ReporterToStringConverter implements Converter<Reporter, String> {

	@Override
	public String convert(final Reporter reporter) {
		String result;

		if (reporter == null)
			result = null;
		else
			result = String.valueOf(reporter.getId());

		return result;
	}

}
