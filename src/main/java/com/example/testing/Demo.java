package com.example.testing;

import com.example.testing.converters.xml.AModifierXslt;
import com.example.testing.converters.xml.XmlConverter;
import com.example.testing.services.TestService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@Profile("dev")
public class Demo {
    private final TestService testService;
    private final XmlConverter xmlConverter;

    public Demo(TestService testService, XmlConverter xmlConverter) {
        this.testService = testService;
        this.xmlConverter = xmlConverter;
        input();
        print(xmlConverter.convertToXml(testService.getAll()));
        print(xmlConverter.convertToXml(testService.getAll(), new AModifierXslt()));
        print("Sum: " + Integer.toString(testService.sum()));
    }

    private void input() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter size (integer): ");

        while (!scanner.hasNextInt()) {
            scanner.next();

            System.out.println("You can only enter an integer");
            System.out.print("Enter size (integer): ");
        }

        int size = scanner.nextInt();
        testService.add(size);

        scanner.close();
    }

    private void print(String text) {
        System.out.println(text);
    }
}
