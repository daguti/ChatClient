/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatclient.threads.video;

import com.googlecode.javacv.CanvasFrame;
import com.googlecode.javacv.FrameGrabber;
import com.googlecode.javacv.VideoInputFrameGrabber;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import static com.googlecode.javacv.cpp.opencv_core.cvFlip;
import static com.googlecode.javacv.cpp.opencv_highgui.cvSaveImage;

/**
 *
 * @author ESa10969
 */
public class VideoCaptureThread implements Runnable {
    Thread th;
    IplImage image = null;
    CanvasFrame canvas = new CanvasFrame("Web Cam");
    final int INTERVAL = 200;
    
    public VideoCaptureThread() {
        canvas.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        
    }
    
    public void start() {
        th = new Thread(this);
        th.start();
    }

    public void stop() {
       th = null;
       canvas.dispose();
    }
    
    @Override
    public void run() {
        FrameGrabber grabber = new VideoInputFrameGrabber(0); // 1 for next camera
        int i=0;
        try {
            if(VideoInputFrameGrabber.getDeviceDescriptions().length == 0) this.stop();
            grabber.start();
            while (th != null) {
                image = grabber.grab();
                if (image != null) {
                    cvFlip(image, image, 1);// l-r = 90_degrees_steps_anti_clockwise
                    cvSaveImage((i++)+"-aa.jpg", image);
                    // show image on window
                    canvas.showImage(image);
                }
                 Thread.sleep(INTERVAL);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
        
}
    
