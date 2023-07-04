package com.example.untiled1.global.service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.*;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class JasperPrintService {

    public static byte[] jasperPrintToFile(JasperPrint report, String fileType) throws JRException {
        switch (fileType.toLowerCase()) {
            case "pdf":
                JRPdfExporter pdfExporter = new JRPdfExporter();
                SimplePdfReportConfiguration pdfConfig = new SimplePdfReportConfiguration();
                ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
                pdfExporter.setExporterInput(new SimpleExporterInput(report));
                pdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pdfOutputStream));

                pdfExporter.setConfiguration(pdfConfig);
                pdfExporter.exportReport();
                return pdfOutputStream.toByteArray();
            case "xlsx":
                SimpleXlsxReportConfiguration xlsxConfig = new SimpleXlsxReportConfiguration();
                JRXlsxExporter xlsxExporter = new JRXlsxExporter();
                ByteArrayOutputStream xlsxOutputStream = new ByteArrayOutputStream();

                xlsxExporter.setExporterInput(new SimpleExporterInput(report));
                xlsxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(xlsxOutputStream));
                xlsxExporter.setConfiguration(xlsxConfig);
                xlsxExporter.exportReport();
                return xlsxOutputStream.toByteArray();
            case "docx":
                JRDocxExporter docxExporter = new JRDocxExporter();
                docxExporter.setExporterInput(new SimpleExporterInput(report));
                ByteArrayOutputStream docxOutputStream = new ByteArrayOutputStream();
                docxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(docxOutputStream));
                SimpleDocxReportConfiguration docxConfig = new SimpleDocxReportConfiguration();
                docxExporter.setConfiguration(docxConfig);
                docxExporter.exportReport();
                return docxOutputStream.toByteArray();
            default:
                HtmlExporter htmlExporter = new HtmlExporter();
                htmlExporter.setExporterInput(new SimpleExporterInput(report));
                ByteArrayOutputStream htmlOutputStream = new ByteArrayOutputStream();
                htmlExporter.setExporterOutput(new SimpleHtmlExporterOutput(htmlOutputStream));
                SimpleHtmlReportConfiguration htmlConfig = new SimpleHtmlReportConfiguration();
                htmlExporter.setConfiguration(htmlConfig);
                htmlExporter.exportReport();
                return htmlOutputStream.toByteArray();
        }
    }
}
