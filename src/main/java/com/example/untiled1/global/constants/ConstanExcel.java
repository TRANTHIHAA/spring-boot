package com.example.untiled1.global.constants;

import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;

@Slf4j
public class ConstanExcel {

    private void ConstantExcel() {
        throw new IllegalStateException("ConstantExcel class");
    }
    public static StringBuilder errorMessage = new StringBuilder();


//    public static StringBuilder getErrorMessage() {
//
//        return errorMessage;
//
//    }

    public static void setErrorMessage(String errorMessage){

        ConstanExcel.errorMessage.append(errorMessage);

    }

    public static class RegexCell {

        private RegexCell() {

            throw new IllegalStateException("Regex class");

        }

        public static final String REGEX_CELL_NUMBER = "^[\\d.]{1,}$";
//        public static final String REGEX_CELL_STRING_AND_NUMBER = "^[\\w\\s. ]{1,32}$";


        public static boolean checkRegexCell(String fileName, String sheetName, String regex, String input, int column, int row) {
            if (!Pattern.matches(regex, input)) {
                String message = "file "+ fileName +" Sheet "+ sheetName + " Row " + (row + 1) + " column " + (column + 1) + " is not valid;     ";
                log.error(message);
                setErrorMessage(message);
                return false;
            }

            return true;
        }
    }
}

