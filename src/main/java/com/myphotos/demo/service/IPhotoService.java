package com.myphotos.demo.service;

import com.myphotos.demo.model.Photo;

import java.util.ArrayList;
import java.util.Optional;

public interface IPhotoService {

    public Iterable<Photo> getAll();
    public Optional<Photo> getById(int id);
    public Photo create( Photo photo);
    public Optional<Photo> edit(int id, Photo photo);

    public Boolean delete(int id);

}
