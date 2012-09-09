/**
 * 
 */
package com.example.sscptablet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import android.os.AsyncTask;

/**
 * @author sam
 *
 */
public class CanTCPTask extends AsyncTask<String, CanState, Void> {
	public final static int PORT = 22222; 
	protected Dashboard dashboard;
	
	CanTCPTask(Dashboard dashboard)
	{
		super();
		this.dashboard = dashboard;
	}
	
	@Override
	protected Void doInBackground(String... params) {
		if(params.length == 1)
		{
			Socket socket;
			InputStream in;
			byte [] buffer = new byte[32];
			try {
				socket = new Socket(params[0], PORT);
				
				in = socket.getInputStream();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			
			while(!socket.isClosed())
			{
				try {
					if(in.read(buffer, 0, 32) == 32)
					{
						CanState canState = new CanState();
						ByteBuffer b = ByteBuffer.wrap(buffer,19,8);
						b.order(ByteOrder.LITTLE_ENDIAN);
						canState.setSpeed(b.getDouble());
						publishProgress(canState);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(isCancelled()) {
					try {
						socket.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}
	
	protected void onProgressUpdate(CanState... progress) {
	    dashboard.updateCanState(progress[0]);
	}	
	
	protected void onCancelled() {
		
		//dashboard.finish();
	}
}
