package edu.school21.objects;

import edu.school21.Parameters.Parameters;

public class Enemy extends GameObject {
    public Enemy() {
        super(Parameters.getEnemyChar(), Parameters.getEnemyColor());
    }
}
