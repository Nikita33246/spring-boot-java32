package org.itstep.springbootjava32.service;


import org.itstep.springbootjava32.model.Image;
import org.itstep.springbootjava32.model.Student;
import org.itstep.springbootjava32.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
public class ImageUploadService {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageUploadService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }



    public void uploadImageToStudent(MultipartFile file, Student student) throws IOException {

        Image studentProfileImage = imageRepository.findByStudentId(student.getId());
        if (!ObjectUtils.isEmpty(studentProfileImage)) {
            imageRepository.delete(studentProfileImage);
        }
        Image image = new Image();
        image.setStudentId(student.getId());

        image.setContent(compressBytes(file.getBytes()));
        image.setContentType(file.getContentType());
        image.setName(file.getOriginalFilename());
        imageRepository.save(image);
    }


    public Image getImageToStudent(Student student) {
        Image image = imageRepository.findById(student.getId()).get();
        if (!ObjectUtils.isEmpty(image)) {
            image.setContent(decompressBytes(image.getContent()));
        }
        return image;
    }


    private byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }

        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }

    private static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException | DataFormatException e) {
        }
        return outputStream.toByteArray();
    }
}
