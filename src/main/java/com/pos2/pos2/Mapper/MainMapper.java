package com.pos2.pos2.Mapper;


public interface MainMapper <DTO, ENTITY>{

    DTO entityToDto(ENTITY entity);

    ENTITY dtoToEntity(DTO dto);

}
