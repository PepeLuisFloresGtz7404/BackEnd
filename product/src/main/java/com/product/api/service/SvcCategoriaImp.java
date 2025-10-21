package com.product.api.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.product.api.dto.DtoCategoryIn;
import com.product.api.entity.Categoria;
import com.product.api.repository.RepoCategoria;
import com.product.commons.ApiResponse;
import com.product.exception.ApiException;
import com.product.exception.DBAccessException;

@Service
public class SvcCategoriaImp implements SvcCategoria{

    @Autowired
    RepoCategoria repo;



	@Override
	public List<Categoria> findAll() {
		try {
            return repo.findAll();
        }catch (DataAccessException e) {
            throw new DBAccessException();
        }
	}


	@Override
	public List<Categoria> findActive() {
		try {
            return repo.findActive();
        }catch (DataAccessException e) {
            throw new DBAccessException();
        }
	}


	@Override
	public ApiResponse create(DtoCategoryIn in) {
		try {
            repo.create(in.getCategory(), in.getTag());
            return new ApiResponse("La categoria ha sido registrada");
        }catch (DataAccessException e) {
            if (e.getLocalizedMessage().contains("ux_category"))
                throw new ApiException(HttpStatus.CONFLICT, "El nombre de la categoria ya está registrado");
            if (e.getLocalizedMessage().contains("ux_tag"))
                throw new ApiException(HttpStatus.CONFLICT, "El tag de la categoria ya está registrado");
            throw new DBAccessException();
        }
	}


	@Override
	public ApiResponse update(DtoCategoryIn in, int id) {
		try {
            validateId(id);
            repo.update(id, in.getCategory(), in.getTag());
            return new ApiResponse("La categoria ha sido actualizada");
        }catch (DataAccessException e) {
            if (e.getLocalizedMessage().contains("ux_category"))
                throw new ApiException(HttpStatus.CONFLICT, "El nombre de la categoria ya está registrado");
            if (e.getLocalizedMessage().contains("ux_tag"))
                throw new ApiException(HttpStatus.CONFLICT, "El tag de la categoria ya está registrado");
            throw new DBAccessException();
        }
	}


	@Override
	public ApiResponse enable(int id) {
		try {
            validateId(id);
            repo.setStatus(id, 1);
            return new ApiResponse("La categoria ha sido activada");
        }catch (DataAccessException e) {
            throw new DBAccessException();
        }
	}


	@Override
	public ApiResponse disable(int id) {
		try {
            validateId(id);
            repo.setStatus(id, 0);
            return new ApiResponse("La categoria ha sido desactivada");
        }catch (DataAccessException e) {
            throw new DBAccessException();
        }
	}

	private void validateId(int id) {
        if(repo.findById(id).isEmpty())
            throw new ApiException(HttpStatus.NOT_FOUND, "El id de la categoria no existe");
    }

}