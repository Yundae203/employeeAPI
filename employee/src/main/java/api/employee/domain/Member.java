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
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    private String name;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    private LocalDate birthday;
    private LocalDate workStartDate;

    // ==== 생성자 ==== //
    @Builder
    public Member(Team team, String name, Role role, LocalDate birthday, LocalDate workStartDate) {
        if (team != null) {
            this.team = team;
            this.team.increaseMemberCount();
        }
        this.name = name;
        this.role = role;
        this.birthday = birthday;
        this.workStartDate = workStartDate;
    }

    // ==== 변경자 ==== //
    public Member changeTeam(Team team) {
        if (this.team != null) {
            this.team.decreaseMemberCount();
        }

        this.team = team;

        if (team != null) {
            this.team.increaseMemberCount();
        }
        return this;
    }

    public Member changeRole(Role role) {
        this.role = role;
        if(this.team != null && role == Role.MANAGER) {
            this.team.changeManager(this.name);
        }
        return this;
    }

    public Member changeName(String name) {
        this.name = name;
        return this;
    }

    public Member changeBirthday(LocalDate birthday) {
        this.birthday = birthday;
        return this;
    }

    public Member changeWorkStartDate(LocalDate workStartDate) {
        this.workStartDate = workStartDate;
        return this;
    }
}
