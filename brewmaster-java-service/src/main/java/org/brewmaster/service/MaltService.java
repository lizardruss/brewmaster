package org.brewmaster.service;

import org.brewmaster.domain.Malt;
import org.springframework.stereotype.Service;

@Service
public class MaltService extends AbstractEntityService<Malt> {

	@Override
	protected Class<Malt> getEntityClass() {
		return Malt.class;
	}

}
