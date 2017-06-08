package com.dongly;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Created by tiger on 17-6-4.
 */
public class ListTest {

    @Test
    public void test1() {

        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        for (Integer d : list) {
            list.remove(d);
        }

    }



}
