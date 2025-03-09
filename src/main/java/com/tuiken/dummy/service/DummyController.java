package com.tuiken.dummy.service;

import com.tuiken.dummy.model.DummyDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@Slf4j
@CrossOrigin
@RequiredArgsConstructor
public class DummyController {


    private final DummyService dummyService;
    @GetMapping(path="/hello")
    public String hello() {
        return "Oi-7";
    }

    @GetMapping(path = "/person")
    public DummyDto findMonarchByUrl() {
        return dummyService.getPerson();
    }

}
