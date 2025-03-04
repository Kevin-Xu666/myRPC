package com.xzs.examplespringbootconsumer;

import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import javax.annotation.Resource;

@SpringBootTest
class ExampleServiceImplTest {

    @Resource
    private ExampleServiceImpl exampleService;

    @Test
    void test1() {
        exampleService.test();
    }
}
