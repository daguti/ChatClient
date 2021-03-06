/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatclient.emoticons;

import java.awt.Component;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.ImageOutputStream;
import org.w3c.dom.Node;

/**
 *
 * @author ESa10969
 */
public class GifImageSolver {
    public static Image readImgFromFile(File file, Component parent) {
    if (!file.exists()) {
        return null;
    }

    // Fix for bug when delay is 0
    try {
       

        // Get GIF reader
        ImageReader reader = ImageIO.getImageReadersByFormatName("gif").next();
        // Give it the stream to decode from
        reader.setInput(ImageIO.createImageInputStream(file));

        int numImages = reader.getNumImages(true);

        // Get 'metaFormatName'. Need first frame for that.
        IIOMetadata imageMetaData = reader.getImageMetadata(0);
        String metaFormatName = imageMetaData.getNativeMetadataFormatName();

        // Find out if GIF is bugged
        boolean foundBug = false;
        for (int i = 0; i < numImages && !foundBug; i++) {
            // Get metadata
            IIOMetadataNode root = (IIOMetadataNode)reader.getImageMetadata(i).getAsTree(metaFormatName);

            // Find GraphicControlExtension node
            int nNodes = root.getLength();
            for (int j = 0; j < nNodes; j++) {
                Node node = root.item(j);
                if (node.getNodeName().equalsIgnoreCase("GraphicControlExtension")) {
                    // Get delay value
                    String delay = ((IIOMetadataNode)node).getAttribute("delayTime");

                    // Check if delay is bugged
                    if (Integer.parseInt(delay) == 0) {
                        foundBug = true;
                    }

                    break;
                }
            }
        }

        // Load non-bugged GIF the normal way
        Image image;
        if (!foundBug) {
            image = Toolkit.getDefaultToolkit().createImage(file.getPath());
        } else {
            // Prepare streams for image encoding
            ByteArrayOutputStream baoStream = new ByteArrayOutputStream();
            try (ImageOutputStream ios = ImageIO.createImageOutputStream(baoStream)) {
                // Get GIF writer that's compatible with reader
                ImageWriter writer = ImageIO.getImageWriter(reader);
                // Give it the stream to encode to
                writer.setOutput(ios);

                writer.prepareWriteSequence(null);

                for (int i = 0; i < numImages; i++) {
                    // Get input image
                    BufferedImage frameIn = reader.read(i);

                    // Get input metadata
                    IIOMetadataNode root = (IIOMetadataNode)reader.getImageMetadata(i).getAsTree(metaFormatName);

                    // Find GraphicControlExtension node
                    int nNodes = root.getLength();
                    for (int j = 0; j < nNodes; j++) {
                        Node node = root.item(j);
                        if (node.getNodeName().equalsIgnoreCase("GraphicControlExtension")) {
                            // Get delay value
                            String delay = ((IIOMetadataNode)node).getAttribute("delayTime");

                            // Check if delay is bugged
                            if (Integer.parseInt(delay) == 0) {
                                // Overwrite with a valid delay value
                                ((IIOMetadataNode)node).setAttribute("delayTime", "10");
                            }

                            break;
                        }
                    }

                    // Create output metadata
                    IIOMetadata metadata = writer.getDefaultImageMetadata(new ImageTypeSpecifier(frameIn), null);
                    // Copy metadata to output metadata
                    metadata.setFromTree(metadata.getNativeMetadataFormatName(), root);

                    // Create output image
                    IIOImage frameOut = new IIOImage(frameIn, null, metadata);

                    // Encode output image
                    writer.writeToSequence(frameOut, writer.getDefaultWriteParam());
                }

                writer.endWriteSequence();
            }

            // Create image using encoded data
            image = Toolkit.getDefaultToolkit().createImage(baoStream.toByteArray());
        }

        // Trigger lazy loading of image
        MediaTracker mt = new MediaTracker(parent);
        mt.addImage(image, 0);
        try {
            mt.waitForAll();
        }
        catch (InterruptedException e) {
            image = null;
        }
        return image;
    }
    catch (IOException e) {
        e.printStackTrace();
        return null;
    }
}
}
