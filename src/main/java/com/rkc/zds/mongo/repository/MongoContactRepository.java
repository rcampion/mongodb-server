package com.rkc.zds.mongo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.rkc.zds.mongo.model.Contact;
/*
public interface UserRepository extends MongoRepository<User, String>, QuerydslPredicateExecutor<User> {
    @Query("{ 'name' : ?0 }")
    List<User> findUsersByName(String name);

    @Query("{ 'age' : { $gt: ?0, $lt: ?1 } }")
    List<User> findUsersByAgeBetween(int ageGT, int ageLT);

    @Query("{ 'name' : { $regex: ?0 } }")
    List<User> findUsersByRegexpName(String regexp);

    List<User> findByName(String name);

    List<User> findByNameLikeOrderByAgeAsc(String name);

    List<User> findByAgeBetween(int ageGT, int ageLT);

    List<User> findByNameStartingWith(String regexp);

    List<User> findByNameEndingWith(String regexp);

    @Query(value = "{}", fields = "{name : 1}")
    List<User> findNameAndId();

    @Query(value = "{}", fields = "{_id : 0}")
    List<User> findNameAndAgeExcludeId();
}
*/


//public interface MongoContactRepository extends MongoRepository<Contact, String> {
public interface MongoContactRepository	extends PagingAndSortingRepository<Contact, String> {

	Page<Contact> findByFirstNameLike(String firstName, Pageable pageable);
	Page<Contact> findByFirstNameLikeOrLastNameLike(String firstName, String lastName, Pageable pageable);
	Page<Contact> findByFirstNameLikeIgnoreCaseOrLastNameLikeIgnoreCaseOrCompanyLikeIgnoreCase(String firstName, String lastName, String company,Pageable pageable);
	
}
