package com.ventulus95.ouathjwt;

import com.ventulus95.ouathjwt.service.upload.S3Service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@SpringBootTest
class OuathJwtApplicationTests {

	@Autowired
	S3Service s3Service;

	@Test
	void kkk() throws UnsupportedEncodingException {
		String filePath = "https://bomnae-static.s3.ap-northeast-2.amazonaws.com/myplot.png  ";
		filePath=filePath.replace("https://bomnae-static.s3.ap-northeast-2.amazonaws.com/", "");
		filePath = URLDecoder.decode(filePath,"UTF-8");
		System.out.println(filePath);
		s3Service.deleteFile(filePath);
	}

	@Test
	void contextLoads() {
	}

}
