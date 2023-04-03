package rikkei.accademy.model.VideoModel;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class Video implements Serializable, Comparable<Video>{
    static final long serialVersionUID = 1L;
    private int id;
    private String nameVideo;
    private int view;
    private String dateByVideo;
    private LocalDate dateToUp;
    private String country;
    private List<Category> categories;
    private boolean seriesVideo = false;

    public Video() {
    }

    public Video(int id, String nameVideo, int view,String dateByVideo, LocalDate dateToUp, String country, List<Category> categories, boolean seriesVideo) {
        this.id = id;
        this.nameVideo = nameVideo;
        this.view = view;
        this.dateByVideo = dateByVideo;
        this.dateToUp = dateToUp;
        this.country = country;
        this.categories = categories;
        this.seriesVideo = seriesVideo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameVideo() {
        return nameVideo;
    }

    public void setNameVideo(String nameVideo) {
        this.nameVideo = nameVideo;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public String getDateByVideo() {
        return dateByVideo;
    }

    public void setDateByVideo(String dateByVideo) {
        this.dateByVideo = dateByVideo;
    }

    public LocalDate getDateToUp() {
        return dateToUp;
    }

    public void setDateToUp(LocalDate dateToUp) {
        this.dateToUp = dateToUp;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public boolean isSeriesVideo() {
        return seriesVideo;
    }

    public void setSeriesVideo(boolean seriesVideo) {
        this.seriesVideo = seriesVideo;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", nameVideo='" + nameVideo + '\'' +
                ", view=" + view +
                ", dateByVideo='" + dateByVideo + '\'' +
                ", dateToUp=" + dateToUp +
                ", country='" + country + '\'' +
                ", categories=" + categories +
                ", seriesVideo=" + seriesVideo +

                '}';
    }

    @Override
    public int compareTo(Video o) {
        return 0;
    }

//    @Override
//    public int compareTo(Video o) {
////
//        return o;
////        return o.getDateByVideo() - this.getDateByVideo();
//    }

}
