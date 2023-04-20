package dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CallDataRecord {

    private final CallType callType;
    private final String phoneNumber;
    private final LocalDateTime callStartTime;
    private final LocalDateTime callEndTime;
}
