package pe.tuna.seguridad;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
// Para usar las anotaciones en la autorizacion de cada metodo del controlador usamos esta etiqueta
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    // Para evitar el password error encode usar el {noop} en el password
    // Recordar que este metodo tiene que ser public
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("miguel").password("{noop}chinchay").roles("USER")
                .and().withUser("admin").password("{noop}admin").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Primer ejemplo para la autenticacion de todos los usuario
        // http.authorizeRequests().anyRequest().authenticated().and().httpBasic();

        /*
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/seguridad/hello").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.GET,"/seguridad/admin/hello").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/seguridad/admin/hello").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .httpBasic();
        */

        // Es mas conveniente la autorizacion a través del uso de las anotaciones
        // Para ello habilitamos la anotacion principal @EnaGlobalMethodSecu y usamos este metodo

        http.csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }
}
