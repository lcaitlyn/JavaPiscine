package edu.school21.AStar;

import edu.school21.ChaseLogic.GameObjectInterface;
import edu.school21.ChaseLogic.VectorInt;

import javax.accessibility.AccessibleState;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Comparator;
import java.util.Objects;

public class AStar {
    class AStarNode implements VectorInt {
        public int          x;
        public int          y;
        public Integer      g;
        public Integer      h;
        public AStarNode    parent;

        public AStarNode(int x, int y, int g, int h)
        {
            this(x, y, g, h, null);
        }
        
        public AStarNode(int x, int y, int g, int h, AStarNode parent)
        {
            this.x = x;
            this.y = y;
            this.g = g;
            this.h = h;
            this.parent = parent;
        }

        @Override
        public int getX() {
            return x;
        }

        @Override
        public int getY() {
            return y;
        }

        public Integer getG() {
            return g;
        }

        public Integer getH() {
            return h;
        }

        public AStarNode getParent() {
            return parent;
        }

        public String ToString()
        {
//            return "AStarNode{" +
//                    "x=" + x +
//                    ", y=" + y +
//                    ", g=" + g +
//                    ", h=" + h +
//                    '}';
            return "(" + x +
                    ", " + y + ")";
        }
    }

    static class AStarNodeComparator implements Comparator<AStarNode>
    {
        public int compare(AStarNode c1, AStarNode c2)
        {
            if (Objects.equals(c1.getH(), c2.getH()))
                return c1.getG().compareTo(c2.getG());
            return c1.getH().compareTo(c2.getH());
        }
    }
    static final int ENEMY = 0;
    static final int PLAYER = 1;
    static final int WALL = 2;
    static final int GOAL = 3;
    static final int EMPTY = 4;

    private LinkedList<AStarNode>   open;
    private LinkedList<AStarNode>   closed;
    private final GameObjectInterface[][]   map;
    char[]                          objectChars;

//    private final char              wallChar;
//    private final char              enemyChar;
//    private final char              playerChar;
//    private final char              emptyChar;
//    private final char              goalChar;

    public AStar(GameObjectInterface[][] map, char[] objectChars)
    {
        this.map = map;
        this.objectChars = objectChars;
    }

    public VectorInt calculateDirection(VectorInt start, VectorInt goal)
    {
        AStarNode   curr;
        this.open   = new LinkedList<AStarNode>();
        this.closed = new LinkedList<AStarNode>();

        if (heuristicCost(start, goal) < 2)
            return (goal);

        open.add(new AStarNode(start.getX(), start.getY(),
                0, heuristicCost(start, goal)));
        while (!open.isEmpty())
        {
            open.sort(new AStarNodeComparator());
            curr = open.pop();
            closed.add(curr);
            if (heuristicCost(curr, goal) < 2)
            {
                curr = regeneratePath(start, curr);
                if (curr == null)
                    return (start);
                return curr;
            }
            addAllNeighbours(curr, goal);
        }
        return null;
    }

    private char getShortPath(VectorInt lhs, VectorInt rhs)
    {
        if (lhs.getY() == rhs.getY() - 1)
            return 'w';
        if (lhs.getX() == rhs.getX() - 1)
            return 'a';
        if (lhs.getY() == rhs.getY() + 1)
            return 's';
        if (lhs.getX() == rhs.getX() + 1)
            return 'd';
        return ' ';
    }

    private AStarNode regeneratePath(VectorInt start, AStarNode path)
    {
        if (path == null)
            return null;
        while (path != null && heuristicCost(start, path) > 1)
            path = path.parent;
//        if (path.parent.y == path.y - 1)
//            return 'w';
//        if (path.parent.x == path.x - 1)
//            return 'a';
//        if (path.parent.y == path.y + 1)
//            return 's';
//        if (path.parent.x == path.x + 1)
//            return 'd';
        return path;
    }

    private boolean isWall(VectorInt pos)
    {
//        if (startChar == playerChar && map[pos.getY()][pos.getX()] == startChar)
//            return (true);
        return (map[pos.getX()][pos.getY()].getObjectChar() == objectChars[WALL]
                || map[pos.getX()][pos.getY()].getObjectChar() == objectChars[ENEMY]
                || map[pos.getX()][pos.getY()].getObjectChar() == objectChars[GOAL]);
    }

    private void addAllNeighbours(AStarNode curr, VectorInt goal)
    {
        int	        i;
        AStarNode   node = null;
        AStarNode   pos = new AStarNode(0, 0, 0, 0);

        i = -3;
        while (++i < 3)
        {
            if (i != 0)
            {
                pos.x = curr.x + (i % 2);
                pos.y = curr.y + i / 2;
                if (0 <= pos.getX() && pos.getX() < map.length
                    && 0 <= pos.getY() && pos.getY() < map.length
                    && !isWall(pos)
                    && closed.stream().noneMatch(c -> c.x == pos.getX() && c.y == pos.getY()))
                {
                    node = open.stream().filter(c -> c.x == pos.getX() && c.y == pos.getY())
                            .findAny().orElse(null);
                    if (node != null && node.g > curr.g + 1)
                        node.g = curr.g + 1;
                    if (node == null)
                        open.add(new AStarNode(pos.getX(), pos.getY(),
                                curr.g + 1, heuristicCost(pos, goal), curr));
                }
            }
        }
    }

    private int	heuristicCost(VectorInt lhs, VectorInt rhs)
    {
        return (Math.abs(lhs.getX() - rhs.getX()) + Math.abs(lhs.getY() - rhs.getY()));
    }
}
