package com.example.demo.Service;

import java.lang.reflect.ParameterizedType;

import org.springframework.beans.BeanUtils;

import com.example.demo.entity.BaseEntity;
import com.example.demo.repository.BaseRepository;

public abstract class BaseService<E extends BaseEntity, D>{

    private final Class<E> entityClass;
    private final Class<D> dtoClass;
    private final BaseRepository<E, Long> repository;

    @SuppressWarnings("unchecked")
    protected BaseService(BaseRepository<E, Long> repository){
        this.repository = repository;

        ParameterizedType baseSuperClass = (ParameterizedType) this.getClass().getGenericSuperclass();
        
        this.dtoClass = (Class<D>) baseSuperClass.getActualTypeArguments()[1];
        this.entityClass = (Class<E>) baseSuperClass.getActualTypeArguments()[0];
    }

    public D toDto(E e){
        try{
        D dto = dtoClass.getDeclaredConstructor().newInstance();
        BeanUtils.copyProperties(e, dto);
        return dto;
        } catch(Exception ex){
             throw new RuntimeException("Erro ao converter de Entity para DTO", ex);
        }
    }

    public E toEntity(D dto){
        try{
        E e = entityClass.getDeclaredConstructor().newInstance();
        BeanUtils.copyProperties(dto, e);
        return e;
    } catch(Exception ex){
             throw new RuntimeException("Erro ao converter de DTO para Entity", ex);
        }
    }

    public D create(D dto){
        E e = toEntity(dto);
        e = repository.save(e);
        return toDto(e);
    }

}
