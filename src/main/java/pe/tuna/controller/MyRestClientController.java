package pe.tuna.controller;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

@Controller
@RequestMapping("clientRest")
public class MyRestClientController {
    final String urlUserHello = "http://localhost:8080/seguridad/hello";
    final String urlAdminHello = "http://localhost:8080/seguridad/admin/hello";

    @GetMapping("/userHello")
    public String userHello(Model model) {
        RestTemplate restTemplate = new RestTemplate();

        // Lo normal seria hacerlo de esta forma, pero si tenemos metodos de autenticacion no podemos usarlo
        // con lo cual nos daria un error, es por ello que debemos de usar exchange
        // ResponseEntity<?> response = restTemplate.getForEntity(urlUserHello, String.class);

        ResponseEntity<?> response = restTemplate.exchange(urlUserHello, HttpMethod.GET,
                new HttpEntity<String>(createHeaders("miguel", "chinchay")), String.class);

        model.addAttribute("saludo", response.getBody());

        return "apirest/userHello";
    }

    @GetMapping("/userAdmin")
    public String userAdmin(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<?> response = restTemplate.exchange(urlAdminHello, HttpMethod.GET,
                new HttpEntity<String>(createHeaders("admin", "admin")), String.class);

        model.addAttribute("saludo", response.getBody());

        return "apirest/adminHello";

    }


    @SuppressWarnings("serial")
    private HttpHeaders createHeaders(String username, String password) {
        return new HttpHeaders() {
            {
                String auth = username + ":" + password;
                byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("UTF-8")));
                String authHeader = "Basic " + new String(encodedAuth);
                set("Authorization", authHeader);
            }
        };
    }
}
