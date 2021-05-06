package com.cheers.main.service;

import com.cheers.main.model.Tag;

import java.util.List;

public interface ITagsService {

    List<Tag> getAllTags();

    void saveTag(Tag tag);

}
