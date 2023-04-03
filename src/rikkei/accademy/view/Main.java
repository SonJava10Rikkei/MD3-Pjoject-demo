package rikkei.accademy.view;

import rikkei.accademy.controller.UserController;
import rikkei.accademy.model.User;
import rikkei.accademy.service.video.VideoServiceIMPL;
import rikkei.accademy.view.viewuser.ViewHome;
import rikkei.accademy.view.viewuser.ViewMainMenu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    UserController userController = new UserController();

    public Main(){
        User currenUser = userController.grtCurrenUser();
        if (currenUser == null){
            new ViewMainMenu().menu();
        }else {
            new ViewHome();
        }

    }

    public static void main(String[] args) {
      new Main();
    }

}
