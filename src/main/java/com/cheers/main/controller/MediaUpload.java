package com.cheers.main.controller;

import com.cheers.main.model.enums.MediaType;
import com.cheers.main.utils.DBManager;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
public class MediaUpload {

    private DBManager dbManager;

    @Autowired
    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @PostMapping("m/save")
    public com.cheers.main.model.Media saveMedia(@RequestParam MultipartFile file, String type) {
        com.cheers.main.model.Media media = new com.cheers.main.model.Media();

        String path = System.getProperty("user.dir") + "\\media";
        String filename = UUID.randomUUID().toString();

        media.setId(filename);

        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        filename = filename + "." + extension;

        try {
            byte[] barr = file.getBytes();

            String folder = new SimpleDateFormat("ddMMyyyy").format(new Date());
            Files.createDirectories(Paths.get(path + "\\" + folder));
            BufferedOutputStream bout = new BufferedOutputStream(
                    new FileOutputStream(path + "\\" + folder + "\\" + filename));
            bout.write(barr);
            bout.flush();
            bout.close();

            media.setUrl(folder + "/" + filename);

            if (type != null) {
                if (type.equals("img"))
                    media.setType(MediaType.IMAGE);
                else if (type.equals("vid"))
                    media.setType(MediaType.VIDEO);
                else
                    media.setType(MediaType.IMAGE);
            } else {
                media.setType(MediaType.IMAGE);
            }
            dbManager.getMediaService().saveFile(media);

        } catch (Exception e) {
            System.out.println(e);
        }
        return media;
    }


}
