package int221.MOON.controllers;

import int221.MOON.entities.Categories;
import int221.MOON.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/categories")
public class CategoriesController {
    @Autowired
    private CategoriesService categoriesService;

    @GetMapping("/{id}")
    public Categories getCategories(@PathVariable Integer id) {
        return categoriesService.getIdCategories(id);
    }


    @GetMapping("")
    public List<Categories> categoriesList(){
        return categoriesService.findAll();
    }
}
