package rikkei.accademy.service.video;

import rikkei.accademy.config.Config;
import rikkei.accademy.model.User;
import rikkei.accademy.model.VideoModel.Category;
import rikkei.accademy.model.VideoModel.Video;
import rikkei.accademy.service.categoryvideo.CategoryServiceIMPL;
import rikkei.accademy.service.categoryvideo.ICategoryService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VideoServiceIMPL implements IVideoService{
    ICategoryService categoryService = new CategoryServiceIMPL();
    static String PATH_VIDEO = "src/rikkei/accademy/database/video.txt";
    static List<Video> videoList = new Config<Video>().readFile(PATH_VIDEO);
    @Override
    public List<Video> findAll() {
        new Config<Video>().writeFile(PATH_VIDEO,videoList);
        return videoList;
    }

    @Override
    public void save(Video video) {
        videoList.add(video);
        new Config<Video>().writeFile(PATH_VIDEO,videoList);

    }

    @Override
    public Video findById(int id) {

        return videoList.stream().filter(video -> video.getId() == id).findAny().orElse(null);
    }

    @Override
    public void remove(int id) {
        videoList.remove(findById(id));
        new Config<Video>().writeFile(PATH_VIDEO,videoList);

    }


    @Override
    public void updateVideo(Video video) {
     Video video1 = findById(video.getId());
     video1.setNameVideo(video.getNameVideo());
     video1.setDateByVideo(video.getDateByVideo());
     video1.setCountry(video.getCountry());
     video1.setCategories(video.getCategories());
     video1.setSeriesVideo(video.isSeriesVideo());
        new Config<Video>().writeFile(PATH_VIDEO,videoList);
    }

    @Override
    public List<Video> sortByView() {
        List<Video> view = new ArrayList<>();
        for (int i = 0; i < videoList.size(); i++) {
            view.add(videoList.get(i));
        }
        Collections.sort(view);
        List<Video> topView = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            topView.add(view.get(i));
        }

        return topView;
    }

    @Override
    public List<Video> seriesVideo() {
        List<Video> phimBo = new ArrayList<>();
        for (int i = 0; i < videoList.size(); i++) {
            if (videoList.get(i).isSeriesVideo() == true){
                phimBo.add(videoList.get(i));
            }
        }
        return phimBo;
    }

    @Override
    public List<Video> video() {
        List<Video> phimLe = new ArrayList<>();
        for (int i = 0; i < videoList.size(); i++) {
            if (videoList.get(i).isSeriesVideo() == false){
                phimLe.add(videoList.get(i));
            }
        }
        return phimLe;
    }



    @Override
    public List<Video> searchVideoWithName(String name) {
        List<Video> searchWithName = new ArrayList<>();

        for (int i = 0; i < videoList.size(); i++) {
            if (videoList.get(i).getNameVideo().toLowerCase().contains(name.toLowerCase()) ){
                searchWithName.add(videoList.get(i));
            }
        }
        return searchWithName;
    }

//    @Override
//    public List<Video> searchVideoWithCategory(Category category) {
//        List<Video> videos = new ArrayList<>();
//        for (Video video: videoList) {
//            for (Category category1: categoryService.findAll()) {
//                if (category.getId() == category1.getId()){
//                    videos.add(video);
//                    break;
//                }
//            }
//        }
//
//        return videos;
//    }


}
