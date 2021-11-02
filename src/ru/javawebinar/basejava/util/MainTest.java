package ru.javawebinar.basejava.util;

import java.util.Arrays;

public class MainTest {

  public static char[] generateAlphabet() {
    char[] alphabet = new char[26];
    for (int i = 0; i < alphabet.length; i++) {
      alphabet[i] = (char) ('a' + i);
    }
    return alphabet;
  }

  public static char[] generateReversedAlphabet() {
    char[] alphabetReversed = new char[26];
    for (int i = 0; i < alphabetReversed.length; i++) {
      alphabetReversed[i] = (char) ('z' - i);
    }
    return alphabetReversed;
  }

  public static int[] generateNumbers() {
    int[] numbers = new int[100];
    for (int i = 0; i < numbers.length; i++) {
      numbers[i] = i;
    }
    return numbers;
  }

  public static int findBiggest(int left, int right) {
    return left > right ? left : right;
  }

  public static int findBiggest(int left, int mid, int right) {
    final int biggest = findBiggest(left, mid);
    return biggest > right ? biggest : right;
  }

  public static int findBiggest(int[] numbers) {
    int biggest = 0;
    for (int num : numbers) {
      int biggestArray = findBiggest(biggest, num);
      if (biggestArray > biggest) {
        biggest = biggestArray;
      }
    }
    return biggest;
  }

  public static int findIndexOfBiggestNumber(int[] numbers) {
    int biggest = findBiggest(numbers);
    int index = 0;
    for (int i = 0; i < numbers.length; i++) {
      if (biggest == numbers[i]) {
        index = i;
      }
    }
    return index;
  }

  public static boolean isNegative(int number) {
    return number <= 0;
  }

  public static char[] convertToArray(int number) {
    int capacity = 0;
    int copyNumber = number;
    while (number != 0) {
      capacity++;
      number /= 10;
    }
    char[] arrayChar;
    if (copyNumber < 0) {
      arrayChar = new char[capacity + 1];
      for (int i = arrayChar.length - 1; i >= 0; i--) {
        arrayChar[i] = (char) ('0' - (copyNumber % 10));
        copyNumber /= 10;
      }
      arrayChar[0] = '-';
    } else if (copyNumber > 0) {
      arrayChar = new char[capacity];
      for (int i = arrayChar.length - 1; i >= 0; i--) {
        arrayChar[i] = (char) ('0' + (copyNumber % 10));
        copyNumber /= 10;
      }
    } else {
      arrayChar = new char[]{'0'};
    }

    return arrayChar;
  }


  public static void main(String[] args) {
    System.out.println(Arrays.toString(convertToArray(-123)));
  }
}
