package com.cheers.main.service.impl;

import com.cheers.main.model.Media;
import com.cheers.main.repository.MediaRepository;
import com.cheers.main.service.IMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MediaService implements IMediaService {

    private MediaRepository mediaRepository;

    @Autowired
    public void setMediaRepository(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    @Override
    public Media saveFile(Media file) {
        return mediaRepository.save(file);
    }
}
