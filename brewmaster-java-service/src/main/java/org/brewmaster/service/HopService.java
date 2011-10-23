package org.brewmaster.service;

import org.brewmaster.domain.Hop;
import org.springframework.stereotype.Service;

@Service
public class HopService extends AbstractEntityService<Hop> {

	@Override
	protected Class<Hop> getEntityClass() {
		return Hop.class;
	}

}
