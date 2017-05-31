
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.RelationDislike;

@Component
@Transactional
public class RelationDislikeToStringConverter implements Converter<RelationDislike, String> {

	@Override
	public String convert(final RelationDislike relationDislike) {
		String result;

		if (relationDislike == null)
			result = null;
		else
			result = String.valueOf(relationDislike.getId());

		return result;
	}

}
