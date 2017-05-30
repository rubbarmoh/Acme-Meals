
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Restaurant;

@Component
@Transactional
public class RestaurantToStringConverter implements Converter<Restaurant, String> {

	@Override
	public String convert(final Restaurant restaurant) {
		String result;

		if (restaurant == null)
			result = null;
		else
			result = String.valueOf(restaurant.getId());

		return result;
	}

}
