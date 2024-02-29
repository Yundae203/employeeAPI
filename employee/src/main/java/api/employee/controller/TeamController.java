package api.employee.controller;


import api.employee.model.TeamForm;
import api.employee.model.TeamResponse;
import api.employee.model.TeamUpdateForm;
import api.employee.service.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping("/team")
    public void saveTeam(TeamForm teamForm) {
        teamService.saveTeam(teamForm);
    }

    @GetMapping("/team-list")
    public List<TeamResponse> findAllTeam() {
        return teamService.findAllTeam().stream()
                .map(TeamResponse::fromTeam)
                .toList();
    }

    @GetMapping("/team/{teamId}")
    public TeamResponse findOneTeam(@PathVariable Long teamId) {
        return teamService.findOneTeam(teamId);
    }

    @PutMapping("/team")
    public void updateTeam(TeamUpdateForm teamUpdateForm) {
        teamService.updateTeam(teamUpdateForm);
    }

    @DeleteMapping("/team")
    public void deleteTeam(Long teamId) {
        teamService.deleteTeam(teamId);
    }
}
