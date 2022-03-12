package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Main3 {
    public static void main(String[] args) {
        ArrayList<String> a = new ArrayList<>();
//        List<String> b = new LinkedList<>();
        a.add("a");
        a.add("b");
        a.add("c");

//        b.add("1");
//        b.add("2");
//        b.add("3");

        Iterator<String> iterator = a.iterator();
        while (iterator.hasNext()) {
//            iterator.remove();
            System.out.println(iterator.next());
        }
    }
}
