package rikkei.accademy.controller;

import rikkei.accademy.model.seriesfilm.Series;
import rikkei.accademy.service.seriesfilm.ISeriesFilmService;
import rikkei.accademy.service.seriesfilm.SeriesFilmServiceIMPL;
import rikkei.accademy.service.video.IVideoService;
import rikkei.accademy.service.video.VideoServiceIMPL;

import java.util.List;

public class SeriesFilmController {
    ISeriesFilmService seriesFilmService = new SeriesFilmServiceIMPL();
    IVideoService videoService = new VideoServiceIMPL();


    public List<Series> getListSeries(){
        return seriesFilmService.findAll();
    }
    public Series findByIdSeries(int id){
        return seriesFilmService.findById(id);
    }
    public void deleteSeries(int id){
        seriesFilmService.remove(id);
    }
    public void createSeries(Series series){
        seriesFilmService.save(series);
    }
    public void upDateSeries(Series series1){
        seriesFilmService.eDitSeries(series1);

    }


}
