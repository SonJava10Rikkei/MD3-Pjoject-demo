package rikkei.accademy.controller;

import rikkei.accademy.model.VideoModel.Category;
import rikkei.accademy.service.categoryvideo.CategoryServiceIMPL;
import rikkei.accademy.service.categoryvideo.ICategoryService;
import rikkei.accademy.service.role.IRoleService;
import rikkei.accademy.service.role.RoleServiceIMPL;

import java.util.List;

public class CategoryController {
    ICategoryService categoryService = new CategoryServiceIMPL();
    IRoleService roleService = new RoleServiceIMPL();

    public List<Category> getListCategory(){
        return categoryService.findAll();
    }
    public void createCategory(Category category){
        categoryService.save(category);
    }

    public void deleteCategory(int id){
        categoryService.remove(id);

    }
    public Category detailCategory(int id){
        return categoryService.findById(id);
    }
    public void editCategory(int id, Category category){
        Category category1 = categoryService.findById(id);
        category1.setCategory(category.getCategory());
        getListCategory();
    }

}
