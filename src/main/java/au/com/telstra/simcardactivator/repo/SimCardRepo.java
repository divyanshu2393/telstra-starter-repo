package au.com.telstra.simcardactivator.repo;

import au.com.telstra.simcardactivator.entity.SimCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimCardRepo extends JpaRepository<SimCard, Long> {
}
