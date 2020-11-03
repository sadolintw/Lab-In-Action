package me.lab.in.action.azure.controller;

import com.azure.storage.common.StorageSharedKeyCredential;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @Value("${azure.username}")
    String username;

    @Value("${azure.key}")
    String key;

}
