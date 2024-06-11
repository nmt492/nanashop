// src/main/java/vn/devpro/javaweb26/controller/GlobalExceptionHandler.java
package vn.devpro.personalproject.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        // Kiểm tra loại lỗi và chuyển hướng đến trang lỗi tương ứng
        return "backend/404"; // Thay "error404" bằng tên trang 404 của bạn
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}