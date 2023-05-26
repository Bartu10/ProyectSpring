package com.example.proyectospring.Controller;

import com.example.proyectospring.Models.Order;
import com.example.proyectospring.Models.Product;
import com.example.proyectospring.Models.ProductOrder;
import com.example.proyectospring.Repositories.ProductOrderRepository;
import com.example.proyectospring.dto.OrderDto;
import com.example.proyectospring.dto.ProductDto;
import com.example.proyectospring.dto.ProductOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ProductOrderController {
    @Autowired
    ProductOrderRepository productOrderRepository;

    @GetMapping("/productsOrder/")
    public ResponseEntity<Object> index() {
        return new ResponseEntity<>(productOrderRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/productsOrder/{id}/")
    public  ResponseEntity<Object> show(@PathVariable("id") Long id) {
        return new ResponseEntity<>(productOrderRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping("/productsOrder/create")
    public ResponseEntity<Object> create(@RequestBody ProductOrderDto productOrderDto) {
        ProductOrder newProductOrder = new ProductOrder();

        newProductOrder.setCantidad(productOrderDto.getCantidad());
        return new ResponseEntity<>(productOrderDto, HttpStatus.OK);
    }




    @DeleteMapping("/productsOrder/{id}/")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        Optional<ProductOrder> productOrder = productOrderRepository.findById(id);
        productOrder.ifPresent(value -> productOrderRepository.delete(value));
        return new ResponseEntity<>(productOrder.isPresent(), HttpStatus.OK);
    }

    @PutMapping("/productsOrder/{id}/")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody ProductOrder productOrder) {
        Optional<ProductOrder> oldProductOrder = productOrderRepository.findById(id);
        if(oldProductOrder.isPresent()) {
            productOrder.setId(id);
            productOrderRepository.save(productOrder);
            return new ResponseEntity<>(productOrder, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }
}
