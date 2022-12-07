package edu.school21.objects;

import edu.school21.Parameters.Parameters;

public class Player extends GameObject {
    public Player() {
        super(Parameters.getPlayerChar(), Parameters.getPlayerColor());
    }
}
