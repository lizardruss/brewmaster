package org.brewmaster.data;

import org.brewmaster.domain.Hop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HopRepository extends JpaRepository<Hop, Long> {
}
