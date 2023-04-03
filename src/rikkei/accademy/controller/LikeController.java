package rikkei.accademy.controller;

import rikkei.accademy.model.Like;
import rikkei.accademy.model.VideoModel.Video;
import rikkei.accademy.service.like.ILikeService;
import rikkei.accademy.service.like.LikeServiceIMPL;
import rikkei.accademy.service.video.IVideoService;
import rikkei.accademy.service.video.VideoServiceIMPL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LikeController {
    ILikeService likeService = new LikeServiceIMPL();
    IVideoService videoService = new VideoServiceIMPL();
    public int getLikeNumberById(int id){
        return likeService.getLikeNumberByVideo(id);
    }
    public List<Like> getListLike(){
        return likeService.findAll();
    }
    public void createLike(Like like){
        likeService.save(like);
    }
    public void deleteLike(int id){
        likeService.remove(id);
    }
    public boolean checkLike(int id){
        return likeService.checkLike(id);

    }
    public List<Integer> topLike () {
        Map<Integer, Integer> map = new HashMap<>();
        for (Video video : videoService.findAll()) {
            int like = getLikeByVideoId(video.getId()).size();
            map.put(video.getId(), like);
        }
        return map.keySet().stream().sorted((o1,o2)->map.get(o2).compareTo(map.get(o1))).collect(Collectors.toList());
    }


    public List<Like> getLikeByVideoId(int idVideo)  {
        List<Like> likeList = new ArrayList<>();
        for (Like like:
             getListLike()) {
            if (like.getIdVideo() == idVideo){
                likeList.add(like);
            }
        }

        return likeList;
    }

}
