package com.example.untiled1.domain.ai;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.core.MatOfRect;

import org.opencv.core.Mat;

import org.opencv.imgproc.Imgproc;
import org.opencv.core.MatOfFloat;
import org.opencv.core.Core;

import java.util.Arrays;
import java.util.List;


public class FaceComparison {

    public boolean compareFaces(String imagePath1, String imagePath2) {
        CascadeClassifier cascadeClassifier = new CascadeClassifier();
        cascadeClassifier.load("haarcascade_frontalface_default.xml"); // Đường dẫn đến tệp xml chứa thông tin về khuôn mặt

        Mat image1 = Imgcodecs.imread(imagePath1);
        Mat image2 = Imgcodecs.imread(imagePath2);

        MatOfRect faces1 = new MatOfRect();
        MatOfRect faces2 = new MatOfRect();

        // Tìm khuôn mặt trong hai hình ảnh
        cascadeClassifier.detectMultiScale(image1, faces1);
        cascadeClassifier.detectMultiScale(image2, faces2);

        // So sánh số lượng khuôn mặt tìm thấy
        return faces1.toArray().length == faces2.toArray().length;
    }

    public double compareFacesReturnCen(String imagePath1, String imagePath2) {
        Mat image1 = Imgcodecs.imread(imagePath1);
        Mat image2 = Imgcodecs.imread(imagePath2);

        Mat hist1 = new Mat();
        Mat hist2 = new Mat();

        // Chuyển đổi hình ảnh sang không gian màu HSV
        Imgproc.cvtColor(image1, image1, Imgproc.COLOR_BGR2HSV);
        Imgproc.cvtColor(image2, image2, Imgproc.COLOR_BGR2HSV);

        // Tính toán histogram cho hai hình ảnh
        Imgproc.calcHist(
                List.of(image1),
                new MatOfInt(0),
                new Mat(),
                hist1,
                new MatOfInt(180),
                new MatOfFloat(0, 180)
        );

        Imgproc.calcHist(
                List.of(image2),
                new MatOfInt(0),
                new Mat(),
                hist2,
                new MatOfInt(180),
                new MatOfFloat(0, 180)
        );

        // Chuẩn hóa histogram
        Core.normalize(hist1, hist1);
        Core.normalize(hist2, hist2);

        // Tính toán độ tương đồng giữa hai histogram
        double similarity = Imgproc.compareHist(hist1, hist2, Imgproc.CV_COMP_CORREL);

        // Chuyển đổi độ tương đồng thành phần trăm
        double percentage = similarity * 100;

        return percentage;
    }

//    public double compareFacesT(String imagePath1, String imagePath2) {
//        // Load dữ liệu khuôn mặt và mô hình nhận dạng
//        LBPHFaceRecognizer faceRecognizer = LBPHFaceRecognizer.create();
//        faceRecognizer.read("path/to/trained_model.xml");
//
//        // Đọc và chuyển đổi hình ảnh thành grayscale
//        Mat image1 = Imgcodecs.imread(imagePath1, Imgcodecs.IMREAD_GRAYSCALE);
//        Mat image2 = Imgcodecs.imread(imagePath2, Imgcodecs.IMREAD_GRAYSCALE);
//
//        // Thực hiện nhận dạng khuôn mặt trên hai hình ảnh
//        int label1 = faceRecognizer.predict(image1);
//        int label2 = faceRecognizer.predict(image2);
//
//        // Tính toán độ giống nhau giữa hai khuôn mặt
//        double similarity = 0.0;
//        if (label1 == label2) {
//            similarity = faceRecognizer.getLabelHistogram(label1).get(0);
//        }
//
//        return similarity;
//    }
}
