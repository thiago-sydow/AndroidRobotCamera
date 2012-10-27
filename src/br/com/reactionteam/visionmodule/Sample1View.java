package br.com.reactionteam.visionmodule;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.MatOfRect;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.FeatureDetector;
import org.opencv.features2d.Features2d;
import org.opencv.imgproc.Imgproc;
import org.opencv.samples.fd.DetectionBasedTracker;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

class Sample1View extends SampleViewBase {

    public static final int     VIEW_MODE_RGBA  = 0;
    public static final int     VIEW_MODE_GRAY  = 1;
    public static final int     VIEW_MODE_CANNY = 2;
    public static final int     VIEW_MODE_FACE = 3;
    public static final int     VIEW_MODE_FAST = 4;
    
    String FILENAME = "hello_file";
    String string = "hello world!";
    
//    private static final Scalar   FACE_RECT_COLOR = new Scalar(0, 255, 0, 255);

    private Mat mYuv;
    private Mat mYuv2;
    private Mat mRgba;
    private Mat mBGR;
    private Mat mGray;
    private Mat mGraySubmat;
    private Mat mIntermediateMat;
	private Bitmap mBitmap;
	private int mViewMode;
    private DetectionBasedTracker mNativeDetector;
    private File                  mCascadeFile;

    public Sample1View(Context context) {
        super(context);
        mViewMode = VIEW_MODE_FACE;
        
        try {
            InputStream is = context.getResources().openRawResource(R.raw.lbpcascade_frontalface);
            File cascadeDir = context.getDir("cascade", Context.MODE_PRIVATE);
            mCascadeFile = new File(cascadeDir, "lbpcascade_frontalface.xml");
            FileOutputStream os = new FileOutputStream(mCascadeFile);

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            is.close();
            os.close();

            mNativeDetector = new DetectionBasedTracker(mCascadeFile.getAbsolutePath(), 0);
            
            cascadeDir.delete();

        } catch (IOException e) {
            e.printStackTrace();
            Log.e("SAMPLEVIEW", "Failed to load cascade. Exception thrown: " + e);
        }
        
    }

	@Override
	protected void onPreviewStarted(int previewWidth, int previewHeight) {
	    synchronized (this) {
        	// initialize Mats before usage
        	mYuv = new Mat(getFrameHeight() + getFrameHeight() / 2, getFrameWidth(), CvType.CV_8UC1);
        	mYuv2= new Mat(getFrameHeight() + getFrameHeight() / 2, getFrameWidth(), CvType.CV_8UC3);
        	
        	mGraySubmat = mYuv.submat(0, getFrameHeight(), 0, getFrameWidth());

        	mRgba = new Mat();
        	mBGR = new Mat();
        	
        	mIntermediateMat = new Mat();

        	mBitmap = Bitmap.createBitmap(previewWidth, previewHeight, Bitmap.Config.ARGB_8888); 
        	 
    	    }
	}

	@Override
	protected void onPreviewStopped() {
		if(mBitmap != null) {
			mBitmap.recycle();
		}

		synchronized (this) {
            // Explicitly deallocate Mats
            if (mYuv != null)
                mYuv.release();
            
            if (mYuv2 != null)
                mYuv2.release();
            
            if (mRgba != null)
                mRgba.release();
            if (mGraySubmat != null)
                mGraySubmat.release();
            if (mIntermediateMat != null)
                mIntermediateMat.release();

            if(mBGR != null)
            	mBGR.release();
            
            mYuv = null;
            mYuv2 = null;
            
            mRgba = null;
            mGraySubmat = null;
            mIntermediateMat = null;
            mBGR = null;
        }
    }

