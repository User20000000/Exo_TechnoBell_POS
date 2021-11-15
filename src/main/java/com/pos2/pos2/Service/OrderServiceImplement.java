package com.pos2.pos2.Service;

import com.pos2.pos2.DTO.OrderDTO;
import com.pos2.pos2.DataAccess.Entitys.Order;
import com.pos2.pos2.DataAccess.Repository.OrderRepository;
import com.pos2.pos2.Exeptions.AlreadyExist;
import com.pos2.pos2.Exeptions.ElementNotFound;
import com.pos2.pos2.Mapper.MainMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImplement implements OrderService {

    private final MainMapper<OrderDTO, Order> mapper;
    private final OrderRepository repository;

    public OrderServiceImplement(MainMapper<OrderDTO, Order> mapper, OrderRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public OrderDTO getOne(Integer id) throws ElementNotFound {
        return repository.findById(id)
                .map(mapper::entityToDto)
                .orElseThrow( ElementNotFound::new);
    }

    @Override
    public List<OrderDTO> getAll() {
        return repository.findAll().stream()
                .map(mapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void insert(OrderDTO toInsert) throws AlreadyExist {
            if (repository.existsById(toInsert.getId())){
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
    public void update(OrderDTO toUpdate) throws ElementNotFound {
        if(!repository.existsById(toUpdate.getId())){
            throw new ElementNotFound();
        }

        repository.save(mapper.dtoToEntity(toUpdate));

    }
}
