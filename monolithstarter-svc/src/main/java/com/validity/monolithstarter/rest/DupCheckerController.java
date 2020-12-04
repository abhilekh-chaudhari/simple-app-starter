package com.validity.monolithstarter.rest;
import com.validity.monolithstarter.service.DupCheckerService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping("/check")
public class DupCheckerController {

    @Inject
    private DupCheckerService dupCheckerService;

    /**
     * Added CrossOrigin to connect localhost from Front-End 
     * @return
     */
    @CrossOrigin(origins = "*")
    @GetMapping("/dup")
    public String getRefinedData() {
        return dupCheckerService.getRefinedData();
    }
}
