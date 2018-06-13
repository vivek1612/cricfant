package com.cricfant.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Venue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "name", nullable = false, length = 128)
    private String name;

    @Basic
    @Column(name = "ext_id")
    private Integer extId;

    @OneToMany(mappedBy = "venue")
    private Set<Match> matches;

    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private City city;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getExtId() {
        return extId;
    }

    public void setExtId(Integer extId) {
        this.extId = extId;
    }

    public Set<Match> getMatches() {
        return matches;
    }

    public void setMatches(Set<Match> matchesById) {
        this.matches = matchesById;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City cityByCityId) {
        this.city = cityByCityId;
    }
}
