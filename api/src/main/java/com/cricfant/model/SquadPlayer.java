package com.cricfant.model;

import com.cricfant.constant.PowerType;

import javax.persistence.*;

@Entity
@Table(name = "squad_player")
public class SquadPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "power_type", length = 32)
    private PowerType powerType;

    @ManyToOne
    @JoinColumn(name = "squad_id", referencedColumnName = "id", nullable = false)
    private Squad squad;

    @ManyToOne
    @JoinColumn(name = "tournament_team_player_id", referencedColumnName = "id", nullable = false)
    private TournamentTeamPlayer tournamentTeamPlayer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PowerType getPowerType() {
        return powerType;
    }

    public void setPowerType(PowerType powerType) {
        this.powerType = powerType;
    }

    public Squad getSquad() {
        return squad;
    }

    public void setSquad(Squad squadBySquadId) {
        this.squad = squadBySquadId;
    }

    public TournamentTeamPlayer getTournamentTeamPlayer() {
        return tournamentTeamPlayer;
    }

    public void setTournamentTeamPlayer(TournamentTeamPlayer tournamentTeamPlayerByTournamentTeamPlayerId) {
        this.tournamentTeamPlayer = tournamentTeamPlayerByTournamentTeamPlayerId;
    }
}
