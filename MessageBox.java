import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.util.StringTokenizer;
import javax.swing.*;

/**
 * This is my class for MessageBox
 *
 * This class represents a message box that displays a message on the screen
 * for a given amount of time, or when the letter I is pressed on the key.
 * This messagebox can also be removed by pressing I again.
 */
public class MessageBox extends DragonGameShape {
    private int width;
    private int height;
    private String message;
    private Timer timer;
    private boolean display;

    /**
     * The constructor for my Messagebox
     *
     * pre: the frame width of the game, the frame height of the game, the dragon of the game, and the message of
     * the instructions to the game
     * post: assigns values to the width and height of the message box, and also initializes what the x and y locations
     * are for the messagebox in game, and creates a time for the messagebox according to the amount of words
     * it has.
     *
     * @param frameWidth
     * @param frameHeight
     * @param dragon
     * @param message
     */
    public MessageBox(int frameWidth, int frameHeight, Dragon dragon, String message) {
        super();
        this.width = frameWidth / 3;
        this.height = (int) (frameHeight * 0.8);
        this.message = message;
        this.display = false;

        if (dragon.isFacingRight()) {
            loc.x = dragon.getLoc().x + 100;
        }
        else {
            loc.x = dragon.getLoc().x - 1200 / 3 - 100;
        }
        loc.y = frameHeight / 2 - this.height / 2;

        int numWords = countWords(message);
        int displayTime = numWords / 4 * 1000;
        this.timer = new Timer(displayTime, e -> this.display = false);
    }

    /**
     * Method for counting the amount of words there are in the message.
     *
     * pre: the string for the message
     * post: returns the amount of words in your string message.
     *
     * @param s
     * @return
     */

    private int countWords(String s) {
        StringTokenizer st = new StringTokenizer(s);
        return st.countTokens();
    }

    /**
     * This is the display method
     *
     * pre: nothing
     * post: sets the display to true, and starts the timer for the message box.
     *
     */

    public void display() {
        this.display = true;
        this.timer.start();
    }

    /**
     * This is the hide method
     *
     * pre: nothing
     * post: sets the display to false, and it stops the timer for the messagebox
     *
     */

    public void hide() {
        this.display = false;
        this.timer.stop();
    }

    /**
     * This is the move method
     *
     * pre: nothing
     * post: this keeps the movement of the messagebox within the bounds of the games frames
     * changes the movements of the message in response to the x changes with the dragon's facing
     * position.
     *
     */

    public void move() {
        if (loc.x < 100) {
            loc.x = 100;
        }
        else if (loc.x > 1200 - this.width - 100) {
            loc.x = 900 - this.width - 100;
        }
    }

    /**
     * This is the draw method, for drawing the messagebox.
     *
     * pre: the graphics class
     * post: draws out the messagebox and wraps the text within the messagebox.
     *
     * @param g
     */

    public void draw(Graphics g) {
        if (this.display) { //if statement, checks if display is true or not.
            g.setColor(Color.BLACK); //this is for the filled black rectangle
            g.fillRect((int) loc.x, (int) loc.y, this.width, this.height);

            g.setColor(Color.YELLOW); //this is for the yellow
            g.fillRect((int) loc.x - 5, (int) loc.y - 5, this.width + 10, 5);
            g.fillRect((int) loc.x - 5, (int) loc.y + this.height, this.width + 10, 5);
            g.fillRect((int) loc.x - 5, (int) loc.y - 5, 5, this.height + 10);
            g.fillRect((int) loc.x + this.width, (int) loc.y - 5, 5, this.height + 10);

            g.setColor(Color.WHITE); //this is for the text
            Font font = new Font("Arial", Font.PLAIN, 26); //for the font
            g.setFont(font); //sets the font
            int x = (int) loc.x + 20;
            int y = (int) loc.y + 50;
            int lineHeight = g.getFontMetrics().getHeight();
            //for each loop for inserting the message in the box.
            for (String line : wrapText(this.message, this.width - 250, font)) {
                g.drawString(line, x, y);
                y += lineHeight;
            }
        }
    }

    /**
     * This is the method to wrap the text within the message box.
     *
     * pre: the text that is inputted, the width of how much the text can go, and the font.
     * post: returns the string to the messagebox and starts a new line for each line.
     *
     * @param text
     * @param width
     * @param font
     * @return
     */

    private String[] wrapText(String text, int width, Font font) {
        String[] words = text.split(" "); //array of words.
        StringBuilder wrappedText = new StringBuilder();
        int lineWidth = 0;

        for (String word : words) { //for each loop for the words.
            int wordWidth = getWordWidth(word, font);
            if (lineWidth + wordWidth <= width) { //if statement to append the words.
                wrappedText.append(word).append(" ");
                lineWidth += wordWidth + getWordWidth(" ", font);
            }
            else {
                wrappedText.append("\n").append(word).append(" ");
                lineWidth = wordWidth + getWordWidth(" ", font);
            }
        }
        return wrappedText.toString().split("\n"); //returns the line of words back to the draw method.
    }

    /**
     * This is the getWordWidth method for the wrap text method.
     *
     * pre: the word, and the font.
     * post: returns a new instance of Jlabel with a specified text and
     * returns the width of it.
     *
     * @param word
     * @param font
     * @return
     */

    private int getWordWidth(String word, Font font) {
        return new JLabel(word).getPreferredSize().width;
    }
}