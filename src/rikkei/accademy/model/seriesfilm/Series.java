package rikkei.accademy.model.seriesfilm;

import rikkei.accademy.model.VideoModel.Video;

import java.io.Serializable;
import java.util.List;

public class Series implements Serializable {
    private int id;
    private String name;

    private List<Video> videos;

    public Series() {
    }

    public Series(int id, String name, List<Video> videos) {
        this.id = id;
        this.name = name;
        this.videos = videos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    @Override
    public String toString() {
        return "Series{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", videos=" + videos +
                '}';
    }
}
