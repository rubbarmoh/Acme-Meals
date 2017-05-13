
package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.MealOrderRepository;
import domain.MealOrder;

@Component
@Transactional
public class StringToMealOrderConverter implements Converter<String, MealOrder> {

	@Autowired
	MealOrderRepository	mealOrderRepository;


	@Override
	public MealOrder convert(final String text) {
		MealOrder result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.mealOrderRepository.findOne(id);
			}
		} catch (final Exception oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
