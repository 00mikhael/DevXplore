package com.example.gravity.devxplore.utilities;

/**
 * Created by gravity on 8/27/17.
 */

public class Objects {
    public static boolean equals(Object o1, Object o2) {
        if (o1 == null) {
            return o2 == null;
        }
        if (o2 == null) {
            return false;
        }
        return o1.equals(o2);
    }
}
