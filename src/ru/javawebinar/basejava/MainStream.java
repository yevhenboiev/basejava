package ru.javawebinar.basejava;

import java.util.*;
import java.util.stream.Collectors;

public class MainStream {

    public static void main(String[] args) {
        int[] number = {1, 2, 1};
        MainStream ms = new MainStream();
        System.out.println("Stream : " + ms.minValue(number));
        System.out.println("Non Stream : " + ms.minValueNoStream(number));
        System.out.println("Stream : " + ms.oddOrEven(ms.setList(number)));
        System.out.println("Non Stream : " + ms.oddOrEvenNoStream(ms.setList(number)));
    }

    private int minValueNoStream(int[] values) {
        Set<Integer> set = new HashSet<>();
        for (int value : values) {
            set.add(value);
        }
        int min = 0;
        for (Integer number : set) {
            min = 10 * min + number;
        }
        return min;
    }

    private List<Integer> oddOrEvenNoStream(List<Integer> integers) {
        int sum = 0;
        for (Integer integer : integers) {
            sum += integer;
        }
        List<Integer> list = new ArrayList<>();
        if (sum % 2 != 0) {
            for (Integer integer : integers) {
                if (integer % 2 == 0) {
                    list.add(integer);
                }
            }
        } else {
            for (Integer integer : integers) {
                if (integer % 2 != 0) {
                    list.add(integer);
                }
            }
        }
        return list;
    }


    private int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce(0, (a, b) -> 10 * a + b);
    }

    private List<Integer> oddOrEven(List<Integer> integers) {
        final int sumItem = integers.stream().mapToInt(Integer::intValue).sum() % 2;
        return integers
                .stream()
                .filter(item -> item % 2 != sumItem)
                .collect(Collectors.toList());
    }

    private List<Integer> setList(int[] values) {
        List<Integer> list = new ArrayList<>();
        for (int value : values) {
            list.add(value);
        }
        return list;
    }
}
