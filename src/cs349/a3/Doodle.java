package cs349.a3;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.util.LinkedList;

public class Doodle {
    public LinkedList<Line> lines = new LinkedList<Line>();
    public int firstFrame = 0;
    public int lastFrame = 99999999;
    
    public void append(Line line) {
        lines.add(line);
    }
    
    public void paint(Graphics g, int frame) {
        if (frame < firstFrame || frame >= lastFrame) {
            return;
        }
        
        for (Line l : lines) {
            l.paint(g);
        }
    }
    
    public void toRelative(Vector2D origin) {
        for (Line l : lines) {
            l.toRelative(origin);
        }
    }
    
    public Rectangle getBoundingBox() {
        Rectangle bb = new Rectangle(999999, 999999, -99999, -99999);
        
        for (Line l : lines) {
            bb.x = Math.min(bb.x, (int)Math.min(l.source.x, l.dest.x));
            bb.width = Math.max(bb.width, (int)Math.max(l.source.x, l.dest.x));
            
            bb.y = Math.min(bb.y, (int)Math.min(l.source.y, l.dest.y));
            bb.height = Math.max(bb.height, (int)Math.max(l.source.y, l.dest.y));
        }
        
        bb.width -= bb.x;
        bb.height -= bb.y;
        
        return bb;
    }

    boolean intersects(Line r) {
        Line2D line1 = new Line2D.Double(r.source, r.dest), line2 = new Line2D.Double();
        for (Line l : lines) {
            line2.setLine(l.source, l.dest);
            
            if (line1.intersectsLine(line2)) {
                return true;
            }
        }
        
        return false;
    }
    
    void erase(int frame, Line line) {
        if (frame < firstFrame || frame >= lastFrame) {
            return;
        }
        
        if (intersects(line)) {
            lastFrame = frame;    
        }
    }
}
