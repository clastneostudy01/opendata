package com.example.myworkspace.dust;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class DustHourlyService {
	
	private String serviceKey = "YVyMih37i7MfUgaPa%2BJgltdnFJS89Xv5MJQs4%2Bw%2BADaKiwqHfc5bJeUzZ21lAVMOdsKfZKCjKuZeyC60ndfPzw%3D%3D";
	private DustHourlyRepository repo;
	
	@Autowired
	public DustHourlyService(DustHourlyRepository repo) {
		this.repo = repo;
	}
	
	// 주기적으로 데이터 요청하는 메소드
	// ms단위 *1000 == 1초 *60 == 1분 *30 == 30분
//	@Scheduled(fixedRate = 1000 * 60 * 30)
	@Scheduled(cron = "0 30 * * * *")	// 매 시 30분마다 실행
	public void requestDustHourlyData() throws IOException {
		getDustHourlyData("PM10");	// 미세먼지 데이터 조회
		getDustHourlyData("PM25");	// 초미세먼지 데이터 조회
	}
	
	// airkorea에 데이터를 요청하는 메소드
	@SuppressWarnings("deprecation")
	private void getDustHourlyData(String dataType) throws IOException {
		System.out.println(new Date().toLocaleString() + ":" + dataType);
		
		StringBuilder builder = new StringBuilder();
		builder.append("http://openapi.airkorea.or.kr/openapi/services/rest");	// 호스트/서비스타입
		builder.append("/ArpltnInforInqireSvc/getCtprvnMesureLIst");			// /서비스명/오퍼레이션명
		builder.append("?ServiceKey=" + serviceKey);
//		builder.append(serviceKey);
		builder.append("&itemCode=" + dataType);
//		builder.append(dataType);
		builder.append("&dataGubun=" + "HOUR");
//		builder.append("HOUR");
		builder.append("&pageNo=1");
		builder.append("&numOfRows=1");
		builder.append("&_returnType=json");
		
		URL url = new URL(builder.toString());	// URL 객체 생성
		HttpURLConnection con = (HttpURLConnection) url.openConnection();	// 접속을 만듦
		// 접속으로부터 스트림을 읽어서 리더 객체로 변환
		BufferedReader reader = 
				new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
		
		// 응답의 한 줄을 문자열로 읽어들임
		String data = reader.readLine();
//		System.out.println(data);
		
		// JSON String을 Java Object로 변환한다. (필요한 데이터만 추출)
														// (문자열, 타입)
		DustHourlyResponse response = new Gson().fromJson(data, DustHourlyResponse.class);
		System.out.println(response);
		
		
		// PM2.5 15:00 이미 있으면 insert X
		// 같은 시간에 같은 데이터 타입이 없으면 추가
		// "2021-01-21 16:00" "PM25"
		String dataTime = response.getList().get(0).getDataTime();
		if(repo.findByDataTimeAndDataType(dataTime, dataType)==null) {
			// 엔티티로 변환
			DustHourly hourlyData = new DustHourly(response.getList().get(0), dataType);
			repo.save(hourlyData);
		}
		
		

		

		
		
	}
}
