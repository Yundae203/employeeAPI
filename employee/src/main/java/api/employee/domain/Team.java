package api.employee.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "team_id")
    private Long id;

    private String name;
    private String manager;
    private int leavePolicy;
    private int memberCount;

    // ==== 생성자 ==== //
    @Builder
    public Team(String name, String manager, int leavePolicy, int memberCount) {
        this.name = name;
        this.manager = manager;
        this.leavePolicy = leavePolicy;
        this.memberCount = memberCount;
    }

    // ==== 변경자 ==== //
    public Team changeName(String name) {
        this.name = name;
        return this;
    }

    public Team changeManager(String manager) {
        this.manager = manager;
        return this;
    }

    public Team changeLeavePolicy(int leavePolicy) {
        this.leavePolicy = leavePolicy;
        return this;
    }

    // ==== 편의 메서드 ==== //
    public void increaseMemberCount() {
        this.memberCount++;
    }

    public void decreaseMemberCount() {
        this.memberCount--;
    }
}
