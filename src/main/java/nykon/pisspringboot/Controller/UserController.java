package nykon.pisspringboot.Controller;

import lombok.RequiredArgsConstructor;
import nykon.pisspringboot.Model.Order;
import nykon.pisspringboot.Security.JwtService;
import nykon.pisspringboot.Service.OrderService;
import nykon.pisspringboot.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final OrderService orderService;
    private final UserService userService;
    private final JwtService jwtService;
    @GetMapping ("/myorders")
    public ResponseEntity<List<Order>> getAllOrderByEmail(@RequestHeader(name="Authorization", required = false) String token){
        String email = jwtService.extractUsername(token);
        return ResponseEntity.ok(orderService.findAllByEmail(email));
    }

}
