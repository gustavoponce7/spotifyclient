package com.example.spotifyclient;

import com.example.spotifyclient.domain.Artist;
import com.example.spotifyclient.service.ArtistService;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

@PageTitle("Spotify")
@Route(value = "spotify")
public class MainView extends HorizontalLayout {

    private ArtistService artistService;


    @Autowired
    public MainView(ArtistService artistService) {
        this.artistService = artistService;
        add(getTabs());
    }

    private Tabs getTabs() {
        Tab artists = getArtistsTab();
        Tab songs = new Tab("Songs");
        Tab playLists = new Tab("Play Lists");

        Tabs tabs = new Tabs(artists, songs, playLists);
        return tabs;
    }

    private Tab getArtistsTab() {
        try {
            List<Artist> allArtists = artistService.getAllArtists();
            Tab artistsTab = new Tab("Artists");
            artistsTab.add(allArtists.toString());
            return artistsTab;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
