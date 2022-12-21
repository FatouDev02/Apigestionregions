package com.example.ApiTourist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8100", maxAge = 3600,allowCredentials="true")
@Controller
public class ActivitController {

}
