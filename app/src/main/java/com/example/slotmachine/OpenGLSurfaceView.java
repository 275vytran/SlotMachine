package com.example.slotmachine;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;

/*___________________________________________________________________
|
| Class: OpenGLSurfaceView
|__________________________________________________________________*/
class OpenGLSurfaceView extends GLSurfaceView 
{
	private OpenGLRenderer render_thread;
	private Handler mHandler; 
		
	/*___________________________________________________________________
	|
	| Function: OpenGLSurfaceView (default constructor)
	|__________________________________________________________________*/
	public OpenGLSurfaceView (Context context) 
	{
		super (context); 
		Start_Renderer (context);
	}

	/*___________________________________________________________________
	|
	| Function: OpenGLRenderer (constructor)
	|
	| Description: This is the constructor called when you inflate your 
	|	GLSurfaceView from an XML layout.
	|__________________________________________________________________*/
	public OpenGLSurfaceView (Context context, AttributeSet attrs) 
	{      
		super(context, attrs); 
		Start_Renderer (context);
	} 
	
	/*___________________________________________________________________
	|
	| Function: Start_Renderer
	|
	| Description: Creates the renderer thread and starts it running.
	|__________________________________________________________________*/
	private void Start_Renderer (final Context context)
	{
		// Create a handler so render thread can run code on UI thread
	  	mHandler = new Handler();

		// Call this to enable run-time debug info to be generated
//		setDebugFlags (DEBUG_CHECK_GL_ERROR | DEBUG_LOG_GL_CALLS);
		// Create the render thread
		render_thread = new OpenGLRenderer (context, mHandler);
		setRenderer (render_thread);     
        // Make sure we get events
        setFocusable (true); 
        setFocusableInTouchMode (true);
	}
	
    /*___________________________________________________________________
    |
    | Function: onPause
    |__________________________________________________________________*/
	@Override
	public void onPause () 
    {
    	render_thread.onPause ();
    }

    /*___________________________________________________________________
    |
    | Function: onResume
    |__________________________________________________________________*/
	@Override
	public void onResume () 
    {
    	render_thread.onResume ();
    }
    
    /*___________________________________________________________________
    |
    | Function: onTouchEvent
    |
    | Description: Standard motion event override.  Let the thread process 
    |		this event.  Returns true if event handled, else false.
    |__________________________________________________________________*/
	@Override
    public boolean onTouchEvent (MotionEvent event) 
	{
		// Let the render thread handle the event
        if (render_thread.doTouchEvent (event))
        	return true;
        // If the render thread didn't handle the event then let the super class handle it
        else
        	return (super.onTouchEvent(event));
    }

}
