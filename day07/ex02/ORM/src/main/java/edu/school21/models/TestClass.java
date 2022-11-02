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

    public TestClass() {
    }

    public TestClass(int id, String stringLength, String stringNoLength, int anInt, boolean aBoolean, long aLong, float aFloat, double aDouble) {
        this.id = id;
        this.stringLength = stringLength;
        this.stringNoLength = stringNoLength;
        this.anInt = anInt;
        this.aBoolean = aBoolean;
        this.aLong = aLong;
        this.aFloat = aFloat;
        this.aDouble = aDouble;
    }

    @Override
    public String toString() {
        return "TestClass{" +
                "id=" + id +
                ", stringLength='" + stringLength + '\'' +
                ", stringNoLength='" + stringNoLength + '\'' +
                ", anInt=" + anInt +
                ", aBoolean=" + aBoolean +
                ", aLong=" + aLong +
                ", aFloat=" + aFloat +
                ", aDouble=" + aDouble +
                '}';
    }
}
