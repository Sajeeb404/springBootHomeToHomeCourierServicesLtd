package com.example.HomeToHomeSpring.dto;

import com.example.HomeToHomeSpring.enums.CourierStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDTO {


    //    SenderVaiable
    private String p_sender_name;
    private String  p_sender_phone;
    private String  p_sender_address;
    private String  p_sender_branch;



    //    ReciverVariable
    private String p_receiver_name;
    private String p_receiver_phone;
    private String p_receiver_address;
    private String p_receiver_branch;


    //    courier item variable
    private String prodcuttype;
    private String productname;
    private Long productweight;
    private Double charge_amount;
    private String payment_method;
    @CreationTimestamp
    private LocalDateTime available_date;

    private String payment_status;

    private String courier_status;
    private String user_name;



    @CreationTimestamp
    private LocalDateTime created_at;




}
