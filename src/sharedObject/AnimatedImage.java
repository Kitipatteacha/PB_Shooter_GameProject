package sharedObject;
import javafx.scene.image.Image;

public class AnimatedImage
{
    // assumes animation loops,
    //  each image displays for equal time
    public Image[] frames;
    public double duration;
    public int index=0;
    private double timeCounter=0;
    
    public Image getFrame(double time)
    {
        if(System.nanoTime()-timeCounter>duration*1000000000) {
        		index++;
        		timeCounter = System.nanoTime();
        }
        return frames[index%frames.length];
    }
    
    public int getIndex() {
    		return index%frames.length;
    }
    
    public void setDuration(double duration) {
    		this.duration = duration;
    }
}