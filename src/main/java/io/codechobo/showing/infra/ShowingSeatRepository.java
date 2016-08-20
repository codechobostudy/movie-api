package io.codechobo.showing.infra;

import io.codechobo.showing.model.ShowingSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowingSeatRepository extends JpaRepository<ShowingSeat, Long>{
}
