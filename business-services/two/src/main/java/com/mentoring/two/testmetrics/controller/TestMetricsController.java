package com.mentoring.two.testmetrics.controller;

import io.micrometer.core.instrument.LongTaskTimer;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/metrics")
public class TestMetricsController {

    private final MeterRegistry registry;
    private LongTaskTimer timer;

    public TestMetricsController(MeterRegistry registry) {
        this.registry = registry;
    }

    @PostConstruct
    public void configureStatistic() {
        timer = LongTaskTimer.builder("Service Two - Test request duration handling")
                .register(registry);
    }

    @GetMapping
    public String getStatistic() {
        LongTaskTimer.Sample startHandling = timer.start();
        long randomDelay = getRandomDelayMills(150, 1500);
        try {

            Thread.sleep(randomDelay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long duration = startHandling.stop();

        return String.format("Random delay generated = %d. Duration of handling = %d", randomDelay, duration);
    }

    private long getRandomDelayMills(int from, int to) {
        return (long) (Math.random() * to - from + 1) + from;
    }
}
