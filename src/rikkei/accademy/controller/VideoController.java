package rikkei.accademy.controller;

import rikkei.accademy.model.VideoModel.Category;
import rikkei.accademy.model.VideoModel.Video;
import rikkei.accademy.service.categoryvideo.CategoryServiceIMPL;
import rikkei.accademy.service.categoryvideo.ICategoryService;
import rikkei.accademy.service.role.IRoleService;
import rikkei.accademy.service.role.RoleServiceIMPL;
import rikkei.accademy.service.video.IVideoService;
import rikkei.accademy.service.video.VideoServiceIMPL;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class VideoController {
    IRoleService roleService = new RoleServiceIMPL();
    IVideoService videoService = new VideoServiceIMPL();
    ICategoryService categoryService = new CategoryServiceIMPL();

    public List<Video> getListVideo() {
        return videoService.findAll();

    }

    public Video findByIdVideo(int id) {
        return videoService.findById(id);
    }

    public void createVideo(Video video) {
        videoService.save(video);
    }

    public void deleteVideo(int id) {
        videoService.remove(id);
    }


    public List<Video> showVideoWithCategory(int id) {
        List<Video> videoList = new ArrayList<>();
        for (Video video : videoService.findAll()) {
            for (Category category : video.getCategories()) {
                if (id == category.getId()) {
                    videoList.add(video);
                }
            }
        }
        return videoList;
    }

    public void editVideo(Video video1) {
        videoService.updateVideo(video1);
    }

    public List<Video> topView() {
        return videoService.sortByView();
    }

    public List<Video> phimBo() {
        return videoService.seriesVideo();
    }

    public List<Video> phimLe() {
        return videoService.video();
    }

    public List<Video> searchVideoWithName(String name) {
        return videoService.searchVideoWithName(name);
    }


//    public List<Video> filmByDate(){
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy '@'hh:mm a");
//        Collections.sort(datestring, (s1, s2) -> LocalDateTime.parse(s1, formatter).
//                compareTo(LocalDateTime.parse(s2, formatter)));
//
//    }

    public List<Video> getTopVideoByNgaySX() {

        return videoService.findAll().stream().sorted((v1,v2)->parse(v2.getDateByVideo()).compareTo(parse(v1.getDateByVideo()))).collect(Collectors.toList());
    }
    private Date parse(String string) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(videoService.findAll().get(0).getDateByVideo());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
