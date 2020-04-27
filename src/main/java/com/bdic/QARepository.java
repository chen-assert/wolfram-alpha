package com.bdic;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface QARepository extends CrudRepository<QA, Integer> {
    @Query(value = "select * from Wolfram.QA where question=?1", nativeQuery = true)
    Collection<QA> findByQuestion(String question);
}
