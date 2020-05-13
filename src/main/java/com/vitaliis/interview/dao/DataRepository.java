package com.vitaliis.interview.dao;

import com.vitaliis.interview.model.FileRow;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends CrudRepository<FileRow, String> {

}