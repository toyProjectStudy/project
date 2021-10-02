/**
 * ===============================================================
 * File name : EstimationRequest.java
 * Created by injeahwang on 2021-10-01
 * ===============================================================
 */
package com.suwon.toy.moving.out.common.estimation;

import com.suwon.toy.moving.out.common.estimation.embbeded.EstimationDetails;
import com.suwon.toy.moving.out.common.estimation.embbeded.Route;
import com.suwon.toy.moving.out.common.payload.RequestedPayload;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="TB_ESTIMATION_REQUEST")
@NoArgsConstructor
@AllArgsConstructor
public class EstimationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long estimationId;

    @Embedded
    private Route route;

    @Column(name="team_id")
    private long teamId;

    @Column(name="user_id")
    private String userId;

    @Column(name = "estimation_status")
    private String estimationStatus;

    @Column(name = "manager_emp_id")
    private long managerEmpId;

    @Embedded
    private EstimationDetails estimationDetails;

    @Column(name = "reply_email_send_count")
    private long replyEmailSendCount;

    @ManyToMany
    @JoinTable(name = "TB_PAYLOAD_ESTIMATION_LINK",
            joinColumns = @JoinColumn(name="estimationId"),
            inverseJoinColumns = @JoinColumn(name = "requested_payload_info_id"))
    private List<RequestedPayload> requestedPayloadList = Collections.emptyList();
}
