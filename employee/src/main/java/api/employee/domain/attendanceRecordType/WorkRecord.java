package api.employee.domain.attendanceRecordType;


import api.employee.domain.AttendanceStatus;
import api.employee.domain.Member;
import api.employee.domain.WorkTime;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("work")
public class WorkRecord extends AttendanceStatus {

    private LocalTime startTime;
    private LocalTime endTime;
    @Embedded
    private WorkTime workTime;

    // ==== 생성자 ==== //
    private WorkRecord(Member member, LocalDate attendanceDate, LocalTime startTime) {
        super(member, attendanceDate);
        this.startTime = startTime;
    }

    public static WorkRecord recordStartTime(Member member, LocalDate attendanceDate, LocalTime startTime) {
        return new WorkRecord(member, attendanceDate, startTime);
    }

    // ==== 변경자 ==== //
    public WorkRecord recordEndTime(LocalTime endTime) {
        if (!this.startTime.isBefore(endTime)) {
            throw new IllegalArgumentException("퇴근 시간이 출근 시간보다 빠릅니다.");
        }
        this.endTime = endTime;
        this.workTime = WorkTime.workRecord(this.startTime, this.endTime);
        return this;
    }

    // ==== 편의 메서드 ==== //
    public Long getWorkingMinute() {
        return this.workTime.getWorkingMinute();
    }
}
