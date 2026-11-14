package com.shecancode.attendence.registration.Event;

import lombok.*;
import java.time.Instant;
import java.util.UUID;

/** Emitted when an administrator creates a student enrolment. */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentRegisteredEvent {
    private int version;
    private UUID eventId;
    private UUID studentId;
    private UUID programId;
    private UUID cohortId;
    private Instant timestamp;
}
