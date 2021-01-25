package com.example.myworkspace.dust;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class DustyHourlyController {
	private DustHourlyRepository dustRepo;
	
	@Autowired
	public DustyHourlyController(DustHourlyRepository dustRepo) {
		this.dustRepo = dustRepo;	
	}
	
	//시간별 목록 조회
	@RequestMapping
	(value="/dust-hourly", method=RequestMethod.GET)
	public List<DustHourly> getDustHourly(){
		return dustRepo.
				findAll(Sort.by("dataTime").descending());
	}

	//시간별 목록 조회, 페이징
	@RequestMapping
	(value="/dust-hourly/paging", method=RequestMethod.GET)
	public List<DustHourly> getDustHourlyPaging(
			@RequestParam("page") int page,
			@RequestParam("size") int size
			){
		return dustRepo.
				findAll(PageRequest.of(page, size)).toList();
	}
	
		//시간별 목록 조회, 페이징, id 역정렬
		@RequestMapping
		(value="/dust-hourly/paging-and-sort", method=RequestMethod.GET)
		public List<DustHourly> getDustHourlyPagingAndSort(
				@RequestParam("page") int page,
				@RequestParam("size") int size
				){
			return dustRepo.
					findAll(PageRequest.of(page, size, Sort.by("id").descending())).toList();
		}

		//시간별 목록 조회, 페이징, dataType별 분류, id 역정렬
		@RequestMapping
		(value="/dust-hourly/paging-and-sort/{dataType}", method=RequestMethod.GET)
		public List<DustHourly> getDustHourlyPagingAndSortAndFilterByDataType(
				@PathVariable("dataType") String dataType,
				@RequestParam("page") int page,
				@RequestParam("size") int size
				){
			return dustRepo.findByDataType(dataType,
					PageRequest.of(
							page, size, 
							Sort.by("id").descending()
							)
					);
		}

	
}
