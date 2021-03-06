package ro.etrcpo.htcalculator.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "ABILITIES")
public class Ability implements Serializable {

    @Id
    @Column(name = "ID")
    private Long id;

    @Id
    @Column(name = "VALUE")
    private Long value;

    @Column(name = "NAME", length = 100)
    private String name;

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
