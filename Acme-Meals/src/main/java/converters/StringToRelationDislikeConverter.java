
package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.RelationDislikeRepository;
import domain.RelationDislike;

@Component
@Transactional
public class StringToRelationDislikeConverter implements Converter<String, RelationDislike> {

	@Autowired
	RelationDislikeRepository	relationDislikeRepository;


	@Override
	public RelationDislike convert(final String text) {
		RelationDislike result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.relationDislikeRepository.findOne(id);
			}
		} catch (final Exception oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
