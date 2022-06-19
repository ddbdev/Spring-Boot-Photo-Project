package com.myphotos.demo.controller.api;

import com.myphotos.demo.model.Photo;
import com.myphotos.demo.service.IPhotoService;
import com.myphotos.demo.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class AdminPhotoController {

    @Autowired
    @Qualifier("mainPhotoService")
    private IPhotoService photoService;

    public AdminPhotoController() {
        photoService = new PhotoService();
    }


    @RequestMapping("/admin/api/photos")
    public Iterable<Photo> getAll(){
        return photoService.getAll();
    }

    @RequestMapping("/admin/api/photos/{id}")
    public Photo getById(@PathVariable int id){

        Optional<Photo> photo = photoService.getById(id);

        if (photo.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "item not found");
        }

        return photo.get();
    }


    @RequestMapping(value="/admin/api/photos", method = RequestMethod.POST)
    public Photo create(@Valid @RequestBody Photo photo){
        return photoService.create(photo);
    }

    @RequestMapping(value="/admin/api/photos/{id}", method = RequestMethod.PUT)
    public Optional<Photo> edit(@PathVariable int id,@Valid @RequestBody Photo photo){

        Optional<Photo> updatedPhoto = photoService.edit(id,photo);

        if (updatedPhoto.isEmpty()){
            return Optional.empty();
        }

        return updatedPhoto;
    }

    @RequestMapping(value="/admin/api/photos/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id){

        Boolean isDeleted = photoService.delete(id);

        if (!isDeleted){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "item not found");
        }

    }

}
