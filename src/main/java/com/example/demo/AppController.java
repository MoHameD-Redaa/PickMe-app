
package com.example.demo;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping(value = "/app", produces = MediaType.APPLICATION_JSON_VALUE)
public class AppController {

    RegistrationClass registration;
    LoginClass login;

    public AppController() throws InterruptedException {
        registration = new RegistrationClass();
        login = new LoginClass();
    }

    @PostMapping("/signUpClient")
    public boolean signUpClient(@RequestBody Client client) {
        return registration.signUpClient(client);
    }


    @PostMapping("/signUpDriver")
    public boolean signUpDriver(@RequestBody Driver driver) {
        return registration.signUpDriver(driver);
    }


    @PostMapping("/loginAsAdmin/{username}/{password}")
    public RedirectView loginAsAdmin(@PathVariable String username, @PathVariable String password) throws InterruptedException {
        boolean valid = AppSystem.isValidAdmin(username, password);
        if (!valid) {
            //System.out.println("Wrong username or password");
            return new RedirectView("/app/loginAsAdmin");

        } else {
            //System.out.println("\t\tWelcome " + username + "\n");
            Admin admin = login.database.getAdmin(username);
            //return new RedirectView("admin/" + admin.getUsername());
            return new RedirectView("/admin/" + admin.getUsername());
        }

    }

    @PostMapping("/loginAsClient/{username}/{password}")
    public RedirectView loginAsClient(@PathVariable String username,@PathVariable String password) throws InterruptedException {
        boolean valid = AppSystem.isValidClient(username, password);
        if (!valid) {
            //System.out.println("Wrong username or password");
            return new RedirectView("/app/loginAsClient");
        } else {
            //System.out.println("\t\tWelcome " + username + "\n");
            Client client = login.database.getClient(username);
            //AppSystem.curClient = client;
            return new RedirectView("/Client/" + client.getUsername());
        }
    }

    @PostMapping("/loginAsDriver/{username}/{password}")
    public RedirectView loginAsDriver(@PathVariable String username,@PathVariable  String password) throws InterruptedException {
        boolean valid = AppSystem.isValidDriver(username, password);
        if (!valid) {
            //System.out.println("Wrong username or password");
            return new RedirectView("/app");
        } else {
            //System.out.println("\t\tWelcome " + username + "\n");
            Driver driver = login.database.getDriver(username);
            //AppSystem.curDriver = driver;
            return new RedirectView("/Driver/" + driver.getUsername());
        }
    }
}





