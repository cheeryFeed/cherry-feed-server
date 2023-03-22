package com.bazzi.cherryfeed.repository;

import com.bazzi.cherryfeed.domain.FileStore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileDataRepository extends JpaRepository<FileStore,Long> {
    Optional<FileStore> findByFileName(String fileName);
}
