package tz.co.victorialush.lushpesa.controllers;

import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ResponseController {
    private boolean success;
    private String message;
    private Object data;
    private HttpStatus status;

    public ResponseController(){
        success = true; message = "You reached server!"; data = null; status = HttpStatus.OK;
    }

    @RequestMapping(path = "/un-authenticated")
    @CrossOrigin(value = "http://localhost:5173", allowCredentials = "true", maxAge = 3600)
    public ResponseEntity<JSONObject> unAuthenticated(){
        success = false;
        message = "Un authenticated";
        data = null;
        status = HttpStatus.UNAUTHORIZED;

        return ResponseEntity.status(status).body(getResponseBody());
    }

    public JSONObject getResponseBody(){
        Map<String, Object> resData = new HashMap<>();
        resData.put("success", success); resData.put("message", message); resData.put("data", data);
        return new JSONObject(resData);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
