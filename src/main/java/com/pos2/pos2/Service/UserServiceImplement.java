package com.pos2.pos2.Service;

import com.pos2.pos2.DTO.UserDTO;
import com.pos2.pos2.DataAccess.Entitys.User;
import com.pos2.pos2.DataAccess.Repository.UserRepository;
import com.pos2.pos2.Exeptions.AlreadyExist;
import com.pos2.pos2.Exeptions.ElementNotFound;
import com.pos2.pos2.Mapper.MainMapper;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImplement implements UserService {

    private final MainMapper<UserDTO, User> mapper;
    private final UserRepository repository;
    private final EntityManager manager;

    public UserServiceImplement(MainMapper<UserDTO, User> mapper, UserRepository repository, EntityManager manager) {
        this.mapper = mapper;
        this.repository = repository;
        this.manager = manager;
    }

    @Override
    public UserDTO getOne(Integer id){
        return repository.findById(id)
                .map(mapper::entityToDto)
                .orElseThrow(null);

    }

    @Override
    public List<UserDTO> getAll() {
        return repository.findAll().stream()
                .map(mapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void insert(UserDTO toInsert) throws AlreadyExist {
        if( repository.existsById(toInsert.getId())){
            throw new AlreadyExist();
        }
        repository.save(mapper.dtoToEntity(toInsert));
    }

    @Override
    public void delete(Integer id) throws ElementNotFound {
        if (!repository.existsById(id)){
            throw new ElementNotFound();
        }

        repository.deleteById(id);
    }

    @Override
    public void update(UserDTO toUpdate) throws ElementNotFound {

        if (!repository.existsById(toUpdate.getId())){
            throw new ElementNotFound();
        }

        repository.save(mapper.dtoToEntity(toUpdate));

    }


    @Override
    public UserDTO login(String username, String password) throws ElementNotFound {

        User user = repository.loginCheck(username, password);

        UserDTO finalUser = mapper.entityToDto(user);

        return finalUser;

    }
}
