package yenduGroup.EcropMaster.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yenduGroup.EcropMaster.DTO.ProducteurDto;
import yenduGroup.EcropMaster.Services.ProducteurService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/producteur")
@CrossOrigin("*")
public class ProducteurController {
    private final ProducteurService producteurService;
    public ProducteurController(ProducteurService producteurService) {
        this.producteurService = producteurService;
    }

    @PostMapping("/create")
    public ResponseEntity<ProducteurDto> createProducteur(@RequestBody ProducteurDto producteurDto) {
        ProducteurDto createdProducteur = producteurService.createProducteur(producteurDto);
        if (createdProducteur != null) {
            return new ResponseEntity<>(createdProducteur, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<ProducteurDto>> getAllProducteurs() {
        List<ProducteurDto> producteurs = producteurService.getAllProducteur();
        return new ResponseEntity<>(producteurs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProducteurDto> getProducteurById(@PathVariable Long id) {
        Optional<ProducteurDto> producteurOptional = producteurService.getProducteurById(id);

        return producteurOptional
                .map(producteur -> new ResponseEntity<>(producteur, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<ProducteurDto> updateProducteur(@PathVariable Long id, @RequestBody ProducteurDto producteurDto) {
        ProducteurDto updatedProducteur = producteurService.updateProducteur(id, producteurDto);
        if (updatedProducteur != null) {
            return new ResponseEntity<>(updatedProducteur, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProducteur(@PathVariable Long id) {
        try {
            producteurService.deleteProducteur(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

