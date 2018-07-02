package com.app.demo.domain.service.repository;

import com.app.demo.domain.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;

@Transactional
public interface MessageRepository extends PagingAndSortingRepository<Message, String> {

    @Query("select message from Message message " +
            "order by createdAt asc")
    Page<Message> findAllMessages(
            Pageable pageRequest);
}
