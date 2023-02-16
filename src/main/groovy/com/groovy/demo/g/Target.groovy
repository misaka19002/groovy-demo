package com.groovy.demo.g;

import groovy.transform.ToString;
import groovy.transform.stc.POJO;

@POJO
@ToString(pojo = true, includeNames = true)
class Target {
    String name;
    String brand;
}
