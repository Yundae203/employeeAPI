package api.employee.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "record_type")
public abstract class AttendanceStatus {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "AttendanceStatus_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public abstract WorkTime getWorkTime();

}
