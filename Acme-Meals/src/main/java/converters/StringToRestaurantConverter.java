
package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.RestaurantRepository;
import domain.Restaurant;

@Component
@Transactional
public class StringToRestaurantConverter implements Converter<String, Restaurant> {

	@Autowired
	RestaurantRepository	restaurantRepository;


	@Override
	public Restaurant convert(final String text) {
		Restaurant result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.restaurantRepository.findOne(id);
			}
		} catch (final Exception oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
