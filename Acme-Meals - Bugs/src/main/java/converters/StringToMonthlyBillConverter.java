
package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.MonthlyBillRepository;
import domain.MonthlyBill;

@Component
@Transactional
public class StringToMonthlyBillConverter implements Converter<String, MonthlyBill> {

	@Autowired
	MonthlyBillRepository	monthlyBillRepository;


	@Override
	public MonthlyBill convert(final String text) {
		MonthlyBill result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.monthlyBillRepository.findOne(id);
			}
		} catch (final Exception oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
