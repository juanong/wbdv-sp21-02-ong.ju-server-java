package com.example.wbdvsp2102juanongserverjava.repositories;
import com.example.wbdvsp2102juanongserverjava.models.Widget;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

/*
This repository connects our Widget entity to the SQL server
 */
public interface WidgetRepository extends CrudRepository<Widget, Integer> {
    // Implement any additional SQL functionality here

    @Query(value = "SELECT * FROM wbdv_sp21_02_schema.widgets WHERE topic_id = :tid", nativeQuery = true)
    public List<Widget> findWidgetsForTopic(@Param("tid") String topicId);
}
