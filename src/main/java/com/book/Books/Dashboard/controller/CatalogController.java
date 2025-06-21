package com.book.Books.Dashboard.controller;

import com.book.Books.Dashboard.dto.CatalogDto;
import com.book.Books.Dashboard.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/catalogs")
public class CatalogController {
    @Autowired
    private CatalogService catalogService;

    @PostMapping
    public CatalogDto addCatalog(@RequestBody CatalogDto catalogDto) {
        return catalogService.addCatalog(catalogDto);
    }


//    @GetMapping
//    public List<CatalogDto> getAllCatalogs() {
//        return catalogService.getAllCatalogs();
//    }
//
//    @PutMapping("/{id}")
//    public CatalogDto updateCatalog(@PathVariable Long id, @RequestBody CatalogDto catalogDto) {
//        return catalogService.updateCatalog(id, catalogDto);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteCatalog(@PathVariable Long id) {
//        catalogService.deleteCatalog(id);
//    }
}
