package ro.etrcpo.htcalculator.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SKILLS")
public class Skill {

    @Id
    private Long id;

    @Column(length = 100)
    private String name;

    public Skill() {

    }

    public Skill(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
