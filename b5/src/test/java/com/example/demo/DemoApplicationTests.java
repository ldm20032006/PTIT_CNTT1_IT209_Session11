package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
        // Test mặc định PASS để Job test hoàn thành màu xanh (Success)
        // Khi test PASS: Job docker-build sẽ được kích hoạt thông qua 'needs: test'
        
        // HƯỚNG DẪN KIỂM TRA BÀI 5 (LÀM ĐỨT TEST ĐỂ DOCKER-BUILD BỊ SKIPPED):
        // Để kiểm tra yêu cầu làm đứt JUnit Test, bạn chỉ cần bỏ comment dòng dưới đây:
        // org.junit.jupiter.api.Assertions.fail("Cố tình làm đứt JUnit Test để kiểm tra Job docker-build bị Skipped");
    }

}
