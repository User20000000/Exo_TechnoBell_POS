package com.pos2.pos2.Service;

import com.pos2.pos2.DTO.UserDTO;
import com.pos2.pos2.Exeptions.ElementNotFound;

public interface UserService extends CrudService<UserDTO, Integer>{

    public UserDTO login (String username, String password) throws ElementNotFound;

}
