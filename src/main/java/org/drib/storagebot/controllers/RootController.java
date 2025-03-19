package org.drib.storagebot.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD}, path = "/")
    public String root() {
        return "It is a storage application";
    }

}
