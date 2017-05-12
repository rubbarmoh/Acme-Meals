
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Meal;

@Component
@Transactional
public class MealToStringConverter implements Converter<Meal, String> {

	@Override
	public String convert(final Meal meal) {
		String result;

		if (meal == null)
			result = null;
		else
			result = String.valueOf(meal.getId());

		return result;
	}

}
