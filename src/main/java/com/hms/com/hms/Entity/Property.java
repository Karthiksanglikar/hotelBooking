package com.hms.com.hms.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "property")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "no_of_guest", nullable = false)
    private Integer no_of_guest;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNo_of_guest() {
        return no_of_guest;
    }

    public void setNo_of_guest(Integer no_of_guest) {
        this.no_of_guest = no_of_guest;
    }

    public Integer getNo_of_bed() {
        return no_of_bed;
    }

    public void setNo_of_bed(Integer no_of_bed) {
        this.no_of_bed = no_of_bed;
    }

    public Integer getNo_of_room() {
        return no_of_room;
    }

    public void setNo_of_room(Integer no_of_room) {
        this.no_of_room = no_of_room;
    }

    public String getNo_of_bathroom() {
        return no_of_bathroom;
    }

    public void setNo_of_bathroom(String no_of_bathroom) {
        this.no_of_bathroom = no_of_bathroom;
    }

    @Column(name = "no_of_bed", nullable = false)
    private Integer no_of_bed;

    @Column(name = "no_of_room", nullable = false)
    private Integer no_of_room;

    @Column(name = "no_of_bathroom")
    private String no_of_bathroom;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

}