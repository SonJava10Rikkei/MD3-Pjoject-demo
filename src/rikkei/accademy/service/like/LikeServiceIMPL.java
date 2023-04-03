package rikkei.accademy.service.like;

import rikkei.accademy.config.Config;
import rikkei.accademy.model.Like;
import rikkei.accademy.model.User;
import rikkei.accademy.service.user.IUserService;
import rikkei.accademy.service.user.UserServiceIMPL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LikeServiceIMPL implements ILikeService{

    IUserService userService = new UserServiceIMPL();
    static String PATH_LIKE = "src/rikkei/accademy/database/like.txt";
    static List<Like> likeList = new Config<Like>().readFile(PATH_LIKE);

    @Override
    public List<Like> findAll() {
        new Config<Like>().writeFile(PATH_LIKE,likeList);
        return likeList;
    }
    public void upDateData(){
        new Config<Like>().writeFile(PATH_LIKE,likeList);
    }

    @Override
    public void save(Like like) {
        likeList.add(like);
        upDateData();

    }

    @Override
    public Like findById(int id) {
        for (Like like: likeList) {
            if (like.getId() == id){
                return like;
            }
        }
        return null;
    }

    @Override
    public void remove(int id) {
        Like likeDelete = null;
        for (Like like: likeList) {
            if (like.getIdUser() == userService.getCurrenUser().getId() && like.getIdVideo() == id ){
                likeDelete = like;
            }
        }
        likeList.remove(likeDelete);
        upDateData();
    }

    @Override
    public int getLikeNumberByVideo(int id) {
        int count = 0;
        for (Like like: likeList) {
            if (like.getIdVideo() == id){
                count++;
            }
        }
        return count;
    }

    @Override
    public boolean checkLike(int id) {
        User current = userService.getCurrenUser();
        for (Like like:
             likeList) {
            if (like.getIdVideo() == id && like.getIdUser() == current.getId()){
                return true;
            }
        }
        return false;
    }

//    @Override
//    public List<Like> sortByLike() {
//        List<Like> listSortLike = new ArrayList<>();
//        for (int i = 0; i < likeList.size(); i++) {
//            listSortLike.add(likeList.get(i));
//        }
//        Collections.sort(listSortLike);
//        System.out.println();
//        List<Like> topLike = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            topLike.add(listSortLike.get(i));
//        }
//         return topLike;
//    }
}
