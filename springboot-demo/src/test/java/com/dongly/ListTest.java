package com.dongly;

import org.junit.Test;

import java.util.ArrayList;
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

        Set<String> set = new LinkedHashSet<>();
        set.add("one");
        set.add("one");
        set.add("one");
        System.out.println(set);

    }



}
