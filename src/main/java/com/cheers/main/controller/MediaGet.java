package com.cheers.main.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class MediaGet {
    @GetMapping("/m/get/{folder}/{id}")
    public HttpEntity<byte[]> getMedia(@PathVariable String folder, @PathVariable String id) throws IOException {
        String path = System.getProperty("user.dir") + "\\media";
        path = String.format("%s\\%s\\%s", path, folder, id);
        Path rpath = Paths.get(path);
        ByteArrayResource arrayResource = new ByteArrayResource(Files.readAllBytes(rpath));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentLength(arrayResource.getByteArray().length);
        return new HttpEntity<>(arrayResource.getByteArray(), headers);
    }
}
