package ru.hogwarts.school.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.IntStream;
import java.util.stream.Stream;

@RestController
@RequestMapping("/info")
public class InfoController {

    private final Logger logger = LoggerFactory.getLogger(InfoController.class);

    @Value("${server.port}")
    private String port;

    @GetMapping
    public String getPort() {
        return port;
    }

    @GetMapping("/compute")
    public void compute() {
        Long start = System.currentTimeMillis();
        int results = Stream.iterate(1, a -> a +1)
                .limit(1_000_000_000)
                .reduce(0, (a, b) -> a + b );
        Long finish = System.currentTimeMillis();
        logger.info("Result:{},time:{}",results,finish-start);
    }
    @GetMapping("/improved-compute")
    public void improvedCompute() {
        Long start = System.currentTimeMillis();
        int results = Stream.iterate(1, a -> a + 1)
                .limit(100_000_000)
                .parallel()
                .reduce(0, (a, b) -> a + b)
                ;
        Long finish = System.currentTimeMillis();
        logger.info("ResultParallel:{},time:{}",results,finish-start);
    }
    @GetMapping("/best-improved-compute")
    public void bestImprovedCompute() {
        Long start = System.currentTimeMillis();
        int results = IntStream.iterate(1, a -> a + 1)
                .limit(1_000_000_000)
                .reduce(0, (a, b) -> a + b)
                ;
        Long finish = System.currentTimeMillis();
        logger.info("BestResult:{},time:{}",results,finish-start);
    }

}
