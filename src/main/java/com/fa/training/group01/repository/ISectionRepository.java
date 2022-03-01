package com.fa.training.group01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.fa.training.group01.entity.SectionEntity;

@RepositoryRestResource(collectionResourceRel = "sections", path = "sections")
public interface ISectionRepository extends JpaRepository<SectionEntity, Integer> {

}
