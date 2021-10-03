/**
 * ===============================================================
 * File name : MailSendHistory.java
 * Created by injeahwang on 2021-10-02
 * ===============================================================
 */
package com.suwon.toy.moving.out.common.messanger;

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
@Table(name = "TB_MAIL_SEND_HISTORY")
@NoArgsConstructor
@AllArgsConstructor
public class MailSendHistory {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "mail_send_history_id")
    private long mailSendHistoryId;

    @Column(name = "estimation_id")
    private long estimationId;

    @Column(name = "sended_timestamp")
    private Timestamp sendedTimestamp;

    @Column(name = "sender_type_code")
    private String senderTypeCode;

    @Column(name = "reciever_user_id")
    private String recieverUserId;

    @Column(name = "send_message_type")
    private String sendMessageType;

    @Column(name = "send_mail_message")
    private String sendMailMessage;
}
