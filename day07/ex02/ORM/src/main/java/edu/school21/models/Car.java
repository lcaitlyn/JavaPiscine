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

    public Car(int id, String name, Integer speed) {
        this.id = id;
        this.name = name;
        this.speed = speed;
    }

    public Car() {
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", speed=" + speed +
                '}';
    }
}
