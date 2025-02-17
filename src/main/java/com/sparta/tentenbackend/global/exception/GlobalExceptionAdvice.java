package com.sparta.tentenbackend.global.exception;

import com.sparta.tentenbackend.domain.ai.controller.AiController;
import com.sparta.tentenbackend.domain.category.controller.CategoryController;
import com.sparta.tentenbackend.domain.delivery_address.controller.DeliveryAddressController;
import com.sparta.tentenbackend.domain.order.controller.OrderController;
import com.sparta.tentenbackend.domain.payment.controller.PaymentController;
import com.sparta.tentenbackend.domain.review.controller.ReviewController;
import com.sparta.tentenbackend.domain.user.controller.UserController;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice(annotations = RestController.class, basePackageClasses = {
    AiController.class,
    CategoryController.class,
    DeliveryAddressController.class,
    OrderController.class,
    PaymentController.class,
    ReviewController.class,
    UserController.class
})
public class GlobalExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ForbiddenException.class)
    public ResponseEntity forbiddenException(HttpServletRequest req, ForbiddenException e) {
        ErrorResponse errorResponse = new ErrorResponse(req, HttpStatus.FORBIDDEN, e);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity notFoundException(HttpServletRequest req, NotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(req, HttpStatus.NOT_FOUND, e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(value = ConflictException.class)
    public ResponseEntity conflictException(HttpServletRequest req, ConflictException e) {
        ErrorResponse errorResponse = new ErrorResponse(req, HttpStatus.FORBIDDEN, e);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    public ResponseEntity unauthorizedException(HttpServletRequest req, UnauthorizedException e) {
        ErrorResponse errorResponse = new ErrorResponse(req, HttpStatus.UNAUTHORIZED, e);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity badRequestException(HttpServletRequest req, BadRequestException e) {
        ErrorResponse errorResponse = new ErrorResponse(req, HttpStatus.BAD_REQUEST, e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
