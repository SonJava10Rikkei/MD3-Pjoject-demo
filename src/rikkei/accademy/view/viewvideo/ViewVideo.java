package rikkei.accademy.view.viewvideo;

import rikkei.accademy.config.Config;
import rikkei.accademy.controller.CategoryController;
import rikkei.accademy.controller.LikeController;
import rikkei.accademy.controller.UserController;
import rikkei.accademy.controller.VideoController;
import rikkei.accademy.model.Like;
import rikkei.accademy.model.User;
import rikkei.accademy.model.VideoModel.Category;
import rikkei.accademy.model.VideoModel.Video;
import rikkei.accademy.view.viewuser.ViewHome;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ViewVideo {
    UserController userController = new UserController();
    VideoController videoController = new VideoController();
    CategoryController categoryController = new CategoryController();
    List<Video> videoList = videoController.getListVideo();
    List<Category> categories = categoryController.getListCategory();
    LikeController likeController = new LikeController();
    List<Like> likeList = likeController.getListLike();

    public void menuVideo() {
        System.out.println("******************VIDEO******************");
        System.out.println("1: Show list Video");
        System.out.println("2: Create Video");
        System.out.println("3: Delete Video");
        System.out.println("4: Update video");
        System.out.println("5: Detail Video");
        System.out.println("6: Show list video with category");
        System.out.println("7: Top View Video");
        System.out.println("8: Show phim lẻ");
        System.out.println("9: Show Phim Bộ");
        System.out.println("10: Search Film by name");
        System.out.println("11: Search Film by category");
        System.out.println("12: Like video");
        System.out.println("13: Top Like");
        System.out.println("14: List film by date");
        System.out.println("15: Back");

        int choice = 0;
        try {
            choice = Integer.parseInt(Config.scanner().nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice");
            menuVideo();
        }
        switch (choice) {
            case 1:
                formShowListVideo();
                break;
            case 2:
                formCreateVideo();
                break;
            case 3:
                formDeleteVideo();
                break;
            case 4:
                formUpDateVideo();
                break;
            case 5:
                formDetailVideo();
                break;
            case 6:
                formVideoWithCategory();
                break;
            case 7:
                formTopViewVideo();
                break;
            case 8:
                formShowFilm();
                break;
            case 9:
                formShowSeriesFilm();
                break;
            case 10:
                formSearchFilmWithName();
                break;
            case 11:
                formVideoWithCategory();
                break;
            case 12:
                formLikeVideo();
                break;
            case 13:
                formTopLikeVideo();
                break;
            case 14:
                formVideoByDate();
                break;
            case 15:
                new ViewHome();
                break;

        }
        menuVideo();
    }

   public void formVideoByDate() {
        for(Video video : videoController.getTopVideoByNgaySX()) {
            System.out.println(video.getNameVideo() + "   " + video.getDateByVideo());
        }
    }


    public void formTopLikeVideo() {
        for(int id : likeController.topLike()){
            Video video = videoController.findByIdVideo(id);
            System.out.println(video.getNameVideo() + " :Like:  " + likeController.getLikeByVideoId(video.getId()).size());
        }


    }

   public void formLikeVideo() {
        User currentUser = userController.grtCurrenUser();
        for (Video video: videoList) {
            System.out.println("ID: " +video.getId() + " :Name: " + video.getNameVideo()  + " :Like: " + likeController.getLikeNumberById(video.getId()));
        }
        System.out.println("Enter video id to show details: ");
        int idVideo = Integer.parseInt(Config.scanner().nextLine());
        int likeNumber = likeController.getLikeNumberById(idVideo);

        System.out.println(videoController.findByIdVideo(idVideo));
        System.out.println("Like: " + likeNumber);

        boolean checkLike = likeController.checkLike(idVideo);
        System.out.println(checkLike ? "Liked" : "Like");

        System.out.println("Enter 1 to Like or else to back");
        int choice = Integer.parseInt(Config.scanner().nextLine());
        if (choice == 1){
            if (checkLike){
                likeController.deleteLike(idVideo);
            }else {
                int idLike;
                if (likeList.isEmpty()){
                    idLike = 1;
                }else {
                    idLike = likeList.get(likeList.size() -1).getId() + 1;
                }
                likeController.createLike(new Like(idLike,idVideo,currentUser.getId()));
            }
        }
    }

//    public void formSearchFilmWithCategory() {
//        System.out.println("*******************Search by Category******************");
//        System.out.println(categoryController.getListCategory());
//        System.out.println("Enter id category");
//        int idCategory = Config.scanner().nextInt();
//        videoController.showVideoWithCategory(idCategory);
//    }

    public void formSearchFilmWithName() {
        System.out.println("**************Search by name********************");
        System.out.println("Enter name");
        String searchName = Config.scanner().nextLine();
        if (searchName.trim().equals("")) {
            System.out.println("Not found");
        } else if (videoController.searchVideoWithName(searchName).size() == 0){
            System.out.println("không có luôn");

        }else {
            System.out.println(videoController.searchVideoWithName(searchName));
        }

//        videoController.getListVideo();

    }

    public void formTopViewVideo() {
        System.out.println("********************TOP VIEW*********************");
        for (int i = 0; i < videoController.topView().size(); i++) {
            System.out.println("Video: " + videoController.topView().get(i).getNameVideo() + " view: " + videoController.topView().get(i).getView());
        }
        videoController.getListVideo();
    }

    private void formDetailVideo() {
        formShowListVideo();
        System.out.println("Enter id Video");
        int idDetail = Config.scanner().nextInt();
        if (videoController.findByIdVideo(idDetail) == null) {
            System.out.println("Not Found");
        } else {
            Video video = videoController.findByIdVideo(idDetail);
            video.setView(video.getView() + 1);

            System.out.printf("%-15s%-15s%-15s%-15s%-15s%-15s%-20s%-15s%n", "ID", "VideoName", "View", "DateByVideo", "DateToUp", "Country", "Category", "isSeries");
            System.out.printf("%-15d%-15s%-15s%-15s%-15s%-15s%-20s%-15s%n", video.getId(), video.getNameVideo(), video.getView(), video.getDateByVideo(), video.getDateToUp(), video.getCountry(), video.getCategories().stream().map(Category::getCategory).collect(Collectors.toList()), video.isSeriesVideo() ? "Phim bộ" : "Phim Lẻ");

//            System.out.println(video);
            videoController.getListVideo();
        }
//        formDetailVideo();
        System.out.println("Enter quit to back or another enter key to continue");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("quit")) {
            menuVideo();
        }
    }

    private void formUpDateVideo() {

        System.out.println("Enter idVideo to edit");
        int idEdit = Config.scanner().nextInt();
        if (videoController.findByIdVideo(idEdit) == null) {
            System.out.println("not found");
        } else {
            Video video = videoController.findByIdVideo(idEdit);
            System.out.println("OLD videoName: " + video.getNameVideo());
            System.out.println("OLD NSX: " + video.getDateByVideo());
            System.out.println("OLD Country: " + video.getCountry());
            System.out.println("OLD Category: " + video.getCategories().stream().map(Category::getCategory).collect(Collectors.toList()));
            System.out.println("OLD isSeries: " + video.isSeriesVideo());

            System.out.println("Enter new name to edit");
            String newNameVideo = Config.scanner().nextLine();
            if (newNameVideo.trim().equals("")) {
//                video.setNameVideo(newNameVideo);
                newNameVideo = video.getNameVideo();
            }

            System.out.println("Thêm mới ngày sản xuất cho phim");
            String newNsx = Config.scanner().nextLine();
            if (newNsx.trim().equals("")) {
//                video.setDateByVideo(newNsx);
                newNsx = video.getDateByVideo();

            }
            LocalDate dateToUp = LocalDate.now();
            System.out.println("Enter new country");
            String newCountry = Config.scanner().nextLine();
            if (newCountry.trim().equals("")) {
//                video.setCountry(newCountry);
                newCountry = video.getCountry();

            }
            List<Category> categoryList = video.getCategories();
            for (int i = 0; i < video.getCategories().size(); i++) {
                System.out.println(categoryList);
            }

//            for (int i = 0; i < categories.size(); i++) {
//                System.out.println("**" + categories.get(i).getId() + "**" + categories.get(i).getCategory());
//            }
            System.out.println("Enter id category want to edit");
            int idCategory = Config.scanner().nextInt();

            System.out.println(categoryController.getListCategory());
            System.out.println("Enter id category to edit");
            int idToEdit = Config.scanner().nextInt();
            for (int i = 0; i < categoryList.size(); i++) {
                if (categoryList.get(i).getId() == idCategory) {
                    categoryList.set(i, categoryController.detailCategory(idToEdit));
                }
                List<Category> listSelectCategory = new ArrayList<>();
                selectCategory(listSelectCategory);


            }
            System.out.println("Phim bộ 1 or phim lẻ 2");
            int phim = Config.scanner().nextInt();
            boolean series = false;
            if (phim == 1) {
                series = true;
            }

            Video video1 = new Video(video.getId(), newNameVideo, 0, newNsx, dateToUp, newCountry, categoryList, series);
            videoController.editVideo(video1);


            System.out.println("Success");
        }
    }

    private void formDeleteVideo() {
        System.out.println("Enter id video to delete");
        int idVideo = Config.scanner().nextInt();
        if (videoController.findByIdVideo(idVideo) == null) {
            System.out.println("Not found");
        } else {
            System.out.println("Enter 1 to delete or 2 to not delete");
            int choiceDelete = Config.scanner().nextInt();
            switch (choiceDelete) {
                case 1:
                    videoController.deleteVideo(idVideo);
                    formShowListVideo();
                    videoController.getListVideo();
                    break;
                case 2:
                    menuVideo();
                    break;
            }
        }
    }



    public void formVideoWithCategory() {
        for (int i = 0; i < categories.size(); i++) {
            System.out.println("**" + categories.get(i).getId() + "**" + categories.get(i).getCategory());
        }
        System.out.println("Enter id category");
        int idCategory = Config.scanner().nextInt();
//       videoController.showVideoWithCategory(idCategory);
        for (Video video : videoController.showVideoWithCategory(idCategory)) {

            if (video.getNameVideo() == null){
                System.out.println("Not found!!!!");
            }
            System.out.println("name: " +video.getNameVideo());
        }
        videoController.getListVideo();
    }


    private void formCreateVideo() {

        int lastId;
        if (videoList.isEmpty()) {
            lastId = 1;
        } else {
            lastId = videoList.get(videoList.size() - 1).getId() + 1;
        }
        System.out.println("Enter Video Name ");
        String videoName = "";
        while (!videoName.matches("(^[A-Za-z]{1,16})([ ]{0,1})([A-Za-z]{1,16})?([ ]{0,1})?([A-Za-z]{1,16})?([ ]{0,1})?([A-Za-z]{1,16})")) {
            videoName = Config.scanner().nextLine();
            if (!videoName.matches("(^[A-Za-z]{1,16})([ ]{0,1})([A-Za-z]{1,16})?([ ]{0,1})?([A-Za-z]{1,16})?([ ]{0,1})?([A-Za-z]{1,16})")) {
                System.out.println("Invalid name");
            }
        }
        System.out.println("Enter date by Video");
        String dateByVideo = "";
        while (!dateByVideo.matches("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]|(?:Jan|Mar|May|Jul|Aug|Oct|Dec)))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2]|(?:Jan|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec))\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)(?:0?2|(?:Feb))\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9]|(?:Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep))|(?:1[0-2]|(?:Oct|Nov|Dec)))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$")) {
            dateByVideo = Config.scanner().nextLine();
            if (!dateByVideo.matches("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]|(?:Jan|Mar|May|Jul|Aug|Oct|Dec)))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2]|(?:Jan|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec))\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)(?:0?2|(?:Feb))\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9]|(?:Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep))|(?:1[0-2]|(?:Oct|Nov|Dec)))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$")) {
                System.out.println("Invalid name");
            }
        }
        LocalDate dateToUp = LocalDate.now();

        System.out.println("Enter Country");
        String videoCountry = "";
        while (!videoCountry.matches("(^[A-Za-z]{1,16})([ ]{0,1})([A-Za-z]{1,16})?([ ]{0,1})?([A-Za-z]{1,16})?([ ]{0,1})?([A-Za-z]{1,16})")) {
            videoCountry = Config.scanner().nextLine();
            if (!videoCountry.matches("(^[A-Za-z]{1,16})([ ]{0,1})([A-Za-z]{1,16})?([ ]{0,1})?([A-Za-z]{1,16})?([ ]{0,1})?([A-Za-z]{1,16})")) {
                System.out.println("Invalid name");
            }
        }

        System.out.println("Phim bộ 1 or phim lẻ 2");
        int phim = Config.scanner().nextInt();
        boolean series = false;
        if (phim == 1) {
            series = true;
        }

        System.out.println("Add category");

        List<Category> listSelectCategory = new ArrayList<>();
        selectCategory(listSelectCategory);
        Video video = new Video(lastId, videoName, 0, dateByVideo, dateToUp, videoCountry, listSelectCategory, series);
        System.out.println("Create Success!!!!!!!");
        videoController.createVideo(video);
        videoController.getListVideo();
    }

    private void selectCategory(List<Category> listSelectCategory) {
        for (int i = 0; i < categories.size(); i++) {
            System.out.println("**" + categories.get(i).getId() + "**" + categories.get(i).getCategory());
        }
        System.out.println("Enter id of category to select:");
        int idSelect = Config.scanner().nextInt();
        Category category = categoryController.detailCategory(idSelect);
        listSelectCategory.add(category);
        System.out.println("Enter any key to continue - Enter quit to exit select Category");
        String exitSelect = Config.scanner().nextLine();
        if (exitSelect.equalsIgnoreCase("quit")) {
            return;
        } else {
            selectCategory(listSelectCategory);
        }

    }

    public void formShowListVideo() {
        System.out.println("****************************LIST VIDEO*****************************");

        System.out.printf("%-15s%-15s%-15s%-15s%-15s%-15s%-20s%-15s%n", "ID", "VideoName", "View", "DateByVideo", "DateToUp", "Country", "Category", "isSeries");
        for (Video video : videoList) {
            System.out.printf("%-15d%-15s%-15s%-15s%-15s%-15s%-20s%-15s%n", video.getId(), video.getNameVideo(), video.getView(), video.getDateByVideo(), video.getDateToUp(), video.getCountry(), video.getCategories().stream().map(Category::getCategory).collect(Collectors.toList()), video.isSeriesVideo() ? "Phim bộ" : "Phim Lẻ");
        }

    }

    public void formShowFilm() {
        System.out.println("*********** LIST PHIM LẺ******************");
        for (int i = 0; i < videoController.phimLe().size(); i++) {
            System.out.println(videoController.phimLe().get(i).getNameVideo() + ": Là: " + (videoController.phimLe().get(i).isSeriesVideo() ? "Phim bộ" : "Phim Lẻ"));
        }
    }

    public void formShowSeriesFilm() {
        System.out.println("************** LIST PHIM BỘ******************");
        for (int i = 0; i < videoController.phimBo().size(); i++) {
            System.out.println(videoController.phimBo().get(i).getNameVideo() + ": Là: " + (videoController.phimBo().get(i).isSeriesVideo() ? "Phim Bộ" : "Phim Lẻ"));
        }
    }
}
