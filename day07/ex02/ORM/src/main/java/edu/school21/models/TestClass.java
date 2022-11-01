package edu.school21.models;

import edu.school21.annotation.OrmColumn;
import edu.school21.annotation.OrmColumnId;
import edu.school21.annotation.OrmEntity;

@OrmEntity(table = "TestClass")
public class TestClass {
    @OrmColumnId
    int id;
    @OrmColumn(name = "string_with_length_test", length = 50)
    String stringLength;
    @OrmColumn(name = "string_without_length_test")
    String stringNoLength;
    @OrmColumn(name = "int_test")
    int anInt;
    @OrmColumn(name = "boolean_test")
    boolean aBoolean;
    @OrmColumn(name = "long_test")
    long aLong;
    @OrmColumn(name = "float_test")
    float aFloat;
    @OrmColumn(name = "double_test")
    double aDouble;
}
