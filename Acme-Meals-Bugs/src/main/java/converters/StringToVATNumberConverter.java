
package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.VATNumberRepository;
import domain.VATNumber;

@Component
@Transactional
public class StringToVATNumberConverter implements Converter<String, VATNumber> {

	@Autowired
	VATNumberRepository	vatNumberRepository;


	@Override
	public VATNumber convert(final String text) {
		VATNumber result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.vatNumberRepository.findOne(id);
			}
		} catch (final Exception oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
