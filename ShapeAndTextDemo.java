import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ShapeAndTextDemo extends JPanel implements KeyListener {
    private int currentShapeIndex = 0; // Index to keep track of the current shape
    private final String[] shapes = {"Circle", "Square", "Triangle"};
    
    public ShapeAndTextDemo() {
        setFocusable(true);
        addKeyListener(this);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Set background color
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Set shape color
        g2d.setColor(Color.BLUE);

        // Determine shape position
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        // Draw current shape
        switch (currentShapeIndex) {
            case 0 -> g2d.fillOval(centerX - 50, centerY - 50, 100, 100); // Circle
            case 1 -> g2d.fillRect(centerX - 50, centerY - 50, 100, 100); // Square
            case 2 -> { // Triangle
                Polygon triangle = new Polygon(
                        new int[]{centerX, centerX - 50, centerX + 50},
                        new int[]{centerY - 50, centerY + 50, centerY + 50},
                        3
                );
                g2d.fillPolygon(triangle);
            }
        }

        // Draw text below the shape
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        String text = shapes[currentShapeIndex];
        int textWidth = g2d.getFontMetrics().stringWidth(text);
        g2d.drawString(text, centerX - textWidth / 2, centerY + 80);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            currentShapeIndex = (currentShapeIndex + 1) % shapes.length; // Move to the next shape
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            currentShapeIndex = (currentShapeIndex - 1 + shapes.length) % shapes.length; // Move to the previous shape
        }
        repaint(); // Redraw the panel
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Shape Changer");
        ShapeAndTextDemo panel = new ShapeAndTextDemo();

        frame.add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
