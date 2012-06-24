package org.brewmaster.data;

import org.brewmaster.domain.Malt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaltRepository extends JpaRepository<Malt, Long> {
}
