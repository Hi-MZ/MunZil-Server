package io.munzil.munzil.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum GlobalErrorCode {

    // 401
    EXPIRED_JWT(401, "Expired Jwt"),
    INVALID_JWT(401, "Invalid Jwt"),
    PASSWORD_MISMATCH(401,  "Password Mismatch"),

    // 403
    FORBIDDEN(403, "FORBIDDEN"),
    NOT_VALID_USER(403, "Not Valid User"),

    // 404
    USER_NOT_FOUND(404, "User Not Found"),
    REFRESH_TOKEN_NOT_FOUND(404, "RefreshToken Not Found"),
    FEED_NOT_FOUND(404, "Feed Not Found"),
    FEED_VIEW_COUNT_NOT_FOUND(404, "Feed View Count Not Found"),
    IMAGE_NOT_FOUND(404, "IMAGE_NOT_FOUND"),
    QUESTION_NOT_FOUND(404, "Question Not Found"),
    FOLLOW_NOT_FOUND(404, "No such Follow"),

    // 409
    ALREADY_USER_EXIST(409, "Already User Exist"),
    ALREADY_NICKNAME_EXIST(409, "Already Nickname Exist"),
    ALREADY_EMAIL_EXISTS(409, "Already Email Exist"),

    FOLLOW_ALREADY_EXISTS(409, "Follow Already Exists"),

    // 500
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    private final int status;
    private final String message;

}
