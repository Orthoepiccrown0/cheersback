package com.cheers.main.service.impl;

import com.cheers.main.model.Tag;
import com.cheers.main.repository.TagRepository;
import com.cheers.main.service.ITagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagsService implements ITagsService {

    private TagRepository tagRepository;

    @Autowired
    public void setTagRepository(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    @Override
    public void saveTag(Tag tag) {
        tagRepository.save(tag);
    }
}
