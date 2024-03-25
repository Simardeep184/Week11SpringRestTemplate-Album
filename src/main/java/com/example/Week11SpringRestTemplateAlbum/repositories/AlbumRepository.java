package com.example.Week11SpringRestTemplateAlbum.repositories;

import com.example.Week11SpringRestTemplateAlbum.models.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {

}
