package pe.tuna.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seguridad")
public class EjemSeguridadController {

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/hello")
    public String sayHello(){
        return "Hola Mundo";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/hello")
    public String sayHelloForAdmins(){
        return "Hello Wordl, Mr Admin";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/hello")
    public String sunmitGreet(@RequestBody String greet){
        return "The received greet is " + greet;
    }
}
