package com.cheers.main.service;

import com.cheers.main.model.events.Tag;

import java.util.List;

public interface ITagsService {

    List<Tag> getAllTags();

    void saveTag(Tag tag);

}
