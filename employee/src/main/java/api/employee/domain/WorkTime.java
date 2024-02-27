package api.employee.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkTime {

    private final Long ZERO = 0L;
    private Long workTime;

    private WorkTime(Long workTime) {
        this.workTime = workTime;
    }

    public static WorkTime time(Long workTime) {
        return new WorkTime(workTime);
    }

}
