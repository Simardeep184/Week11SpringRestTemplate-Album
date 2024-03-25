package com.example.Week11SpringRestTemplateAlbum.services;

import com.example.Week11SpringRestTemplateAlbum.models.Album;
import com.example.Week11SpringRestTemplateAlbum.repositories.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;

    private final RestTemplate restTemplate;

    private final String EXTERNAL_ALBUM_API = "https://jsonplaceholder.typicode.com/";

    private final String INTERNAL_DASHBOARD_API = "http://localhost:8081/dashboard/";
    @Autowired
    public AlbumService(AlbumRepository albumRepository, RestTemplate restTemplate){

        this.albumRepository = albumRepository;
        this.restTemplate = restTemplate;
    }

    public List<Album> getAlbums(){
        ResponseEntity<List<Album>> response = this.restTemplate
                .exchange(EXTERNAL_ALBUM_API + "albums"
                        , HttpMethod.GET, null, new ParameterizedTypeReference<List<Album>>() {
                        });

        //save the albums to the database h2
        this.albumRepository.saveAll(response.getBody());

        // make a post request to the Dashboard microservice sending the albums data
        sendAlbumsToDashboard(response.getBody());

        return response.getBody();
    }

    //call internal dashboard microservice to send the albums and save data over there
    public void sendAlbumsToDashboard(List<Album> albums) {
        ResponseEntity<String> response = this.restTemplate.postForEntity(INTERNAL_DASHBOARD_API + "saveAlbum",
                albums, String.class);

        if(response.getStatusCode().is2xxSuccessful()){
            System.out.println("Albums save to Dashboard microservice successfully from Album microservice");
        }
    }
}
