/**
 * ===============================================================
 * File name : EstimationDetails.java
 * Created by injeahwang on 2021-10-02
 * ===============================================================
 */
package com.suwon.toy.moving.out.common.estimation.embbeded;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstimationDetails {

    @Column(name = "estimation_description")
    private String estimationDescription;

    @Column(name = "estimation_request_date")
    private LocalDate estimationRequestDate;

    @Column(name = "estimation_due_date")
    private LocalDate estimationDueDate;

    @Column(name = "estimation_checked_emp_id")
    private long estimationCheckedEmpId;

    @Column(name = "estimation_amount")
    private long estimationAmount;
}
