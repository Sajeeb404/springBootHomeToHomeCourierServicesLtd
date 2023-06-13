package com.example.HomeToHomeSpring.model;

import com.example.HomeToHomeSpring.enums.CourierStatus;
import com.example.HomeToHomeSpring.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductInformation extends BaseModel{


//    SenderVaiable
    private String p_senderName;
    private String  p_senderPhone;
    private String  p_senderAddress;
    private String  p_senderBranch;



//    ReciverVariable
    private String p_ReceiverName;
    private String p_ReceiverPhone;
    private String p_ReceiverAddress;
    private String p_ReceiverBranch;


//    courier item variable
    private String prodcuttype;
    private String productname;
    private Long productweight;
    private Double chargeAmount;
    private String paymentMethod;

//    @CreationTimestamp
    private LocalDateTime availableDate;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;


    @Enumerated(EnumType.STRING)
    private CourierStatus courierStatus;
    private String userName;




}
