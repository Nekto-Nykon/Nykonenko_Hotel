package nykon.pisspringboot.Controller;

import lombok.RequiredArgsConstructor;
import nykon.pisspringboot.Controller.Request.AuthenticationRequest;
import nykon.pisspringboot.Controller.Request.RegisterRequest;
import nykon.pisspringboot.Controller.Response.AuthenticationResponse;
import nykon.pisspringboot.Service.Security.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authenticationService.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
    @GetMapping("/get")
    public ResponseEntity getText(){
        return ResponseEntity.ok("Super Text");
    }
}
