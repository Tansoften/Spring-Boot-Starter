package tz.co.victorialush.lushpesa.controllers;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tz.co.victorialush.lushpesa.models.Token;
import tz.co.victorialush.lushpesa.models.User;
import tz.co.victorialush.lushpesa.repositories.UsersRepository;

import java.util.Map;
import java.util.Optional;

@RequestMapping(value = "/api")
@RestController
@CrossOrigin(value = "http://localhost:5173", allowCredentials = "true", maxAge = 3600)
public class UserController {
    @Autowired
    ResponseController response;

    @GetMapping(value = "/profile")
    public ResponseEntity<JSONObject> getProfile(HttpServletRequest request, @RequestHeader Map<String, String> headers){
        Gson gson = new Gson();
        try{
            User user = (User) request.getAttribute("user");
            response.setSuccess(user != null);

            if(response.isSuccess()){
                response.setMessage("User profile was retrieved successfully.");
                response.setData(gson.toJson(user));
                response.setStatus(HttpStatus.OK);
            }else{
                response.setMessage("Un-authenticated");
                response.setData(null);
                response.setStatus(HttpStatus.UNAUTHORIZED);
            }

            return ResponseEntity.status(response.getStatus()).body(response.getResponseBody());
        }catch(Exception exc){
            System.out.println(exc.getMessage());
        }

        return ResponseEntity.status(response.getStatus()).body(response.getResponseBody());
    }
}
