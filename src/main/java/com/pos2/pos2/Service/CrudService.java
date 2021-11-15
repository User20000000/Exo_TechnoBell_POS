package com.pos2.pos2.Service;

import com.pos2.pos2.Exeptions.AlreadyExist;
import com.pos2.pos2.Exeptions.ElementNotFound;

import java.util.List;

public interface CrudService <DTO, ID>{

    //Read
    DTO getOne(ID id) throws ElementNotFound;
    List<DTO> getAll();

    //Create
    void insert(DTO toInsert)throws AlreadyExist;

    //Delete
    void delete(ID id)throws ElementNotFound;

    //Update
    void update(DTO toUpdate)throws ElementNotFound;


}
