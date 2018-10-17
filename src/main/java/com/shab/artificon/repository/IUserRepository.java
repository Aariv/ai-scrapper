package com.shab.artificon.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.shab.artificon.model.User;

public interface IUserRepository extends PagingAndSortingRepository<User, Integer> {

}
