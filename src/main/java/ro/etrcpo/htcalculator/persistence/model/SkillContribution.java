package ro.etrcpo.htcalculator.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "SKILL_CONTRIBUTIONS")
public class SkillContribution implements Serializable {

    @Id
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "POSITION_ID")
    private Position position;

    @ManyToOne
    @JoinColumn(name = "SKILL_ID")
    private Skill skill;

    @ManyToOne
    @JoinColumn(name = "TEAM_PART_ID")
    private TeamPart teamPart;

    @Column(name = "PERCENT_VALUE", precision = 5, scale = 2)
    private Double percentValue;

    @Column(name = "CENTRAL_OR_SIDE_FLAG", length = 1)
    private String centralOrSideFlag;

//    @CreatedBy
//    @Column(name = "CREATED_BY", length = 100)
//    private String createdBy;
//
//    @CreatedDate
//    @Column(name = "CREATION_DATE")
//    private LocalDateTime creationDate;
//
//    @LastModifiedBy
//    @Column(name = "LAST_UPDATED_BY", length = 100)
//    private String lastUpdatedBy;
//
//    @LastModifiedDate
//    @Column(name = "LAST_UPDATE_DATE")
//    private LocalDateTime lastUpdateDate;
}
