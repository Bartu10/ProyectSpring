package com.example.proyectospring.Controller;

import com.example.proyectospring.Models.Order;
import com.example.proyectospring.Models.Product;
import com.example.proyectospring.Models.ProductOrder;
import com.example.proyectospring.Repositories.OrderRepository;
import com.example.proyectospring.Repositories.ProductOrderRepository;
import com.example.proyectospring.Repositories.ProductRepository;
import com.example.proyectospring.dto.OrderDto;
import com.example.proyectospring.dto.ProductDto;
import com.example.proyectospring.dto.ProductOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ProductOrderController {
    @Autowired
    ProductOrderRepository productOrderRepository;
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

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
        Product product = productRepository.findById(productOrderDto.getProductid()).get();
        Order order = orderRepository.findById(productOrderDto.getOrderid()).get();

        ProductOrder newProductOrder = new ProductOrder();
        newProductOrder.setOrder(order);
        newProductOrder.setProduct(product);
        newProductOrder.setCantidad(productOrderDto.getCantidad());
        productOrderRepository.save(newProductOrder);

        return new ResponseEntity<>(productOrderDto, HttpStatus.OK);
    }




    @DeleteMapping("/productsOrder/{id}/")
    @PreAuthorize("hasRole('SCOPE_ADMIN')")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        Optional<ProductOrder> productOrder = productOrderRepository.findById(id);
        productOrder.ifPresent(value -> productOrderRepository.delete(value));
        return new ResponseEntity<>(productOrder.isPresent(), HttpStatus.OK);
    }

    @PutMapping("/productsOrder/{id}/")
    @PreAuthorize("hasRole('SCOPE_ADMIN')")
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
