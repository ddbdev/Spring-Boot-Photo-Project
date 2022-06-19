package com.myphotos.demo.repository;

import com.myphotos.demo.model.Photo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPhotoRepository extends CrudRepository<Photo, Integer>{



}
