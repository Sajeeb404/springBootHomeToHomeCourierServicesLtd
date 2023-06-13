package com.example.HomeToHomeSpring.service;

import com.example.HomeToHomeSpring.dto.ReportDTO;
import com.example.HomeToHomeSpring.enums.CourierStatus;
import com.example.HomeToHomeSpring.enums.PaymentStatus;
import com.example.HomeToHomeSpring.model.ProductInformation;
import com.example.HomeToHomeSpring.repository.ProductInformationDaw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductInformationService {
    @Autowired
    private ProductInformationDaw productInformationDaw;

    LocalDateTime currentDate = LocalDateTime.now();
    LocalDateTime newDate = currentDate.plusDays(1);


    public void saveProduct(ProductInformation courier,  String username){
        courier.setUserName(username);
        courier.setCourierStatus(CourierStatus.Pending);

        if (courier.getPaymentMethod().equals("Online Payment")){
        courier.setPaymentStatus(PaymentStatus.Paid);
        }else {
            courier.setPaymentStatus(PaymentStatus.Due);
        }

        courier.setAvailableDate(newDate);
        productInformationDaw.save(courier);
    }


    public void updateCourier(ProductInformation products){
        if(products.getId()!=null){
            productInformationDaw.findById(products.getId()).map(old ->{
                old.setCourierStatus(products.getCourierStatus());
                return productInformationDaw.save(old);
            });

        }

    }

    public void updatePaySatus(ProductInformation products){
        if(products.getId()!=null){
            productInformationDaw.findById(products.getId()).map(old ->{
                old.setPaymentStatus(products.getPaymentStatus());
                return productInformationDaw.save(old);
            });

        }

    }




    public void deleteProduct(Long id){
        productInformationDaw.deleteById(id);
    }



    public List<ProductInformation>getAlls(String courierStatus, String username){
        if (username != null){
            return productInformationDaw.findAllByUserName(username);
        }
        if (courierStatus == null) {
            return productInformationDaw.findAll();
        } else {
            return productInformationDaw.findAllByCourierStatus(courierStatus);
        }
    }


    public List<ProductInformation>getAllss(){
        return productInformationDaw.findAll();
    }

    public List<ProductInformation> getById(Long id){
        return productInformationDaw.findAllByUserId(id);
    }



}
