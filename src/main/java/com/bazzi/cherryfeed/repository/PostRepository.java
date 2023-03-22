package com.bazzi.cherryfeed.repository;

import com.bazzi.cherryfeed.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
