package com.bristle.orderservice.controller;

import com.bristle.orderservice.model.OrderEntity;
import com.bristle.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "api/v1/customer-detail")
@RestController
public class OrderController {

    OrderService m_orderService;

    @Autowired
    public OrderController(OrderService m_orderService) {
        this.m_orderService = m_orderService;
    }

    @GetMapping("/getOrdersByLimitAndOffset")
    public ResponseEntity<List<OrderEntity>> getOrdersByLimitAndOffset(
            @RequestParam(name = "limit", required = false) int limit,
            @RequestParam(name = "offset", required = false) int offset
    ) {
        // This order table is eventually going to be huge
        // we should never consider fetching all rows without a limit
        int realLimit = limit == 0 ?limit:20;
        int realOffset = offset == 0 ? offset : 20;
        try {
            return new ResponseEntity<>(
                    m_orderService.getOrdersByLimitAndOffset(realLimit, realOffset), HttpStatus.ACCEPTED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

//    @PostMapping("/addOrder")
//    public ResponseEntity<?> addCustomer(
//            @RequestParam(name = "customerId", required = true) String customerId,
//            @RequestParam(name = "name", required = false) String name,
//            @RequestParam(name = "contactName", required = false) String contactName,
//            @RequestParam(name = "contactNumber", required = false) String contactNumber,
//            @RequestParam(name = "contactMobileNumber", required = false) String contactMobileNumber,
//            @RequestParam(name = "faxNumber", required = false) String faxNumber,
//            @RequestParam(name = "postalCode", required = false) String postalCode,
//            @RequestParam(name = "address", required = false) String address,
//            @RequestParam(name = "taxId", required = false) String taxId,
//            @RequestParam(name = "receiver", required = false) String receiver,
//            @RequestParam(name = "note", required = false) String note
//        )
//    {
//        try {
//            m_customerDetailService.addCustomer(new CustomerEntity(
//                    customerId,
//                    name,
//                    contactName,
//                    contactNumber,
//                    contactMobileNumber,
//                    faxNumber,
//                    postalCode,
//                    address,
//                    taxId,
//                    receiver,
//                    note
//            ));
//            return new ResponseEntity<>(HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}
