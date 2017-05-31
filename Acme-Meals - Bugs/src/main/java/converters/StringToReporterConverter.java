
package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.ReporterRepository;
import domain.Reporter;

@Component
@Transactional
public class StringToReporterConverter implements Converter<String, Reporter> {

	@Autowired
	ReporterRepository	reporterRepository;


	@Override
	public Reporter convert(final String text) {
		Reporter result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.reporterRepository.findOne(id);
			}
		} catch (final Exception oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
