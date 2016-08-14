package org.lenin.app;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @RequestMapping(value="/greeting",produces = { MediaType.APPLICATION_JSON_VALUE})
    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
    	return "Hello "+name;
    }
}
