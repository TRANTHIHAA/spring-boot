package com.example.untiled1.domain.ai;

import org.bytedeco.javacpp.*;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.face.*;

import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.Objdetect;
import org.opencv.videoio.VideoCapture;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

@RestController
public class FaceVerificationController {

    @PostMapping("/verify-faces")
    public String verifyFaces(@RequestParam("file1") MultipartFile file1,
                              @RequestParam("file2") MultipartFile file2) throws IOException {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println("1");

        try {

            InputStream inputStream1 = file1.getInputStream();
            byte[] fileBytes1 = inputStream1.readAllBytes();
            MatOfByte matOfByte1 = new MatOfByte(fileBytes1);
            Mat mat = Imgcodecs.imdecode(matOfByte1, Imgcodecs.IMREAD_COLOR);




//          MatOfByte matOfByte =  new MatOfByte(file1.getBytes());
            // Đọc ảnh từ MultipartFile và chuyển đổi sang định dạng Mat
//            Mat matOfByte1 = new MatOfByte(file1.getBytes());
            Mat matOfByte2 = new MatOfByte(file2.getBytes());
            Mat image1 = Imgcodecs.imdecode(matOfByte1, Imgcodecs.IMREAD_COLOR);
            Mat image2 = Imgcodecs.imdecode(matOfByte2, Imgcodecs.IMREAD_COLOR);
            // Chuyển đổi ảnh sang grayscale
            Mat grayImage1 = new Mat();
            Mat grayImage2 = new Mat();
            Imgproc.cvtColor(image1, grayImage1, Imgproc.COLOR_BGR2GRAY);
            Imgproc.cvtColor(image2, grayImage2, Imgproc.COLOR_BGR2GRAY);

            // Sử dụng FaceRecognizer để nhận diện và so sánh khuôn mặt
            FaceRecognizer faceRecognizer = LBPHFaceRecognizer.create();
            faceRecognizer.read("G:/hatt/demo/untiled1/src/main/resources/data/haarcascade_frontalface_alt2.xml");

            // Nhận diện khuôn mặt trong ảnh
            int[] label = new int[1];
            double[] confidence = new double[1];
            faceRecognizer.predict(grayImage1, label, confidence);
            int predictedLabel1 = label[0];

            faceRecognizer.predict(grayImage2, label, confidence);
            int predictedLabel2 = label[0];

            // So sánh kết quả xác thực
            if (predictedLabel1 == predictedLabel2) {
                return "Cùng một người";
            } else {
                return "Khác người";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Lỗi xử lý";
        }
//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//        Mat image = Imgcodecs.imread("G:/hatt/demo/untiled1/src/main/resources/static/images/rose.png");
//        detectAndSave(image);
//        return "ok";

    }
    private  void detectAndSave(Mat image) {
        MatOfRect faces = new MatOfRect();
        Mat grayFrame = new Mat();
        Imgproc.cvtColor(image,grayFrame,Imgproc.COLOR_BGR2GRAY);
        Imgproc.equalizeHist(grayFrame,grayFrame);
        int height = grayFrame.height();
        int absoluteFaceSize = 0;
        if (Math.round(height * 0.2f) >0) {
            absoluteFaceSize = Math.round(height * 0.2f);
        }
        CascadeClassifier faceCascade = new CascadeClassifier();
        faceCascade.load("G:/hatt/demo/untiled1/src/main/resources/data/haarcascade_frontalface_alt2.xml");
        faceCascade.detectMultiScale(grayFrame,faces,1.1,2,0| Objdetect.CASCADE_SCALE_IMAGE
                ,new Size(absoluteFaceSize,absoluteFaceSize),new Size());
        Rect[] faceArray = faces.toArray();
        for (int i = 0; i < faceArray.length; i++) {

            Imgproc.rectangle(image,faceArray[i],new Scalar(0,0,255),3);
        }
        Imgcodecs.imwrite("G:/hatt/demo/untiled1/src/main/resources/static/images/rose_love.png",image);
        System.out.println("success");
    }
}
