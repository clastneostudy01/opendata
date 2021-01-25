package com.example.myworkspace.dust;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DustHourlyRepository extends JpaRepository<DustHourly, Long> {
	
	// 일치 데이터가 있는지 확인하는 메소드 원형 선언
	// select * from
	
	// where data_time = ?1 and data_type = ?2
	// findByDataTimeAndDataType(String 1, String 2)
	DustHourly findByDataTimeAndDataType(String dataTime, String dataType);
	
	public List<DustHourly> findByDataType(String dataType, Pageable pageable);
}
