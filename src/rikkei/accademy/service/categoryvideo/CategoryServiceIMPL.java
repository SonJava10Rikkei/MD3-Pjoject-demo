package rikkei.accademy.service.categoryvideo;

import rikkei.accademy.config.Config;
import rikkei.accademy.model.VideoModel.Category;

import java.util.List;

public class CategoryServiceIMPL implements ICategoryService{

    static String PATH_CATEGORY = "src/rikkei/accademy/database/category.txt";
static List<Category> categories = new Config<Category>().readFile(PATH_CATEGORY);
    @Override
    public List<Category> findAll() {
        new Config<Category>().writeFile(PATH_CATEGORY,categories);
        return categories;
    }

    @Override
    public void save(Category category) {
        categories.add(category);
        new Config<Category>().writeFile(PATH_CATEGORY,categories);

    }

    @Override
    public Category findById(int id) {
       return categories.stream().filter(category -> category.getId() == id).findAny().orElse(null);
    }

    @Override
    public void remove(int id) {
        categories.remove(findById(id));
        new Config<Category>().writeFile(PATH_CATEGORY,categories);

    }


}
