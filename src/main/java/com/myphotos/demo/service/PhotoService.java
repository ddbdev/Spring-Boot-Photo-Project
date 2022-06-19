package com.myphotos.demo.service;

import com.myphotos.demo.model.Photo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PhotoService implements IPhotoService{

    List<Photo> list ;
    private int lastId;
    public PhotoService(){
        list = new ArrayList<>();

        list.add(new Photo(1, "./img/27.png"));
        list.add(new Photo(2, "./img/26.png"));
        list.add(new Photo(3, "./img/25.png"));

        lastId = list.size();
    }

    @Override
    public Iterable<Photo> getAll(){
        return list;
    }

    @Override
    public Optional<Photo> getById(int id){

        Optional<Photo> photo = list.stream().filter(item->item.getId() == id).findFirst();

        return photo;
    }

    @Override
    public Photo create( Photo photo){
        lastId++;
        photo.setId(lastId);
        list.add(photo);
        return photo;
    }

    @Override
    public Optional<Photo> edit(int id, Photo photo){

        Optional<Photo> photoFound = list.stream().filter(item->item.getId() == id).findFirst();

        if (photoFound.isEmpty()){
            return Optional.empty();
        }

        photoFound.get().setUrl(photo.getUrl());

        return photoFound;
    }

    @Override
    public Boolean delete(int id){

        Optional<Photo> photoFound = list.stream().filter(item->item.getId() == id).findFirst();

        if (photoFound.isEmpty()){
            return false;
        }

        list.remove(photoFound.get());
        return true;
    }



}
