//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.untiled1.global.utils;

import com.google.common.io.ByteStreams;
import com.google.common.io.CharStreams;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import com.lowagie.text.BadElementException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.ValidatorResources;
import org.springframework.core.io.ClassPathResource;
import org.xml.sax.SAXException;

public class FileEx {
    private static final String CLASS_PATH = "classpath";
    private static final String CLASS_PATH_1 = "classpath:";
    private static final String CLASS_PATH_2 = "classpath*:";

    private FileEx() {
    }

    public static void validatePathEx(String sPathName) {
        sPathName = StringUtils.trimToEmpty(sPathName);
        if (sPathName.contains("./") || sPathName.contains(".\\") || sPathName.startsWith("//") || sPathName.startsWith("\\\\")) {
            throw new BadElementException("Path: [%s] invalid.");
        }
    }

    public static String getFileNameByPathName(String sPathName, boolean checkExists, String msgNotFound) {
        return getPathByPathName(sPathName, checkExists, msgNotFound).toFile().getName();
    }

    public static Path getPathByPathName(String sPathName, boolean checkExists, String msgNotFound) {
        validatePathEx(sPathName);
        Path path = Paths.get(sPathName);
        if (checkExists && !path.toFile().exists()) {
            throw new BadElementException(StringUtils.isEmpty(msgNotFound) ? String.format("File: [%s] is not exists.", sPathName) : msgNotFound);
//            throw new BadRequestException(StringUtils.isEmpty(msgNotFound) ? String.format("File: [%s] is not exists.", sPathName) : msgNotFound);
        } else {
            return path;
        }
    }

    public static Path writeFile(String sPathName, byte[] bytes) throws IOException {
        return writeFile(getPathByPathName(sPathName, false, (String)null), bytes);
    }

    public static Path writeFile(Path path, byte[] bytes) throws IOException {
        Files.createDirectories(path.getParent());
        return Files.write(path, bytes, new OpenOption[0]);
    }

    public static Path writeFile(Path path, String content) throws IOException {
        return writeFile((Path)path, (Iterable)Arrays.asList(content));
    }

    public static Path writeFile(Path path, Iterable<? extends CharSequence> lines) throws IOException {
        Files.createDirectories(path.getParent());
        return Files.write(path, lines, StandardCharsets.UTF_8);
    }

    public static InputStream getFileInputStream(String sPathName, boolean checkExists, String msgNotFound) throws IOException {
        sPathName = StringUtils.trimToEmpty(sPathName);
        if (sPathName.startsWith("classpath")) {
            sPathName = replaceSpecialCharacterInPath(sPathName).replace("classpath:", "").replace("classpath*:", "");
            ClassPathResource cpr = new ClassPathResource(sPathName);
            if (checkExists && !cpr.exists()) {
                throw new BadElementException("Bad request");
            } else {
                return cpr.getInputStream();
            }
        } else {
            validatePathEx(sPathName);
            File fileTemplate = new File(sPathName);
            if (checkExists && !fileTemplate.exists()) {
                throw new BadElementException("Bad request");
            } else {
                return new FileInputStream(fileTemplate);
            }
        }
    }

    public static File getFileEx(String pathname) {
        if (StringUtils.isEmpty(pathname)) {
            throw new RuntimeException("Pathname is invalid.");
        } else {
            validatePathEx(pathname);
            return new File(pathname);
        }
    }

    public static boolean checkFileExistsEx(String pathname, boolean isThrowException) {
        validatePathEx(pathname);
        pathname = StringUtils.trimToEmpty(pathname);
        if (pathname.startsWith("classpath")) {
            pathname = replaceSpecialCharacterInPath(pathname).replace("classpath:", "").replace("classpath*:", "");
            boolean bExists = (new ClassPathResource(pathname)).exists();
            if (!bExists && isThrowException) {
                throw new RuntimeException("File is invalid.");
            } else {
                return bExists;
            }
        } else {
            return checkFileExistsEx(getFileEx(pathname), isThrowException);
        }
    }

    public static boolean checkFileExistsEx(File file, boolean isThrowException) {
        if (file != null && file.exists() && file.isFile()) {
            return true;
        } else if (isThrowException) {
            throw new RuntimeException("File is invalid.");
        } else {
            return false;
        }
    }

    public static String getResourceBundleKey(String sPathName, String key) {
        getFileNameByPathName(sPathName, true, (String)null);
        String sRet = null;

        try {
            InputStream fis = getFileInputStream(sPathName, false, (String)null);
            Throwable var4 = null;

            try {
                ResourceBundle rbTemp = new PropertyResourceBundle(fis);
                sRet = rbTemp.getString(key);
            } catch (Throwable var14) {
                var4 = var14;
                throw var14;
            } finally {
                if (fis != null) {
                    if (var4 != null) {
                        try {
                            fis.close();
                        } catch (Throwable var13) {
                            var4.addSuppressed(var13);
                        }
                    } else {
                        fis.close();
                    }
                }

            }

            return sRet;
        } catch (Exception var16) {
            throw new RuntimeException(var16);
        }
    }

    public static ValidatorResources getValidatorResources(String sPathName) throws IOException, SAXException {
        InputStream in = getFileInputStream(sPathName, false, (String)null);
        Throwable var2 = null;

        ValidatorResources var3;
        try {
            var3 = new ValidatorResources(in);
        } catch (Throwable var12) {
            var2 = var12;
            throw var12;
        } finally {
            if (in != null) {
                if (var2 != null) {
                    try {
                        in.close();
                    } catch (Throwable var11) {
                        var2.addSuppressed(var11);
                    }
                } else {
                    in.close();
                }
            }

        }

        return var3;
    }

    public static ValidatorResources getValidatorResources(InputStream in) throws IOException, SAXException {
        return new ValidatorResources(in);
    }

    public static String replaceSpecialCharacterInPath(String sPath) {
        for(sPath = StringUtils.trimToEmpty(sPath).replace("\\", "/"); sPath.startsWith(".") || sPath.startsWith("/"); sPath = sPath.substring(1)) {
        }

        return sPath;
    }

    public static String getCanonicalPathInClassPath(String sPath) {
        try {
            return (new ClassPathResource(sPath)).getFile().getCanonicalPath();
        } catch (Exception var2) {
            throw new BadElementException(var2.getMessage());
        }
    }

    public static String getHeaderContentDisposition(String fileName) {
        return String.format("attachment; filename=%s", fileName);
    }

    public static String readAllTextInClassPathOrDisk(String sPathName) {
        return readAllTextInClassPathOrDisk(sPathName, true, (String)null);
    }

    public static String readAllTextInClassPathOrDisk(String sPathName, boolean checkExists, String msgNotFound) {
        try {
            return CharStreams.toString(new InputStreamReader(getFileInputStream(sPathName, checkExists, msgNotFound), StandardCharsets.UTF_8));
        } catch (IOException var4) {
            throw new BadElementException(var4);
        }
    }

    public static byte[] readAllBytesInClassPathOrDisk(String sPathName) {
        return readAllBytesInClassPathOrDisk(sPathName, true, (String)null);
    }

    public static byte[] readAllBytesInClassPathOrDisk(String sPathName, boolean checkExists, String msgNotFound) {
        try {
            return ByteStreams.toByteArray(getFileInputStream(sPathName, checkExists, msgNotFound));
        } catch (IOException var4) {
            throw new BadElementException(var4);
        }
    }
}
