//package com.example.untiled1.configuration;
//
//import org.apache.commons.compress.utils.IOUtils;
//import org.bytedeco.javacpp.Loader;
//import org.bytedeco.opencv.opencv_core.*;
//import org.bytedeco.opencv.opencv_face.FaceRecognizer;
//import org.bytedeco.opencv.opencv_face.LBPHFaceRecognizer;
//import org.bytedeco.opencv.opencv_objdetect.CascadeClassifier;
//import org.opencv.dnn.Dnn;
//import org.opencv.dnn.Net;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.io.InputStream;
//
//import static org.bytedeco.opencv.global.opencv_imgcodecs.IMREAD_UNCHANGED;
//import static org.bytedeco.opencv.global.opencv_imgcodecs.imdecode;
//import static org.bytedeco.opencv.global.opencv_imgproc.COLOR_BGR2GRAY;
//import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;
//
//@Configuration
//public class FaceDetectorConfig {
//
//    @Bean
//    public FaceDetector faceDetector() {
//        Loader.load(org.bytedeco.opencv.global.opencv_dnn.class);
//        Loader.load(org.bytedeco.opencv.global.opencv_face.class);
//        return new FaceDetector();
//    }
//
//    public class FaceDetector {
//
//        private final Net faceNet;
//        private final FaceRecognizer faceRecognizer;
//
//        public FaceDetector() {
//            faceNet = Dnn.readNetFromCaffe("path/to/deploy.prototxt", "path/to/res10_300x300_ssd_iter_140000.caffemodel");
//            faceRecognizer = LBPHFaceRecognizer.create();
//            faceRecognizer.read("path/to/face_recognizer_model.xml");
//        }
//
//        public double compareFaces(MultipartFile file1, MultipartFile file2) throws IOException {
//            // Chuyển đổi MultipartFile thành Mat (OpenCV)
//            Mat image1 = new Mat();
//            Mat image2 = new Mat();
//            try (InputStream is1 = file1.getInputStream();
//                 InputStream is2 = file2.getInputStream()) {
//                byte[] bytes1 = IOUtils.toByteArray(is1);
//                byte[] bytes2 = IOUtils.toByteArray(is2);
//                image1 = imdecode(new Mat(bytes1), IMREAD_UNCHANGED);
//                image2 = imdecode(new Mat(bytes2), IMREAD_UNCHANGED);
//            }
//            // Xử lý khuôn mặt
//            RectVector faceRects1 = detectFaces(image1);
//            RectVector faceRects2 = detectFaces(image2);
//
//            // So sánh khuôn mặt và trả về phần trăm tương đồng
//            if (faceRects1.size() > 0 && faceRects2.size() > 0) {
//                Mat face1 = extractFace(image1, faceRects1.get(0));
//                Mat face2 = extractFace(image2, faceRects2.get(0));
//                double similarity = calculateSimilarity(face1, face2);
//                return similarity;
//            } else {
//                return 0.0;
//            }
//        }
//
//        private RectVector detectFaces(Mat image) {
//            // Phát hiện khuôn mặt bằng OpenCV
//            Mat gray = new Mat();
//            cvtColor(image, gray, COLOR_BGR2GRAY);
//            CascadeClassifier faceCascade = new CascadeClassifier("path/to/haarcascade_frontalface_default.xml");
//            RectVector faceRects = new RectVector();
//            faceCascade.detectMultiScale(gray, faceRects);
//            return faceRects;
//        }
//
//        private Mat extractFace(Mat image, Rect rect) {
//            // Trích xuất khuôn mặt từ ảnh
//            Rect expandedRect = new Rect(rect.x() - 10, rect.y() - 10, rect.width() + 20, rect.height() + 20);
//            Mat face = new Mat(image, expandedRect);
//            return face;
//        }
//
//        private double calculateSimilarity(Mat face1, Mat face2) {
//            // Tính toán phần trăm tương đồng giữa hai khuôn mặt
//            Mat face1Gray = new Mat();
//            Mat face2Gray = new Mat();
//            cvtColor(face1, face1Gray, COLOR_BGR2GRAY);
//            cvtColor(face2, face2Gray, COLOR_BGR2GRAY);
//            int label = faceRecognizer.predict(face1Gray);
//            double[] confidence = new double[1];
//            faceRecognizer.predict(face2Gray, new int[]{label}, confidence);
//            return 100 - confidence[0];
//        }
//    }
//}
