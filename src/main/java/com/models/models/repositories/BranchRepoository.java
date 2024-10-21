package com.models.models.repositories;

import com.models.models.allModels.Branch;
import org.springframework.data.repository.CrudRepository;

public interface BranchRepoository extends CrudRepository<Branch, Long> {
}
