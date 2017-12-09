package sharedObject;
import javafx.scene.image.Image;

public class AnimatedImage
{
    // assumes animation loops,
    //  each image displays for equal time
    public Image[] frames;
    public double duration;
    public int index;
    
    public Image getFrame(double time)
    {
        index = (int)((time % (frames.length * duration)) / duration);
        return frames[index];
    }
    
    public int getIndex() {
    		return index;
    }
    
    public void setDuration(double duration) {
    		this.duration = duration;
    }
}