package com.example.myworkspace.dust;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
@Entity 
public class DustHourly {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String dataTime;	// 데이터 시간
	private String dataType;	// 데이터 타입: PM25, PM10
	
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
	
	
	// 응답 배열 데이터에서 1건을 엔티티로 변환하여 생성 + 데이터 타입(PM25/PM10)
	public DustHourly(DustHourlyResponse.ResponseData res, String dataType) {
		this.dataTime = res.getDataTime();
		this.dataType = dataType;

		this.busan = res.getBusan();
		this.chungbuk = res.getChungbuk();
		this.chungnam = res.getChungnam();
		this.daegu = res.getDaegu();
		this.daejeon = res.getDaejeon();
		this.gangwon = res.getGangwon();
		this.gwangju = res.getGwangju();
		this.gyeongbuk = res.getGyeongbuk();
		this.gyeonggi = res.getGyeonggi();
		this.gyeongnam = res.getGyeongnam();
		this.incheon = res.getIncheon();
		this.jeju = res.getJeju();
		this.jeonbuk = res.getJeonbuk();
		this.jeonnam = res.getJeonnam();
		this.sejong = res.getSejong();
		this.seoul = res.getSeoul();
		this.ulsan = res.getUlsan();
	}
}