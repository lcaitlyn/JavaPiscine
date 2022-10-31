package edu.school21.models;

import edu.school21.annotation.OrmColumn;
import edu.school21.annotation.OrmColumnId;
import edu.school21.annotation.OrmEntity;

@OrmEntity(table = "car")
public class Car {
    @OrmColumnId
    int id;
    @OrmColumn(name = "name", length = 10)
    String name;
    @OrmColumn(name = "speed")
    Integer speed;
}
