
package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.InvoiceRepository;
import domain.Invoice;

@Component
@Transactional
public class StringToInvoiceConverter implements Converter<String, Invoice> {

	@Autowired
	InvoiceRepository	invoiceRepository;


	@Override
	public Invoice convert(final String text) {
		Invoice result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.invoiceRepository.findOne(id);
			}
		} catch (final Exception oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