    @Override
    protected Bitmap processFrame(byte[] data) {
        mYuv.put(0, 0, data);
        mYuv2.put(0, 0, data);
        
    	Imgproc.cvtColor(mYuv, mRgba, Imgproc.COLOR_YUV420sp2RGB, 4);
    	Imgproc.cvtColor(mYuv2, mBGR, Imgproc.COLOR_YUV420p2BGR, 4);
    	
    	MatOfRect faces = new MatOfRect();
    	MatOfKeyPoint kp = new MatOfKeyPoint(); 
    	
        final int viewMode = mViewMode;

        switch (viewMode) {
        case VIEW_MODE_GRAY:
            Imgproc.cvtColor(mGraySubmat, mRgba, Imgproc.COLOR_GRAY2RGBA, 4);
            break;
        case VIEW_MODE_RGBA:
            Imgproc.cvtColor(mYuv, mRgba, Imgproc.COLOR_YUV420sp2RGB, 4);
            break;
        case VIEW_MODE_CANNY:
            Imgproc.Canny(mGraySubmat, mIntermediateMat, 80, 100);
            Imgproc.cvtColor(mIntermediateMat, mRgba, Imgproc.COLOR_GRAY2BGRA, 4);
            break;
        case VIEW_MODE_FACE:
            	if (mNativeDetector != null)
            		mNativeDetector.detect(mGraySubmat, faces);
            break;
            
        case VIEW_MODE_FAST:
        	
        	if (mNativeDetector != null){
        		
        		FeatureDetector fd = FeatureDetector.create(FeatureDetector.FAST);
        		Mat descriptors = new Mat();
        		
        		fd.detect(mGraySubmat, kp);
        		
        		Features2d.drawKeypoints(mGraySubmat, kp, mRgba);
        		
        		DescriptorExtractor extractor = DescriptorExtractor.create(DescriptorExtractor.OPPONENT_SURF);
        		extractor.compute(mGraySubmat, kp, descriptors);
        		
        		if(false){
        			
        		}
        		else{
        			
        			
//        			
//        			Bitmap bmp = null;
//        			FileOutputStream fos = null;
//        			
//        			try {
//        				
//	        			fos = getContext().openFileOutput(FILENAME, Context.MODE_PRIVATE);
//	        			bmp = Bitmap.createBitmap(mRgba.cols(), mRgba.rows(), Bitmap.Config.ARGB_8888);
//        	        
//        	            Utils.matToBitmap(mRgba, bmp);
//        	            bmp.compress(Bitmap.CompressFormat.PNG, 70, fos);
//        	            fos.close();
//        	        } catch(Exception e) {
//        	            Log.e("org.opencv.samples.tutorial1", "Utils.matToBitmap() throws an exception: " + e.getMessage());
//        	            if(bmp != null)
//        	            	bmp.recycle();
//        	            bmp = null;
//        	            try {
//        	            	if(fos != null)
//        	            		fos.close();
//						} catch (IOException e1) {
//							Log.e("org.opencv.samples.tutorial1", "Utils.matToBitmap() throws an exception: " + e.getMessage());
//						}
//        	        }
//        			
//        		}
        		
//        		MatOfDMatch matches = new MatOfDMatch();
//        		DescriptorMatcher matcher = DescriptorMatcher.create(DescriptorMatcher.FLANNBASED);
//        		matches = matcher.match();
        	}
        	}
        	break;
        
        }
        
//        Rect[] facesArray = faces.toArray();
//        for (int i = 0; i < facesArray.length; i++)
//        Core.rectangle(mRgba, facesArray[i].tl(), facesArray[i].br(), FACE_RECT_COLOR, 3);

        Bitmap bmp = Bitmap.createBitmap(mRgba.cols(), mRgba.rows(), Bitmap.Config.ARGB_8888);
        
        try {
            Utils.matToBitmap(mRgba, bmp);
        } catch(Exception e) {
            Log.e("org.opencv.samples.tutorial1", "Utils.matToBitmap() throws an exception: " + e.getMessage());
            bmp.recycle();
            bmp = null;
        }
        
        return bmp;
    }

    public void setViewMode(int viewMode) {
    	mViewMode = viewMode;
    }
    
    
    @Override
    public void run() {
        super.run();

        synchronized (this) {
            // Explicitly deallocate Mats
            if (mRgba != null)
                mRgba.release();
            if (mGray != null)
                mGray.release();
            if (mCascadeFile != null)
            	mCascadeFile.delete();
            if (mNativeDetector != null)
            	mNativeDetector.release();

            if(mBGR != null)
            	mBGR.release();
            
            
            mRgba = null;
            mGray = null;
            mCascadeFile = null;
            mBGR = null;
        }
    }
    
}
