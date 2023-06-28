package com.example.untiled1.global.utils;

import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Date;
import java.util.Objects;

@Slf4j
public class FileUtils {
    private FileUtils() {
        throw new UnsupportedOperationException();
    }

    public static final String TAIL_XML = ".xml";
    public static final String TAIL_XLSX = ".xlsx";

    public static boolean isValidFileName(File file, @NotNull String... tails) {
        try {
            if (file == null || !file.isFile() || !file.canRead()) {
                return false;
            }
            return Objects.requireNonNull(file.getName())
                    .endsWith(tails.length > 0 ? tails[0] : TAIL_XML);
        } catch (Exception ex) {
            log.error(ex.getLocalizedMessage());
            return false;
        }
    }

    public static boolean isCreatedFolder(String p) {
        try {
            Path path = Paths.get(p);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return false;
        }
        return true;
    }

    public static boolean isCreatedFile(String p) {
        try {
            Path path = Paths.get(p);
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
            return true;
        } catch (Exception ex) {
            log.error(ex.getLocalizedMessage());
            return false;
        }
    }

    public static void deleteIfExistsAndEmpty(String p) {
        try {
            File file = new File(p);
            if (file.exists() && file.canRead()) {
                String[] files = file.list();
                if (files == null || files.length == 0) {
                    Files.deleteIfExists(file.toPath());
                }
            }
        } catch (Exception ex) {
            log.error(ex.getLocalizedMessage());
        }
    }

    public static Path move(String src, String dest, String fileName) {
        Path result = null;
        try {
            Path path1 = Paths.get(toAbsolutePath(src, fileName));
            Path path2 = Paths.get(toAbsolutePath(dest, fileName));
            if (Files.exists(path2)) {
                fileName = new Date().getTime() + "_" + fileName;
                result = Files.move(path1, Paths.get(toAbsolutePath(dest, fileName)));
            } else {
                result = Files.move(path1, path2);
            }
        } catch (Exception ex) {
            log.error(ex.getLocalizedMessage());
        }
        return result;
    }

    public static File[] getFiles(String path) {
        try {
            File directory = new File(path);
            return directory.listFiles();
        } catch (Exception ex) {
            log.error(ex.getLocalizedMessage());
            return new File[0];
        }
    }

    public static String getFileName(File file) {
        try {
            return file.getName();
        } catch (Exception ex) {
            log.error(ex.getLocalizedMessage());
            return null;
        }
    }

    public static String toAbsolutePath(@NotNull String... params) {
        return String.join("/", params);
    }

    public static byte[] base64ToBytes(String base64) {
        try {
            return Base64.getDecoder().decode(base64);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new byte[0];
        }
    }

    public static byte[] toBytes(@NotNull String p) {
        try {
            Path path = Paths.get(p);
            if (Files.exists(path)) {
                return Files.readAllBytes(path);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return new byte[0];
    }

    public static String toBase64(@NotNull byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static Boolean copyToFile(InputStream inputStream, File file) {
        try (ReadableByteChannel readableByteChannel = Channels.newChannel(inputStream);
             FileOutputStream fileOutputStream = new FileOutputStream(file);
             FileChannel fileChannel = fileOutputStream.getChannel()) {
            fileChannel.transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
        } catch (Exception ex) {
            log.error(ex.getLocalizedMessage());
            return false;
        }
        return true;
    }
}
