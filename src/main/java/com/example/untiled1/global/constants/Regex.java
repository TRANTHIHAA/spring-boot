package com.example.untiled1.global.constants;

public class Regex {

    private Regex(){
    }

    public static final String REGEX_VIETNAMESE_CHARACTER = "^[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹsW_ ]+$";

    public static final String REGEX_SPECIAL_CHARACTER = "^[^<>{}\"/|;:.,~!?@#$%^=&*\\]\\\\()\\[¿§«»ω⊙¤°℃℉€¥£¢¡®©0-9_+]*$";

    public static final String REGEX_EMAIL = "^\\w+([._]*\\w)*@[\\w]+(\\.[\\w]+){1,2}$";

    public static final String REGEX_NUMBER = "^\\d+$";

    public static final String REGEX_NUMBER_3_CHAR = "^(\\d{0,3})$";

    public static final String REGEX_NUMBER_20_CHAR = "^(\\d{0,20})$";

    public static final String REGEX_NUMBER_PERSONAL_IDENTIFY = "^$|^([A-Z,0-9]{8,9}|[A-Z,0-9]{12})$";

    public static final String REGEX_NUMBER_10_CHAR = "^(\\d{10})$";
}
