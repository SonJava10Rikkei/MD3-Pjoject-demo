package rikkei.accademy.model;

import java.io.Serializable;

public class Like implements Serializable,Comparable<Like> {
    static final long serialVersionUID = 1L;
    private int id;
    private int idVideo;
    private int idUser;

    public Like() {
    }

    public Like(int id, int idVideo, int idUser) {

        this.id = id;
        this.idVideo = idVideo;
        this.idUser = idUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(int idVideo) {
        this.idVideo = idVideo;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "like{" +
                "id=" + id +
                ", idVideo=" + idVideo +
                ", idUser=" + idUser +
                '}';
    }


    @Override
    public int compareTo(Like o) {
        return o.getIdVideo() - this.getIdVideo();
    }
}
