import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AppDrawing extends JFrame{
	
	public static void main(String[] args) {
		
		AppDrawing window = new AppDrawing("My Window");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(900, 500);
		window.setVisible(true);
	}
	
	JButton line,
	rectangle,
	filledRectangle,
	circle,
	filledCircle,
	deleteLast,
	colorButton,
	backButton;
	JColorChooser colorChooser;
	ShapesPanel shapesPanel; //Add a field for ShapesPanel
	ShapeType selectedShape = ShapeType.LINE; // Default to LINE
	
	
	public AppDrawing(String title) {
		super(title);
		setLayout(new BorderLayout()); 
		
		shapesPanel = new ShapesPanel(); // Create an instance of ShapesPanel
	    add(shapesPanel, BorderLayout.CENTER); 
		
		JPanel buttonsP = new JPanel(); 
		buttonsP.setLayout(new FlowLayout());
		
		
		line = new JButton("Line");
		buttonsP.add(line);                        // The instance for all buttons.
		line.setBackground(Color.WHITE);
	
		rectangle = new JButton("Rectangle");
		buttonsP.add(rectangle);
		rectangle.setBackground(Color.WHITE);
		
		filledRectangle = new JButton("Filled Rectangle");
		buttonsP.add(filledRectangle);
		filledRectangle.setBackground(Color.WHITE);
		
		circle = new JButton("Circle");
		buttonsP.add(circle);
		circle.setBackground(Color.WHITE);
		
		filledCircle = new JButton("Filled Circle");
		buttonsP.add(filledCircle);
		filledCircle.setBackground(Color.WHITE);
		
		deleteLast = new JButton("Delete List");
		buttonsP.add(deleteLast);
		deleteLast.setBackground(Color.WHITE);

		colorChooser = new JColorChooser();
		colorButton = new JButton("          ");
		buttonsP.add(colorButton);
		colorButton.setBackground(Color.BLACK);
		
		backButton = new JButton("           ");
		buttonsP.add(backButton);
        backButton.setBackground(Color.WHITE);
		
        add(buttonsP, BorderLayout.NORTH); // Set the panel into the North.
        buttonsP.setBackground(Color.LIGHT_GRAY);
        
       
		Listener listener = new Listener();
		colorButton.addActionListener(listener);
		backButton.addActionListener(listener);
		line.addActionListener(listener);
		rectangle.addActionListener(listener);
		filledRectangle.addActionListener(listener);
		circle.addActionListener(listener);
		filledCircle.addActionListener(listener);
		deleteLast.addActionListener(listener);
		
	}
	
	
	private class Listener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			 	line.setBackground(Color.WHITE);
	            line.setForeground(null);
	            rectangle.setBackground(Color.WHITE);
	            rectangle.setForeground(null);
	            filledRectangle.setBackground(Color.WHITE);
	            filledRectangle.setForeground(null);
	            circle.setBackground(Color.WHITE);
	            circle.setForeground(null);
	            filledCircle.setBackground(Color.WHITE);
	            filledCircle.setForeground(null);
	            deleteLast.setBackground(Color.WHITE);
	            deleteLast.setForeground(null);
	            
			
	        // For the buttons    
			if(e.getSource() == line) {
				selectedShape = ShapeType.LINE;
				line.setBackground(Color.RED);
				line.setForeground(getBackground());
				
			}else
			if(e.getSource() == rectangle) {
				selectedShape = ShapeType.RECTANGLE;
				rectangle.setBackground(Color.RED);
				rectangle.setForeground(getBackground());
				
			}else
			if(e.getSource() == filledRectangle) {
				selectedShape = ShapeType.FILLED_RECTANGLE;
				filledRectangle.setBackground(Color.RED);
				filledRectangle.setForeground(getBackground());
				
			}else
			if(e.getSource() == circle) {
				selectedShape = ShapeType.CIRCLE;
				circle.setBackground(Color.RED);
				circle.setForeground(getBackground());
				
			}else
			if(e.getSource() == filledCircle) {
				selectedShape = ShapeType.FILLED_CIRCLE;
				filledCircle.setBackground(Color.RED);
				filledCircle.setForeground(getBackground());
				
			}else
			if(e.getSource() == deleteLast) {
				selectedShape = ShapeType.DELETE_LAST;
				shapesPanel.deleteLast();
				
			}
			
			 shapesPanel.setSelectedShape(selectedShape);
			
			// For the color fields(Choosers)
			if(e.getSource() == colorButton) {
				Color color = JColorChooser.showDialog(null, "Pick a color", Color.RED);
				colorButton.setBackground(color);
				shapesPanel.setSelectedColor(color); // Set the selected color in ShapesPanel
				
			} else if (e.getSource() == backButton) {
                Color bgColor = JColorChooser.showDialog(null, "Pick a background color", Color.BLUE);
                shapesPanel.setBackground(bgColor);
                backButton.setBackground(bgColor);
            }
			
		}
		
	}
	 
	 // Enum to represent different shapes
    enum ShapeType {
        LINE,
        RECTANGLE,
        FILLED_RECTANGLE,
        CIRCLE,
        FILLED_CIRCLE,
        DELETE_LAST
       
    }

}
