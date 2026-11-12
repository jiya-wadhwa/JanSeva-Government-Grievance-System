package in.janseva.controller;

import in.janseva.dto.*;
import in.janseva.model.*;
import in.janseva.service.ComplaintService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ComplaintController {
    private final ComplaintService service;
    public ComplaintController(ComplaintService service) { this.service = service; }
    @PostMapping("/complaints") public Complaint create(@Valid @RequestBody ComplaintRequest request) { return service.create(request); }
    @GetMapping("/complaints") public List<Complaint> all() { return service.all(); }
    @GetMapping("/complaints/{id}") public Complaint get(@PathVariable Long id) { return service.get(id); }
    @GetMapping("/track/{trackingId}") public Complaint track(@PathVariable String trackingId) { return service.track(trackingId); }
    @PatchMapping("/complaints/{id}/status") public Complaint update(@PathVariable Long id, @Valid @RequestBody StatusUpdateRequest request) { return service.update(id, request); }
    @GetMapping("/dashboard") public Map<String,Object> dashboard() { return service.dashboard(); }
    @GetMapping("/departments") public List<Map<String,String>> departments() { return Arrays.stream(Department.values()).map(d -> Map.of("value",d.name(),"label",d.name().replace('_',' '))).toList(); }
}
