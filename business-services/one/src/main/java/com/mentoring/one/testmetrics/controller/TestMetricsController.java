package com.mentoring.one.testmetrics.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/metrics")
public class TestMetricsController {

    private final MeterRegistry registry;
    private Counter counter;

    public TestMetricsController(MeterRegistry registry) {
        this.registry = registry;
    }

    @PostConstruct
    public void configureStatistic() {
        counter = Counter.builder("Service One - Test counter of requests Endpoint")
                .register(registry);
    }

    @GetMapping
    public String getStatistic() {
        counter.increment();

        return "Info from servo controller. Quantity requests = " + counter.count();
    }
}
