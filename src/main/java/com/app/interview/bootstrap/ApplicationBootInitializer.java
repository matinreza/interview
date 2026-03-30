package com.app.interview.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class ApplicationBootInitializer implements CommandLineRunner {
    private final DataConvertor convertor;

    @Override
    public void run(String... args) throws InterruptedException {
        convertor.convert();
    }
}
