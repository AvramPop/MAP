package com.company;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> array = Arrays.asList("hi", "hello", "gratie", "hola");
        //1
      //  array.forEach(e -> System.out.println("  " + e));

        //2
    //    array.forEach(System.out::println);

        //3
        List<String> excitingWords = array.stream().map(e -> e = e + "!").collect(Collectors.toList());
        List<String> eyeWords = array.stream().map(e -> e.replaceAll("i", "eye")).collect(Collectors.toList());
        List<String> upperCaseWords = array.stream().map(String::toUpperCase).collect(Collectors.toList());

        //4
        List<String> shortWords = array.stream().filter(e -> e.length() < 4).collect(Collectors.toList());
        List<String> wordsWithB = array.stream().filter(e -> e.contains("b")).collect(Collectors.toList());
        List<String> evenLengthWords = array.stream().filter(e -> e.length() % 2 == 0).collect(Collectors.toList());

        //5
        String magic = array
                        .stream()
                        .map(String::toUpperCase)
                        .filter(e -> e.length() < 4)
                        .filter(e -> e.contains("e"))
                        .collect(Collectors.toList())
                        .get(0);

        //6
        String concat = array.stream().reduce("", (string1, string2) -> string1 + string2.toUpperCase());
        System.out.println(concat);

        //7
        String concat2 = array.stream().map(String::toUpperCase).reduce("", (string1, string2) -> string1 + string2);
        System.out.println(concat2);

        //8
        Optional<String> concat3 = array.stream().reduce((string1, string2) -> string1 + ", " + string2);
        System.out.println(concat3.get());

        //9
        int count = array.stream().map(String::length).reduce(0, Integer::sum);
        System.out.println(count);

        //10
        int count2 = (int) array.stream().filter(e -> e.contains("h")).count();
        System.out.println(count2);
    }
}
