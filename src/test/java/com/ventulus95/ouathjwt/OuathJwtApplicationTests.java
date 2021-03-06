package com.ventulus95.ouathjwt;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.ventulus95.ouathjwt.dto.artwork.ArtworkSaveRequestDto;
import com.ventulus95.ouathjwt.service.upload.S3Service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(classes = S3Service.class)
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
	public void  S3_폴더_업로드() throws IOException {
		ArtworkSaveRequestDto a = new ArtworkSaveRequestDto();
		MockMultipartFile firstFile = new MockMultipartFile("testPhoto", "filename.txt", "text/plain", "some xml".getBytes());
		a.setFile(firstFile);
		s3Service.upload(a.getFilePath(), a.getFile());
	}


	@Test
	public void 메타데이터_확인() throws ImageProcessingException, IOException {
		File file = new ClassPathResource("/testPhoto/KakaoTalk_Photo_2020-11-24-14-31-39.jpeg").getFile();
			Metadata metadata = ImageMetadataReader.readMetadata(file);
		Directory directory1 = Optional.ofNullable(metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class)).orElse(new ExifSubIFDDirectory());
		assertNull(directory1.getString(ExifSubIFDDirectory.TAG_MAX_APERTURE));
		for (Directory directory : metadata.getDirectories()) {
			for (Tag tag : directory.getTags()) {
				System.out.println(tag);
			}
		}

	}

}
