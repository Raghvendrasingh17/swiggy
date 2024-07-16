package com.example.swiggy.Services;

import com.example.swiggy.Entety.Users;
import org.springframework.data.repository.CrudRepository;

public interface UserServices extends CrudRepository<Users,Integer> {
}
