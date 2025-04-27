package com.example.coindeskdemo.controller;

import com.example.coindeskdemo.dto.CoindeskDto;
import com.example.coindeskdemo.service.CoindeskService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/coindesk")
public class CoindeskController {
    @Autowired private CoindeskService service;
    @GetMapping("/transformed")
    public CoindeskDto getData() { return service.getTransformedData(); }
}
