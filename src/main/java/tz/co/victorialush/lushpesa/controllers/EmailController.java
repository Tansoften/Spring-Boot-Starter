package tz.co.victorialush.lushpesa.controllers;

import com.google.gson.Gson;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tz.co.victorialush.lushpesa.models.Email;
import tz.co.victorialush.lushpesa.models.EmailGroup;
import tz.co.victorialush.lushpesa.models.To;
import tz.co.victorialush.lushpesa.services.CreateFile;
import tz.co.victorialush.lushpesa.services.EmailService;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/emails")
@CrossOrigin(value = "http://localhost:5173")
public class EmailController {
    @Autowired
    EmailService emailService;
    @Autowired
    ResponseController response;

    @PostMapping(value = "/attachment/upload")
    public ResponseEntity<JSONObject> uploadAttachment(@RequestParam("file") MultipartFile file){
        try{
            String fileEtx = Objects.requireNonNull(Objects.requireNonNull(file.getOriginalFilename())).split("\\.")[1];
            String relativePath = UUID.randomUUID()+"."+fileEtx;
            Path path = Paths.get(relativePath);
            file.transferTo(path);
            response.setSuccess(true);
            response.setMessage("Attachment was uploaded.");
            response.setData(relativePath);
            response.setStatus(HttpStatus.CREATED);
        }catch (IOException exc){
        }

        return ResponseEntity.status(response.getStatus()).body(response.getResponseBody());
    }

    @PostMapping(value = "/send")
    public ResponseEntity<JSONObject> sendEmails(@Autowired CreateFile fileCreator, @RequestBody String body) {
        try{
            JSONParser jsonParser = new JSONParser();
            Gson gson = new Gson();
            JSONObject emailJson = (JSONObject) jsonParser.parse(body);
            EmailGroup emailGroup = gson.fromJson(String.valueOf(emailJson), EmailGroup.class);

            try{
                String filePath = fileCreator.generateAttachment(emailGroup.getAttachment());

                for(To to:emailGroup.getTo()){
                    //We dispatch emails in thread because we want it to be fast and not waiting for response so long
                    Thread thread = new Thread(() -> {
                        Email email = new Email();
                        email.setAddress(to.getAddress());
                        email.setCc(to.getCc());
                        email.setSubject(emailGroup.getSubject());
                        email.setBody(emailGroup.getBody());
                        email.setAttachment(filePath);
                        emailService.sendEmailWithAttachment(email, fileCreator.getFileName());
                        System.out.println("Dispatched thread "+Thread.currentThread().getName());
                    });

                    thread.setDaemon(true);
                    thread.start();
                    System.out.println(thread.getName());
                }
            }catch (IOException exc){
                System.out.println(exc.getMessage());
            }



            response.setSuccess(true);
            response.setMessage("Email(s) dispatched.");
            response.setData(null);
            response.setStatus(HttpStatus.OK);
        }
        catch (ParseException exc){
            System.out.println("Failed to change json array");
        }
        catch (Exception exc){
            System.out.println(exc);
        }

        return ResponseEntity.status(response.getStatus()).body(response.getResponseBody());
    }
}
