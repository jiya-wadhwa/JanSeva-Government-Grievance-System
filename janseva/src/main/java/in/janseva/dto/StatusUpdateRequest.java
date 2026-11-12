package in.janseva.dto;

import in.janseva.model.ComplaintStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record StatusUpdateRequest(@NotNull ComplaintStatus status,
                                  @Size(max=100) String assignedOfficer,
                                  @Size(max=1000) String resolutionNote) {}
