package cs349.a3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;

public class Line {
    Color color;
    Vector2D source;
    Vector2D dest;
    
    public Line(int col, Vector2D src, Vector2D dst) {
        color = new Color(col);
        source = src;
        dest = dst;
    }
    
    public Line(int col, int x0, int y0, int x1, int y1) {
        color = new Color(col);
        source = new Vector2D(x0, y0);
        dest = new Vector2D(x1, y1);
    }
    
    public void paint(Graphics g) {
        g.setColor(color);
        g.drawLine((int)source.x, (int)source.y, (int)dest.x, (int)dest.y);
    }
    
    public boolean containedBy(Rectangle r) {
        return r.contains(source) && r.contains(dest);
    }

    void toRelative(Vector2D origin) {
        source = source.minus(origin);
        dest = dest.minus(origin);
    }

    boolean containedBy(Polygon p) {
        return p.contains(source) && p.contains(dest);
    }
}
