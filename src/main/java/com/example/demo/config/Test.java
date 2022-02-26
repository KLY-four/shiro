package com.example.demo.config;

import com.google.common.collect.Lists;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        final ArrayList<String> strings = Lists.newArrayList("2", "5");
        final ArrayList<String> list = Lists.newArrayList("1", "2", "3", "4", "5");
        // List<List<String>> partition = Lists.partition(list, 2);
         ArrayList<String> list1 = Lists.newArrayList();
        list1.add(list.get(0));
        list1.add(list.get(1));

         ArrayList<String> list2 = Lists.newArrayList();
        list2.add(list.get(2));
        list2.add(list.get(3));

        List<List<String>> partition = Lists.newArrayList();
        partition.add(list1);
        partition.add(list2);

        for (List<String> codes : partition) {
            codes.add("9");
        }
        for (String s : strings) {
            System.out.println(s);
        }
    }
}
