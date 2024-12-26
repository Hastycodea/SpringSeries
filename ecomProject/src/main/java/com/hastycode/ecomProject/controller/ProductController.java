package com.hastycode.ecomProject.controller;

import com.hastycode.ecomProject.model.Product;
import com.hastycode.ecomProject.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ProductController {

    private ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/home")
    public String home() {
        return "Welcome home!";
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Product product = service.getProductById(id);

//      solved 302 error
        return product != null ? new ResponseEntity<>(product, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getImageById(@PathVariable int productId) {
        Product product = service.getProductById(productId);
        byte[] imageFile = product.getImageData();

        return ResponseEntity.ok()
//                .contentType(MediaType.valueOf(product.getImageType()))
                .body(imageFile);
    }

//    @PostMapping("/product")
//    public  ResponseEntity<Product> addProduct(@RequestBody Product product) {
//        Product product1 = service.addProduct(product);
//
////        if(product != null) {
//        if(product1 != null) {
//            return new ResponseEntity<>(product1, HttpStatus.CREATED);
//        }
//        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile) {
        try{
            System.out.println(product);
            Product product1 = service.addProduct(product, imageFile);
            return new ResponseEntity<>(product1, HttpStatus.CREATED);
        } catch (Exception e) {
            return  new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestPart Product product, @RequestPart MultipartFile imageFile) {
        Product product1 = null;
        try{
            product1 = service.updateProduct(id, product, imageFile);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
        }
        return product1 != null ? new ResponseEntity<>("Updated", HttpStatus.OK) : new ResponseEntity<>("Failed to Update", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        Product product = service.getProductById(id);

        if(product != null ) {
            service.deleteProductById(id);
            return  new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Item not found!", HttpStatus.NOT_FOUND);
        }


    }

}
