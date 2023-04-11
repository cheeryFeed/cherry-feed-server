package com.bazzi.cherryfeed.apps.file.domain;

import com.bazzi.cherryfeed.apps.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;

@Entity
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Builder
public class FileStore extends BaseEntity {
    private String fileName;
    private String type;
    private String saveLocation;     //커플아이디
}
