package com.ventulus95.ouathjwt.util;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.ventulus95.ouathjwt.model.exif.Exif;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ExifUtil {

    public static Exif imgtoExif(MultipartFile file) throws IOException, ImageProcessingException {
        InputStream inputStream = new BufferedInputStream(file.getInputStream());
        Metadata metadata = ImageMetadataReader.readMetadata(file.getInputStream());
        Directory SubDirectory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
        Directory IFD0Directory = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
        String fnum = SubDirectory.getString(ExifSubIFDDirectory.TAG_FNUMBER);
        String fl = SubDirectory.getString(ExifSubIFDDirectory.TAG_FOCAL_LENGTH);
        String iso = SubDirectory.getString(ExifSubIFDDirectory.TAG_ISO_EQUIVALENT);
        String exp = SubDirectory.getString(ExifSubIFDDirectory.TAG_EXPOSURE_TIME);
        String model = IFD0Directory.getString(ExifIFD0Directory.TAG_MODEL);
        String maker = IFD0Directory.getString(ExifIFD0Directory.TAG_MAKE);
        return Exif.builder().aperture(fnum).focusLength(fl).iso(iso).exposureTime(exp).model(model).maker(maker).build();
    }
}
