package nykon.pisspringboot.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nykon.pisspringboot.Controller.Request.CreateOrderData;
import nykon.pisspringboot.Controller.Request.UpdateOrder;
import nykon.pisspringboot.Model.Order;
import nykon.pisspringboot.Security.JwtService;
import nykon.pisspringboot.Service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/order")

public class OrderController {
    private final OrderService orderService;
    private final JwtService jwtService;

    @GetMapping("/all")
    public ResponseEntity<List<Order>> getAllOrder(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }
    @GetMapping("/all-to-upgrade-status")
    public ResponseEntity<List<Order>> getAllOrderToChangeStatus(){
        return ResponseEntity.ok(orderService.getAllOrdersToChangeStatus());
    }
    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(
            @RequestBody CreateOrderData orderData
//            @RequestHeader(name="Authorization") String token
    ){
//        String email = jwtService.extractUsername(token);
        return ResponseEntity.ok(orderService.createOrder(orderData.getIdRoom(), orderData.getEmail(), orderData.getCheckIn(), orderData.getCheckOut()));

    }
    @PatchMapping("/update")
    public ResponseEntity<Order> updateOrder(@RequestBody UpdateOrder updateData){
        //TODO make this url only for admin
        return ResponseEntity.ok(orderService.updateStatus(updateData.getIdOrder(),updateData.getStatus()));
    }


}
