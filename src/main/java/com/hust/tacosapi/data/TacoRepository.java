package com.hust.tacosapi.data;

import com.hust.tacosapi.entity.Taco;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TacoRepository extends PagingAndSortingRepository<Taco, Long> {
}
