package org.drib.storagebot.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/v1/mock")
public class MockController {


    @GetMapping("/whoami")
    public String whoAmI() {
        log.debug("/whoami was called");
        return "You Are The Best!!";
    }

    @GetMapping("/env")
    public Map<String, String> getEnvironmentVariables() {
        return System.getenv();
    }
}
