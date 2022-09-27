package br.dev.airtonlima.service;

import br.dev.airtonlima.dto.ProductDTO;
import br.dev.airtonlima.entity.ProductEntity;
import br.dev.airtonlima.repository.ProductRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProductService {
    @Inject
    ProductRepository repository;

    public List<ProductDTO> listAll() {
        List<ProductDTO> products = new ArrayList<>();
        repository.findAll()
                .stream()
                .forEach(product -> products.add(mapToProductDTO(product)));
        return products;
    }

    public void create(ProductDTO dto) {
        ProductEntity product = mapToProductEntity(dto);
        repository.persist(product);
    }

    public void update(Long id, ProductDTO dto) {
        ProductEntity entity = repository.findById(id);
        entity.setName(dto.getName());
        entity.setCategory(dto.getCategory());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setModel(dto.getModel());
        repository.persist(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private ProductDTO mapToProductDTO(ProductEntity entity) {
        ProductDTO dto = new ProductDTO();
        dto.setName(entity.getName());
        dto.setCategory(entity.getCategory());
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());
        dto.setModel(entity.getModel());
        return dto;
    }

    private ProductEntity mapToProductEntity(ProductDTO dto) {
        ProductEntity entity = new ProductEntity();
        entity.setName(dto.getName());
        entity.setCategory(dto.getCategory());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setModel(dto.getModel());
        return entity;
    }
}
