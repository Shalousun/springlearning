package com.sunyu.prometheus.error;

import com.power.common.model.CommonResult;
import com.sunyu.prometheus.enums.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;

/**
 * Global RestException
 */
@RestControllerAdvice
public class RestExceptionHandler {

    /**
     * log
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResult illegalParamsExceptionHandler(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        FieldError fieldError = bindingResult.getFieldError();
        LOGGER.error("request params invalid: {}", fieldError.getDefaultMessage());
        return processBindingError(fieldError);
    }

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResult methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        String error = String.format("The parameter '%s' should be of type '%s'", ex.getName(), ex.getRequiredType().getSimpleName());
        return CommonResult.fail("400", error);
    }

    @ExceptionHandler(value = {NoHandlerFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CommonResult noHandlerFoundException(Exception ex) {
        return CommonResult.fail("404", "Resource Not Found");
    }

    @ExceptionHandler(value = {HttpMediaTypeNotSupportedException.class})
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public CommonResult handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex) {
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(","));
        return CommonResult.fail("415", builder.toString());
    }

    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public CommonResult methodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        LOGGER.error("Error code 405: {}", ex.getMessage());
        return CommonResult.fail("405", ex.getMessage());
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResult unknownException(Exception ex) {
        LOGGER.error("Error code 500：{}", ex);
        return new CommonResult("500", ex.getMessage());
    }


    /**
     * spring binding error
     *
     * @param fieldError
     * @return
     */
    private CommonResult processBindingError(FieldError fieldError) {
        String code = fieldError.getCode();
        LOGGER.debug("validator error code: {}", code);
        switch (code) {
            case "NotEmpty":
                return CommonResult.fail(ErrorCode.PARAM_EMPTY.getCode(), fieldError.getDefaultMessage());
            case "NotBlank":
                return CommonResult.fail(ErrorCode.PARAM_EMPTY.getCode(), fieldError.getDefaultMessage());
            case "NotNull":
                return CommonResult.fail(ErrorCode.PARAM_EMPTY.getCode(), fieldError.getDefaultMessage());
            case "Pattern":
                return CommonResult.fail(ErrorCode.PARAM_ERROR.getCode(), fieldError.getDefaultMessage());
            case "Min":
                return CommonResult.fail(ErrorCode.PARAM_ERROR.getCode(), fieldError.getDefaultMessage());
            case "Max":
                return CommonResult.fail(ErrorCode.PARAM_ERROR.getCode(), fieldError.getDefaultMessage());
            case "Length":
                return CommonResult.fail(ErrorCode.PARAM_ERROR.getCode(), fieldError.getDefaultMessage());
            case "Range":
                return CommonResult.fail(ErrorCode.PARAM_ERROR.getCode(), fieldError.getDefaultMessage());
            case "Email":
                return CommonResult.fail(ErrorCode.PARAM_ERROR.getCode(), fieldError.getDefaultMessage());
            case "DecimalMin":
                return CommonResult.fail(ErrorCode.PARAM_ERROR.getCode(), fieldError.getDefaultMessage());
            case "DecimalMax":
                return CommonResult.fail(ErrorCode.PARAM_ERROR.getCode(), fieldError.getDefaultMessage());
            case "Size":
                return CommonResult.fail(ErrorCode.PARAM_ERROR.getCode(), fieldError.getDefaultMessage());
            case "Digits":
                return CommonResult.fail(ErrorCode.PARAM_ERROR.getCode(), fieldError.getDefaultMessage());
            case "Past":
                return CommonResult.fail(ErrorCode.PARAM_ERROR.getCode(), fieldError.getDefaultMessage());
            case "Future":
                return CommonResult.fail(ErrorCode.PARAM_ERROR.getCode(), fieldError.getDefaultMessage());
            default:
                return CommonResult.fail(ErrorCode.UNKNOWN_ERROR);
        }
    }
}
