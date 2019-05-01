package com.example.slotmachine;

import java.util.HashMap;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;


/*___________________________________________________________________
|
| Class: SoundManager
|
| Description: Class to manage playing of sounds.
|__________________________________________________________________*/
public class soundmanager 
{
	private SoundPool 	  			 	mSoundPool;		// android object to load and play sounds
	private HashMap <Integer, Integer> 	mSoundPoolMap;	// hashmap to store sounds once they are loaded
	private AudioManager 				mAudioManager;	// handle to the service that plays the sounds
	private Context 	  				mContext;		// handle to the application context	
	private MediaPlayer					mMediaPlayer = null;
	
	/*___________________________________________________________________
	|
	| Function: Init
	|
	| Description: Initializes the sound manager.
	|__________________________________________________________________*/
	public void Init (Context theContext, int max_simultaneous_streams, boolean playmusic, int music_resid) 
	{
	    mContext  	  = theContext;
	    mSoundPool 	  = new SoundPool (max_simultaneous_streams, AudioManager.STREAM_MUSIC, 0);
	    mSoundPoolMap = new HashMap<Integer, Integer> ();
	    mAudioManager = (AudioManager)mContext.getSystemService (Context.AUDIO_SERVICE);
	    if (playmusic)
	    	mMediaPlayer  = MediaPlayer.create (theContext, music_resid);
	}
	
	/*___________________________________________________________________
	|
	| Function: Free
	|
	| Description: Frees the sound manager.
	|__________________________________________________________________*/
	public void Free () 
	{
		mMediaPlayer.reset();
		mMediaPlayer.release ();
		mSoundPool.release ();
	}
	
	/*___________________________________________________________________
	|
	| Function: AddSound
	|
	| Description: Adds a sound.
	|__________________________________________________________________*/	
	public void AddSound (int index, int SoundID)
	{
	    mSoundPoolMap.put (index, mSoundPool.load(mContext, SoundID, 1));
	}
	
	/*___________________________________________________________________
	|
	| Function: PlaySound
	|
	| Description: Plays a sound.
	|__________________________________________________________________*/	
	public int PlaySound (int index)
	{
		float streamVolumeCurrent = mAudioManager.getStreamVolume   (AudioManager.STREAM_MUSIC);
		float streamVolumeMax 	  = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		float volume 			  = streamVolumeCurrent / streamVolumeMax; 
	    return (mSoundPool.play (mSoundPoolMap.get(index), volume, volume, 1, 0, 1f));
	}
	 
	/*___________________________________________________________________
	|
	| Function: PlayLoopedSound
	|
	| Description: Plays a sound with infinite looping.
	|__________________________________________________________________*/	
	public int PlayLoopedSound (int index)
	{
	    float streamVolumeCurrent = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
	    float streamVolumeMax     = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
	    float volume			  = streamVolumeCurrent / streamVolumeMax;
	    return (mSoundPool.play (mSoundPoolMap.get(index), volume, volume, 1, -1, 1f));
	}	
	
	/*___________________________________________________________________
	|
	| Function: StopSound
	|
	| Description: Stops playing a sound.
	|__________________________________________________________________*/	
	public void StopSound (int index)
	{
//	    mSoundPool.stop(mSoundPoolMap.get(index));
		mSoundPool.stop(index);
	}

	/*___________________________________________________________________
	|
	| Function: PlayMusic
	|
	| Description: Plays a music file.
	|__________________________________________________________________*/	
	public void PlayMusic ()
	{
		if (mMediaPlayer != null)
			if (! mMediaPlayer.isPlaying ()) {
				mMediaPlayer.setLooping(false);
				mMediaPlayer.setVolume(1f, 1f);
				mMediaPlayer.seekTo (0);
				mMediaPlayer.start ();
			}
	}

	/*___________________________________________________________________
	|
	| Function: PauseMusic
	|
	| Description: Pauses playback of music.
	|__________________________________________________________________*/	
	public void PauseMusic ()
	{
		if (mMediaPlayer != null)
			if (mMediaPlayer.isPlaying ()) 
				mMediaPlayer.pause ();
	}

}
