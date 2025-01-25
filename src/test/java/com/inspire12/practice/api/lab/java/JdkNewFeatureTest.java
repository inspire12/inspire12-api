package com.inspire12.practice.api.lab.java;


import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

class JdkNewFeatureTest {

    // jdk 9
    @Test
    void testTryWithResource() throws IOException {
        BufferedReader br = Files.newBufferedReader(Paths.get("a.txt"));
        try (br) {
            String line = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testCollection() {
        List<String> list = List.of("hi");
        Map<String, String> map = Map.of("a", "aa");
        Set<Integer> set = Set.of(1, 2, 3);
    }


    @Test
    void testArrays() {
        List<String> a = List.of("hi");
        List<String> b = List.of("hi");
        int comp = Arrays.compare(a.toArray(new String[0]), b.toArray(new String[0]));
        int firstIdx = Arrays.mismatch(a.toArray(new String[0]), b.toArray(new String[0]));
        System.out.println(comp);
        System.out.println(firstIdx);
    }

    // jdk 10
    @Test
    void testTypeReferences() {
        var a = 10;
        var result = new ArrayList<Integer>();
        // jdk 11 부터는 람다식에 사용 가능
    }

    // jdk 11
    @Test
    void testString() {
        String line = "line\nline\n";
//        System.out.println(line.isBlank());

        Stream<String> lines = line.lines();
//        Stream<String> lines = Arrays.stream(line.split("\n"));
        System.out.println("lines test");
        lines.forEach(System.out::println);

        String line20 = line.repeat(10);
        System.out.println(line20);
        String lineStrip = "   hello   ";
        System.out.println(lineStrip.strip());
        System.out.println(lineStrip.stripLeading());
        System.out.println(lineStrip.stripTrailing());
    }

    @Test
    void testFiles() throws IOException {
        Files.writeString(Path.of("a.txt"), "string", StandardOpenOption.CREATE);
        String str = Files.readString(Path.of("a.txt"));
        System.out.println(str);
    }

    // Jdk 12
    @Test
    void testStringJdk12() {
        String a = "abc\ndef".indent(3);
        System.out.println(a);
    }

    // Jdk 12
    @Test
    void testDateUtil() {
        var a = "20221121".transform(this::toLocalDate);
        System.out.println(a);
    }
    private LocalDate toLocalDate(String dateInString) {
        return LocalDate.parse(dateInString, DateTimeFormatter.BASIC_ISO_DATE);
    }

    // Jdk 14
    @Test
    void testSwitch() {
        var randomNames = new String [] {"Jayden", "Bernard", "Zino", "Mason", "Elvin"}[(int) (Math.random() * 5)];

        String name = switch(randomNames) {
            case "Jayden", "jayden" -> {
                System.out.println("Me!");
                yield "제이든";
            }
            case "Bernard", "bernard" -> "버나드";
            case "Zino" -> "자이노";
            case "Mason" -> "메이슨";
            case "Elvin" -> "엘빈";
            default -> "What's your name";
        };
        System.out.println(name);
    }

    // Jdk 15
    @Test
    void testTextBlock() {
        String json = """
            {
                "name": "feature",\
                "pubdate": "2022-11-30
            }
            """;
        System.out.println(json);
    }

    @Test
    void testStringFormatted() {
        String oldStr = String.format("name: %s", "java");
        String newStr = "name: %s".formatted("java");
    }

    //
    @Test
    void testNPEMessage() {
        LinkedList<String> names = null;
        System.out.println(names.get(0).toUpperCase());
    }

    // jdk 16
    @Test
    void testStreamToList() {
        List<Integer> nos1 = Stream.of(1, 2, 3).collect(toList());
        List<Integer> nos2 = Stream.of(1, 2, 3).toList();

        List<Integer> result = Stream.of(1, 2, 3)
                                   .mapMulti((Integer no, Consumer<Integer> consumer) -> {
                                       consumer.accept(no);
                                       consumer.accept(-no);
                                   }).toList();
        System.out.println(result);
    }

    @Test
    void testMapMulti() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        double percentage = .01;
        List<Double> evenDoubles = integers.stream()
                                       .<Double>mapMulti((integer, consumer) -> {
                                           if (integer % 2 == 0) {
                                               consumer.accept((double) integer * ( 1 + percentage));
                                           }
                                       })
                                       .toList();
        System.out.println(evenDoubles);
    }

    @Test
    void testInstanceOf() {
        Object a = "hi";
        if (a instanceof String) { // 패턴 변수
            String s = (String) a;
            System.out.println(s);
        }

        if(a instanceof String s) {// 패턴 변수
            System.out.println(s);
        }
    }

    /**
     * 파라미터를 가진 생성자, 같은 이름의 메서드
     * 기본 생성자가 없음
     * final 클래스
     * 값 변경 메서드가 없음, 다른 클래스 상속 불가
     */
    @Test
    void testRecord() {
        FullName fullName = new FullName("f", "l");
        System.out.println(fullName.first());
        System.out.println(fullName.last());
        System.out.println(fullName.formatter());

    }
    record FullName(String first, String last) {
        public String formatter() {
            return first + " " + last;
        }
    }


    public sealed interface FList<T> permits Cons, Empty {

    }
    public final class Cons<T> implements FList<T> {
        private T head;
        private FList<T> tail;
        public Cons(T head, FList<T> tail) {
            this.head = head;
            this.tail = tail;
        }
    }
    public non-sealed class Empty<T> implements FList<T> {

    }
}

