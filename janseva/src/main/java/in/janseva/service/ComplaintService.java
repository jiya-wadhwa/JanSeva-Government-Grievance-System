package in.janseva.service;

import in.janseva.dto.*;
import in.janseva.model.*;
import in.janseva.repository.ComplaintRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

@Service
public class ComplaintService {
    private final ComplaintRepository repository;
    public ComplaintService(ComplaintRepository repository) { this.repository = repository; }

    public Complaint create(ComplaintRequest request) {
        Complaint c = new Complaint();
        c.setTrackingId("JS-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        c.setCitizenName(request.citizenName().trim()); c.setEmail(request.email().trim());
        c.setPhone(request.phone().trim()); c.setDepartment(request.department());
        c.setSubject(request.subject().trim()); c.setDescription(request.description().trim());
        c.setLocation(request.location().trim());
        return repository.save(c);
    }
    public List<Complaint> all() { return repository.findAllByOrderByCreatedAtDesc(); }
    public Complaint get(Long id) { return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Complaint not found")); }
    public Complaint track(String trackingId) { return repository.findByTrackingId(trackingId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No complaint found for this tracking ID")); }
    public Complaint update(Long id, StatusUpdateRequest request) {
        Complaint c = get(id); c.setStatus(request.status());
        if (request.assignedOfficer() != null) c.setAssignedOfficer(request.assignedOfficer().trim());
        if (request.resolutionNote() != null) c.setResolutionNote(request.resolutionNote().trim());
        return repository.save(c);
    }
    public Map<String, Object> dashboard() {
        Map<String,Object> result = new LinkedHashMap<>();
        result.put("total", repository.count());
        for (ComplaintStatus status : ComplaintStatus.values()) result.put(status.name().toLowerCase(), repository.countByStatus(status));
        result.put("recentComplaints", all().stream().limit(5).toList());
        return result;
    }
}
