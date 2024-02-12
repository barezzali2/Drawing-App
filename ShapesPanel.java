import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.awt.event.*;

import javax.swing.*;

public class ShapesPanel extends JPanel {
	
     ArrayList<ShapeInfo> shapes = new ArrayList<>();
     Point currentStartPoint;
     Point currentEndPoint;
     AppDrawing.ShapeType selectedShape = AppDrawing.ShapeType.LINE;
     Color selectedColor = Color.BLACK; // Default color is black 

    public ShapesPanel() {
        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                currentStartPoint = e.getPoint();
                currentEndPoint = e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                currentEndPoint = e.getPoint();
                shapes.add(new ShapeInfo(selectedShape, currentStartPoint, currentEndPoint, selectedColor));
                repaint();
            }
        });

        addMouseMotionListener(new MouseAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) {
                currentEndPoint = e.getPoint();
                repaint();
            }
        });
    }

    public void setSelectedShape(AppDrawing.ShapeType selectedShape) {
        this.selectedShape = selectedShape;
    }
    
    public void setSelectedColor(Color color) {
        this.selectedColor = color;
    }
    
    public void deleteLast() {
        if (!shapes.isEmpty()) {
            shapes.remove(shapes.size() - 1);
            repaint();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(selectedColor);
        // Draw shapes
        for (ShapeInfo shape : shapes) {
        	 g.setColor(shape.shapeColor); ////
        	switch (shape.type) {
                case LINE:
                    g.drawLine(shape.startPoint.x, shape.startPoint.y, shape.endPoint.x, shape.endPoint.y);
                    break;
                    
                case RECTANGLE:
                    g.drawRect(shape.getMinX(), shape.getMinY(), shape.getWidth(), shape.getHeight());
                    break;
                    
                case FILLED_RECTANGLE:
                    g.fillRect(shape.getMinX(), shape.getMinY(), shape.getWidth(), shape.getHeight());
                    break;
                    
                case CIRCLE:
                	 g.drawOval(shape.getMinX(), shape.getMinY(), shape.getWidth(), shape.getHeight());
                    break;
                    
                case FILLED_CIRCLE:
                	 g.fillOval(shape.getMinX(), shape.getMinY(), shape.getWidth(), shape.getHeight());
                    break;
            }
        }

        // Drawing the current shape being dragged
        if (currentStartPoint != null && currentEndPoint != null) {
        	
        	g.setColor(selectedColor); ////
        	
        	switch (selectedShape) {
        	
                case LINE: 
                    g.drawLine(currentStartPoint.x, currentStartPoint.y, currentEndPoint.x, currentEndPoint.y);
                    break;
                    
                case RECTANGLE:
                    g.drawRect(Math.min(currentStartPoint.x, currentEndPoint.x),
                             Math.min(currentStartPoint.y, currentEndPoint.y),
                             Math.abs(currentEndPoint.x - currentStartPoint.x),
                             Math.abs(currentEndPoint.y - currentStartPoint.y));
                    break;
                    
                case FILLED_RECTANGLE:
                    g.fillRect(Math.min(currentStartPoint.x, currentEndPoint.x),
                             Math.min(currentStartPoint.y, currentEndPoint.y),
                             Math.abs(currentEndPoint.x - currentStartPoint.x),
                             Math.abs(currentEndPoint.y - currentStartPoint.y));
                    break;
                    
                case CIRCLE:
                	 g.drawOval(Math.min(currentStartPoint.x, currentEndPoint.x),
                             Math.min(currentStartPoint.y, currentEndPoint.y),
                             Math.abs(currentEndPoint.x - currentStartPoint.x),
                             Math.abs(currentEndPoint.y - currentStartPoint.y));
                    break;
                    
                case FILLED_CIRCLE:
                	 g.fillOval(Math.min(currentStartPoint.x, currentEndPoint.x),
                             Math.min(currentStartPoint.y, currentEndPoint.y),
                             Math.abs(currentEndPoint.x - currentStartPoint.x),
                             Math.abs(currentEndPoint.y - currentStartPoint.y));
                    break;
            }
        }
    }

    private static class ShapeInfo {
        AppDrawing.ShapeType type;
        Point startPoint;
        Point endPoint;
        Color shapeColor; ////

        public ShapeInfo(AppDrawing.ShapeType type, Point startPoint, Point endPoint,Color shapeColor) {
            this.type = type;
            this.startPoint = startPoint;
            this.endPoint = endPoint;
            this.shapeColor = shapeColor; ////
        }

        public int getMinX() {
            return Math.min(startPoint.x, endPoint.x);
        }

        public int getMinY() {
            return Math.min(startPoint.y, endPoint.y);
        }

        public int getWidth() {
            return Math.abs(endPoint.x - startPoint.x);
        }

        public int getHeight() {
            return Math.abs(endPoint.y - startPoint.y);
        }
    }
}
