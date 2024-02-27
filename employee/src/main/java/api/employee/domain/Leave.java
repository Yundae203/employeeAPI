package api.employee.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Leave {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "leave_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "team_id")
    private Team team;
    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private int remainingDays;

    // ==== 생성자 ==== //
    @Builder
    public Leave(Team team, Member member) {
        Assert.notNull(team, "Team은 null일 수 없습니다.");
        Assert.notNull(member, "member는 null일 수 없습니다.");

        this.team = team;
        this.member = member;

        LocalDate workStartDate = this.member.getWorkStartDate();
        if (workStartDate.getYear() == LocalDate.now().getYear()) {
            this.remainingDays = 11;
        } else {
            this.remainingDays = 15;
        }
    }

    // ==== 편의 메서드 ==== //
    public void increaseRemainingDays() {
        this.remainingDays++;
    }

    public void decreaseRemainingDays() {
        if (this.remainingDays <= 0) {
            throw new IllegalArgumentException("연차의 잔여일수가 부족합니다.");
        }
        this.remainingDays--;
    }

}