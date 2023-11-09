package com.example.spotifyclient.service;

import com.example.spotifyclient.domain.Artist;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class ArtistService {


    ObjectMapper mapper;

    public ArtistService(){
        this.mapper = new ObjectMapper();
    }

    public List<Artist> getAllArtists() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/artists"))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());
        List<Artist> artists = mapper.readValue(response.body(), new TypeReference<>() {});
        System.out.println(artists);
        return artists;
    }
}
