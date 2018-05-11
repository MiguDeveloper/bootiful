package pe.tuna.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import pe.tuna.models.PostBean;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

// Ahora vamos a construir un cliente REST
@Controller
@RequestMapping("/json")
public class MyRestController {
    final String urlWS = "https://jsonplaceholder.typicode.com/posts";

    @GetMapping("/list")
    public String getPost(Model model) {
        enableSSL();
        RestTemplate restTemplate = new RestTemplate();
        PostBean[] lista = restTemplate.getForObject(urlWS, PostBean[].class);
        model.addAttribute("lstPost", lista);
        return "post/lst";
    }

    private void enableSSL() {
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                    }

                    @Override
                    public void checkServerTrusted( X509Certificate[] x509Certificates, String s) throws CertificateException {

                    }

                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                }
        };

        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {

        }
    }
}