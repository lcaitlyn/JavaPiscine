package edu.school21.objects;

import edu.school21.Parameters.Parameters;

public class Wall extends GameObject {
    public Wall() {
        super(Parameters.getWallChar(), Parameters.getWallColor());
    }
}
