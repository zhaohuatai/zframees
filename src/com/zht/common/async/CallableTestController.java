package com.zht.common.async;

import java.util.concurrent.Callable;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/async/callable")
public class CallableTestController {
    @RequestMapping(value="/call", method=RequestMethod.GET)
    @ResponseBody
    public  Callable<String> callable() {
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(500);
                return "error";
            }
        };
    }
    
    @RequestMapping(value="/response/entity/headers", method=RequestMethod.GET)  
    public Callable<ResponseEntity<String>> responseEntityCustomHeaders() {  
  
        return new Callable<ResponseEntity<String>>() {  
            public ResponseEntity<String> call() throws Exception {  
  
                // Do some work..  
                Thread.sleep(3000L);  
  
                HttpHeaders headers = new HttpHeaders();  
                headers.setContentType(MediaType.TEXT_PLAIN);  
                return new ResponseEntity<String>(  
                        "The String ResponseBody with custom header Content-Type=text/plain", headers, HttpStatus.OK);  
            }  
        };  
    } 
    @RequestMapping("/exception")  
    public @ResponseBody Callable<String> exception() {  
  
        return new Callable<String>() {  
            public String call() throws Exception {  
  
                // Do some work..  
                Thread.sleep(2000L);  
  
                throw new IllegalStateException("Sorry!");  
            }  
        };  
    }  
    @ExceptionHandler  
    public @ResponseBody String handle(IllegalStateException e) {  
        return "IllegalStateException handled!";  
    }  
}
