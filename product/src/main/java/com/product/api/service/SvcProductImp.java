package com.product.api.service;

import java.nio.file.Files;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.product.api.dto.DtoProductIn;
import com.product.api.dto.DtoProductListOut;
import com.product.api.dto.DtoProductOut;
import com.product.api.entity.Product;
import com.product.api.entity.ProductImage;
import com.product.api.repository.RepoProduct;
import com.product.api.repository.RepoProductImage;
import com.product.commons.ApiResponse;
import com.product.commons.MapperProduct;
import com.product.exception.ApiException;
import com.product.exception.DBAccessException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;

@Service
public class SvcProductImp implements SvcProduct{
	
	@Autowired
	RepoProduct repo;
	
	@Autowired
	MapperProduct mapper;
	
	@Autowired
	RepoProductImage repoProductImage;
	
	@Value("${app.upload.dir}")
    private String uploadDir;

	@Override
	public ResponseEntity<List<DtoProductListOut>> getProducts() {
		try {
			List<Product> products = repo.findAll();
			return new ResponseEntity<>(mapper.fromProductList(products), HttpStatus.OK);
		}catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
	}

	@Override
    public ResponseEntity<DtoProductOut> getProduct(Integer id) {
        try {
            validateProductId(id);

            //return new ResponseEntity<>(null, HttpStatus.OK);
            DtoProductOut product = repo.getProduct(id);

            if(product == null )
                throw new ApiException(HttpStatus.NOT_FOUND, "El id del cliente no existe");

            List<ProductImage> l= getAllProducts(id);

             if (l == null || l.isEmpty()) {
                    System.out.println("No se encontraron imágenes en la base de datos para el producto ID: " + id);
                    product.setImage(new ArrayList<>()); 
                    return new ResponseEntity<>(product, HttpStatus.OK);
                }
            List<String> imagenes= new ArrayList<>();


            for (ProductImage pi : l) {
                if(pi.getStatus()!=0) {
                    String image = readProductImageFile(pi);
                    if (image != null && !image.isBlank()) {
                        imagenes.add(image);
                    } else {
                        System.out.println("No hay imagen : " + pi.getImage());
                    }
                    //imagenes.add(image);

                }

            }


            //String image = readProductImageFile(id);
            product.setImage(imagenes);

            return new ResponseEntity<>(product, HttpStatus.OK);

        }catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }

	@Override
	public ResponseEntity<ApiResponse> createProduct(DtoProductIn in) {
		try {
			Product product = mapper.fromDto(in);
			repo.save(product);
			ProductImage productImage = new ProductImage();
            productImage.setProductId(product.getProduct_id());
            productImage.setImage("");
            productImage.setStatus(1);

            repoProductImage.save(productImage);
			
			
			return new ResponseEntity<>(new ApiResponse("El producto ha sido registrado"), HttpStatus.CREATED);
		}catch (DataAccessException e) {
			if (e.getLocalizedMessage().contains("ux_product_gtin"))
				throw new ApiException(HttpStatus.CONFLICT, "El gtin del producto ya está registrado");
			if (e.getLocalizedMessage().contains("ux_product_product"))
				throw new ApiException(HttpStatus.CONFLICT, "El nombre del producto ya está registrado");
			if (e.getLocalizedMessage().contains("fk_product_category"))
				throw new ApiException(HttpStatus.NOT_FOUND, "El id de categoría no existe");

			throw new DBAccessException(e);
		}
	}

	@Override
	public ResponseEntity<ApiResponse> updateProduct(Integer id, DtoProductIn in) {
		try {
			validateProductId(id);
			Product product = mapper.fromDto(id, in);
			repo.save(product);
			return new ResponseEntity<>(new ApiResponse("El producto ha sido actualizado"), HttpStatus.OK);
		}catch (DataAccessException e) {
			if (e.getLocalizedMessage().contains("ux_product_gtin"))
				throw new ApiException(HttpStatus.CONFLICT, "El gtin del producto ya está registrado");
			if (e.getLocalizedMessage().contains("ux_product_product"))
				throw new ApiException(HttpStatus.CONFLICT, "El nombre del producto ya está registrado");
			if (e.getLocalizedMessage().contains("fk_product_category"))
				throw new ApiException(HttpStatus.NOT_FOUND, "El id de categoría no existe");

			throw new DBAccessException(e);
		}
	}

	@Override
	public ResponseEntity<ApiResponse> enableProduct(Integer id) {
		try {
			validateProductId(id);
			Product product = repo.findById(id).get();
			product.setStatus(1);
			repo.save(product);
			return new ResponseEntity<>(new ApiResponse("El producto ha sido activado"), HttpStatus.OK);
		}catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
	}

	@Override
	public ResponseEntity<ApiResponse> disableProduct(Integer id) {
		try {
			validateProductId(id);
			Product product = repo.findById(id).get();
			product.setStatus(0);
			repo.save(product);
			return new ResponseEntity<>(new ApiResponse("El producto ha sido desactivado"), HttpStatus.OK);
		}catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
	}
	
	private void validateProductId(Integer id) {
		try {
			if(repo.findById(id).isEmpty()) {
				throw new ApiException(HttpStatus.NOT_FOUND, "El id del producto no existe");
			}
		}catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
	}
	
	
	private String readProductImageFile(ProductImage productImage) {
        try {
            String imageUrl = productImage.getImage();
            if (imageUrl == null || imageUrl.isBlank()) return "";

            // Remover barra inicial
            if (imageUrl.startsWith("/")) imageUrl = imageUrl.substring(1);

            // Evitar que "uploads" se duplique
            if (imageUrl.startsWith("uploads/")) {
                imageUrl = imageUrl.substring("uploads/".length());
            }

            // Construir ruta correcta
            File imageFile = new File(uploadDir, imageUrl);

            if (!imageFile.exists()) return "";

            // Leer usando FileInputStream
            byte[] imageBytes = Files.readAllBytes(imageFile.toPath());
            return Base64.getEncoder().encodeToString(imageBytes);

        } catch (Exception e) {
            System.err.println("Error leyendo imagen: " + e.getMessage());
            return "";
        }
    }
	
	public List<ProductImage> getAllProducts(Integer id) {
        return repoProductImage.findAllProducts(id); // Recupera todas las filas de la tabla
    }
	
	
	
	

}
