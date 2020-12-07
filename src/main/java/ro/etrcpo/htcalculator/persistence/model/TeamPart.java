package ro.etrcpo.htcalculator.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "TEAM_PARTS")
public class TeamPart implements Serializable {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", length = 100)
    private String name;

    @ManyToOne
    @JoinColumn(name = "PARENT_TEAM_PART_ID")
    private TeamPart parentTeamPart;

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
