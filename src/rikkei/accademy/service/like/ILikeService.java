package rikkei.accademy.service.like;

import rikkei.accademy.model.Like;
import rikkei.accademy.service.IGenericService;

import java.util.List;

public interface ILikeService extends IGenericService<Like> {
    int getLikeNumberByVideo(int id);
    boolean checkLike(int id);
//    List<Like> sortByLike();
}
