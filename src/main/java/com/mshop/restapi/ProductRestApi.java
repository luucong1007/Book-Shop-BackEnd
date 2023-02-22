package com.mshop.restapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mshop.entity.Product;
import com.mshop.repository.ProductResository;

@CrossOrigin("*")
@RestController
@RequestMapping("api/products")
public class ProductRestApi {
	@Autowired
	ProductResository repo;
	
	@GetMapping
	public ResponseEntity<List<Product>> getAll() {
		return ResponseEntity.ok(repo.findAllStatusTrue());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Product> getOne(@PathVariable("id") Long id) {
		if(!repo.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		List<Product> list = repo.findAllStatusTrue();
		Product product = repo.findByIdAndStatusTrue(id);
		boolean check = false;
		for(Product p : list){
			if(p.getProductId() == product.getProductId()) {
				check = true;
			}
		};
		if(!check) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(product);
	}

	// Tìm kiếm theo tên
	@GetMapping("/search")
	public ResponseEntity<?> search(@RequestParam(name = "text") String text){
		List<Product> products = repo.search(text);
		return ResponseEntity.ok(products);
	}


	@PostMapping
	public ResponseEntity<Product> post(@RequestBody Product product) {
		if(repo.existsById(product.getProductId())) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(repo.save(product));
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Product> put(@PathVariable("id") Long id, @RequestBody Product product) {
		if(!id.equals(product.getProductId())) {
			return ResponseEntity.badRequest().build();
		}
		if(!repo.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(repo.save(product));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		if(!repo.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		Product pro = repo.findById(id).get();
		pro.setStatus(false);
		repo.save(pro);
//		repo.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/by-category/{id}")
	public ResponseEntity<List<Product>> getAllByCategory(@PathVariable("id") Long id) {
//		System.out.println(repo.findAllProductByCategoryId(id));
		return ResponseEntity.ok(repo.findAllProductByCategoryId(id));
	}
}