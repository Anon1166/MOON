package int221.MOON.service;

import int221.MOON.entities.Categories;
import int221.MOON.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CategoriesService {
    @Autowired
    private CategoriesRepository categoriesRepository;

    public Categories getIdCategories(Integer id) {
        Categories categories = categoriesRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categories id " + id + "does not exist !!!"));
        return categories;
    }

    public List<Categories> findAll(){
        return categoriesRepository.findAll();
    }
}
