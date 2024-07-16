package com.example.swiggy.Services;

import com.example.swiggy.Entety.Hotel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface HotelServices extends CrudRepository<Hotel,Integer> {
}
