package org.example.constant;

public enum AccountErrorCode {
    ACCOUNT_CREATED( "BA_200","Account Created Successfully" ),
    ACCOUNT_ALREADY_EXISTS("BA_403","Account already Existed"),
    ACCOUNT_NOT_FOUND("BA_404","Account Not Found"),
    ACCOUNT_NOT_CREATED("BA_422","Account Not Created"),
    ACCOUNT_BALANCE_NOT_ENOUGH("BA_002","Balance Not Enough"),
    ACCOUNT_ERROR("BA_500","Account Error");

    private final String code;
    private final String message;

    AccountErrorCode(String code, String message) {
      this.code = code;
      this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
