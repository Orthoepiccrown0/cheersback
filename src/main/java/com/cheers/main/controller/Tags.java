package com.cheers.main.controller;

import com.cheers.main.model.events.Tag;
import com.cheers.main.utils.DBManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class Tags {

    private DBManager dbManager;

    @Autowired
    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @GetMapping("/tags/all")
    public List<Tag> getAllTags() {
        List<Tag> tags = dbManager.getTagsService().getAllTags();
        Collections.shuffle(tags);
        return tags;
    }
}
