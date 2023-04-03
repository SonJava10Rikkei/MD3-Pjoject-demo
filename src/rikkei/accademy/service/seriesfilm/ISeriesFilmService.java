package rikkei.accademy.service.seriesfilm;

import rikkei.accademy.model.seriesfilm.Series;
import rikkei.accademy.service.IGenericService;

public interface ISeriesFilmService extends IGenericService<Series> {
    void eDitSeries(Series series);
}
