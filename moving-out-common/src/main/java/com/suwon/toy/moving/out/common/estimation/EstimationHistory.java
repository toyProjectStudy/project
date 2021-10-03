/**
 * ===============================================================
 * File name : EstimationHistory.java
 * Created by injeahwang on 2021-10-01
 * ===============================================================
 */
package com.suwon.toy.moving.out.common.estimation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "TB_ESTIMATION_HISTORY")
@NoArgsConstructor
@AllArgsConstructor
public class EstimationHistory {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "estimation_history_id")
    private long estimationHistoryId;

    @Column(name = "estimation_id")
    private long estimationId;

    @Column(name = "estimation_updated_timestamp")
    private Timestamp estimationUpdatedTimestamp;

    @Column(name = "estimation_updated_user_id")
    private String estimationUpdatedUserId;

    @Column(name = "estimation_updated_emp_id")
    private long estimationUpdatedEmpId;

    @Column(name = "estimation_updated_discription")
    private String estimationUpdatedDescription;

}
