package com.example.myworkspace.dust;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class DustHourlyResponse {
	public List<ResponseData> list;
	
	@Data @AllArgsConstructor @NoArgsConstructor
	public class ResponseData{
		private String dataTime;
		
		private String busan;
		private String chungbuk;
		private String chungnam;
		private String daegu;
		private String daejeon;
		private String gangwon;
		private String gwangju;
		private String gyeongbuk;
		private String gyeonggi;
		private String gyeongnam;
		private String incheon;
		private String jeju;
		private String jeonbuk;
		private String jeonnam;
		private String sejong;
		private String seoul;
		private String ulsan;
	}
}
