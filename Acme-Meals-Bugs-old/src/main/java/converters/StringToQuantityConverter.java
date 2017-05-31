
package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.QuantityRepository;
import domain.Quantity;

@Component
@Transactional
public class StringToQuantityConverter implements Converter<String, Quantity> {

	@Autowired
	QuantityRepository	quantityRepository;


	@Override
	public Quantity convert(final String text) {
		Quantity result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.quantityRepository.findOne(id);
			}
		} catch (final Exception oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
