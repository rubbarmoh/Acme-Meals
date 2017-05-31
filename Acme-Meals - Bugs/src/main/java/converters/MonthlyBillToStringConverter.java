
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.MonthlyBill;

@Component
@Transactional
public class MonthlyBillToStringConverter implements Converter<MonthlyBill, String> {

	@Override
	public String convert(final MonthlyBill monthlyBill) {
		String result;

		if (monthlyBill == null)
			result = null;
		else
			result = String.valueOf(monthlyBill.getId());

		return result;
	}

}
