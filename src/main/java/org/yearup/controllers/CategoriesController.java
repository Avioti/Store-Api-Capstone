package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.CategoryDao;
import org.yearup.data.ProductDao;
import org.yearup.models.Category;
import org.yearup.models.Product;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("categories")
@CrossOrigin
public class CategoriesController
{
    private CategoryDao categoryDao;
    private ProductDao productDao;

    private static final String ID_PATH = "/{id}";
    private static final String PRODUCTS_PATH = "/{Id}/products";


    // create an Autowired controller to inject the categoryDao and ProductDao
    @Autowired
    public CategoriesController(CategoryDao categoryDao, ProductDao productDao)
    {
        this.categoryDao = categoryDao;
        this.productDao = productDao;
    }
    @GetMapping
    public List<Category> getAll(@RequestParam(required = false) String name)
    {
        try{
            return categoryDao.getAllCategories();
        }
        catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }

    }

    @GetMapping(ID_PATH)
    @PreAuthorize("permitAll()")
    public Category getById(@PathVariable int id)
    {
        Category category = categoryDao.getById(id);
        if(category == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);}
        return category;
    }

    // the url to return all products in category 1 would look like this
    // https://localhost:8080/categories/1/products
    @GetMapping(PRODUCTS_PATH)
    public List<Product> getProductsById(@PathVariable int categoryId)
    {
        List<Product> products = productDao.listByCategoryId(categoryId);

        if(products == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);}

        return products;
    }

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Category addCategory(@RequestBody Category category)
    {
        try
        {
            return categoryDao.create(category);

        }
        catch(Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }

    @PutMapping(ID_PATH)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void updateCategory(@PathVariable int id, @RequestBody Category category)
    {
        try
        {
            categoryDao.update(id,category);
        }
        catch(Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }


    @DeleteMapping(ID_PATH)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteCategory(@PathVariable int id)
    {
        boolean deleted = categoryDao.delete(id);

        if(deleted){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
