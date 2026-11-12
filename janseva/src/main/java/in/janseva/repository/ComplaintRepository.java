package in.janseva.repository;

import in.janseva.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    Optional<Complaint> findByTrackingId(String trackingId);
    List<Complaint> findAllByOrderByCreatedAtDesc();
    long countByStatus(ComplaintStatus status);
}
