
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.MealOrder;

@Component
@Transactional
public class MealOrderToStringConverter implements Converter<MealOrder, String> {

	@Override
	public String convert(final MealOrder mealOrder) {
		String result;

		if (mealOrder == null)
			result = null;
		else
			result = String.valueOf(mealOrder.getId());

		return result;
	}

}
