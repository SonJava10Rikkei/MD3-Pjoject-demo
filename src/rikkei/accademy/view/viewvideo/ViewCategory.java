package rikkei.accademy.view.viewvideo;

import rikkei.accademy.config.Config;
import rikkei.accademy.controller.CategoryController;
import rikkei.accademy.model.VideoModel.Category;
import rikkei.accademy.view.viewuser.ViewHome;

import java.util.List;

public class ViewCategory {
    CategoryController categoryController = new CategoryController();
    List<Category> categories = categoryController.getListCategory();

    public void menuCategory(){
        System.out.println("***************CATEGORY*********************");
        System.out.println("1: Show list Category");
        System.out.println("2: Create Category");
        System.out.println("3: Delete category");
        System.out.println("4: Edit Category");
        System.out.println("5: Detail Category");
        System.out.println("6: Back ");


        int choice = Config.scanner().nextInt();
        switch (choice){
            case 1:
                formShowListCategory();
                break;
            case 2:
                formCreateCategory();
                break;
            case 3:
                formDeleteCategory();
                break;
            case 4:
                formEditCategory();
                break;
            case 5:
                detailCategory();
                break;
            case 6:
                new ViewHome();
                break;
            default:
                System.out.println("Invalid choice");

        }
        menuCategory();
    }

    private void formEditCategory() {
        System.out.println("Enter id to edit");
        int idEdit = Config.scanner().nextInt();
        if (categoryController.detailCategory(idEdit) == null){
            System.out.println("not found");
        }else {
            Category category = categoryController.detailCategory(idEdit);
            System.out.println("OLD category: " + category.getCategory());
            System.out.println("Enter category want to edit");
            String newCategory = Config.scanner().nextLine();
            if (!newCategory.trim().equals("")){
                category.setCategory(newCategory);
                return;
            }
            categoryController.editCategory(idEdit,category);

            System.out.println("Success");
        }

    }

    private void formDeleteCategory() {
        System.out.println("Enter id category to delete");
        int idCategory = Config.scanner().nextInt();
        if (categoryController.detailCategory(idCategory) == null){
            System.out.println("Not found");
        }else {
            System.out.println("Enter 1 to delete or 2 to not delete");
           int choiceDelete = Config.scanner().nextInt();
            switch (choiceDelete){
                case 1:
                    categoryController.deleteCategory(idCategory);
                    formShowListCategory();
                    categoryController.getListCategory();
                    break;
                case 2:
                    menuCategory();
                    break;
            }
        }
    }
    private void detailCategory(){
        System.out.println("Enter id Category");
        int idDetail = Config.scanner().nextInt();
        if (categoryController.detailCategory(idDetail) == null){
            System.out.println("Not Found");
        }else {
            Category category = categoryController.detailCategory(idDetail);
            System.out.println(category);
        }
        System.out.println("Enter quit to back or another enter key to continue");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("quit")){
            menuCategory();
        }
    }

    public void formShowListCategory() {
        System.out.printf("%-15s%s%n","id","category");
        for (Category category: categories) {
            System.out.printf("%-15d%s%n",category.getId() ,category.getCategory());
        }
    }

    private void formCreateCategory() {
        System.out.println("Enter Category ");
        String categoryName = "";
        while (!categoryName.matches("(^[A-Za-z]{1,16})([ ]{0,1})([A-Za-z]{1,16})?([ ]{0,1})?([A-Za-z]{1,16})?([ ]{0,1})?([A-Za-z]{1,16})")) {
            categoryName = Config.scanner().nextLine();
            if (!categoryName.matches("(^[A-Za-z]{1,16})([ ]{0,1})([A-Za-z]{1,16})?([ ]{0,1})?([A-Za-z]{1,16})?([ ]{0,1})?([A-Za-z]{1,16})")) {
                System.out.println("Invalid name");
            }
        }
        int lastId;
        if (categories.isEmpty()){
            lastId = 1;
        }else {
            lastId = categories.get(categories.size() - 1).getId() + 1;
        }
        Category category = new Category(lastId,categoryName);
        categoryController.createCategory(category);

        System.out.println("Create Category Success!!!");
        System.out.println(categoryController.getListCategory());
        System.out.println("Enter quit to back or another enter key to continue");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("quit")) {
            menuCategory();


        }
    }
}
