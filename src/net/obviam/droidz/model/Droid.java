/**
 * 
 */
package net.obviam.droidz.model;

import java.util.Random;

import net.obviam.droidz.MainGamePanel;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * @author impaler
 *
 */
@SuppressLint("NewApi")
public class Droid {
	private static final String TAG = MainGamePanel.class.getSimpleName();
	private Bitmap bitmap;	// the actual bitmap
	private double nowX;			// the X coordinate
	private double nowY;			// the Y coordinate
	private double targetX;
	private double targetY;
	private double moveFactor;
	private boolean touched;	// if droid is touched/picked up
	private long lastUpdateTime;
	
	public Droid(Bitmap bitmap, double maxX, double maxY) {
		Random random = new Random();
		
		this.bitmap = bitmap;
		//Bitmap tmp = Bitmap.createBitmap(30, 30, Bitmap.Config.ARGB_8888);
		//this.bitmap = tmp.copy(Bitmap.Config.ARGB_8888, false);
		//bitmap.setPremultiplied(true);
		//this.bitmap.eraseColor(Color.TRANSPARENT);
		//Canvas canvas = new Canvas(bitmap);
		// set drawing colour
		//Paint p = new Paint();
		//p.setColor(Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256)));

		// draw a line onto the canvas
		//canvas.drawLine(0, 0, 30, 30, p);
		//canvas.drawLine(25, 5, 5, 25, p);
		
		nowX = random.nextDouble() * maxX;
		nowY = random.nextDouble() * maxY;
		targetX = nowX;
		targetY = nowY;
		moveFactor = random.nextDouble() * 0.05 + 0.02;
		lastUpdateTime = System.currentTimeMillis();
	}
	
	public Bitmap getBitmap() {
		return bitmap;
	}
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	
	public void setTargetCoor(double x, double y) {
		targetX = x;
		targetY = y;
	}

	public boolean isTouched() {
		return touched;
	}

	public void setTouched(boolean touched) {
		this.touched = touched;
	}
	
	public void draw(Canvas canvas) {
		long now = System.currentTimeMillis();
		long ahead = now - lastUpdateTime;
		if (ahead >= 30) {
			// update time
			if (ahead > 30)
				ahead = 30;
			lastUpdateTime = now - ahead;
			
			//Log.i(TAG, "draw: " + now + ",  touched: " + touched);
			if (touched) {
				double dx = targetX - nowX;
				double dy = targetY - nowY;
				double newX = nowX + dx * moveFactor;
				double newY = nowY + dy * moveFactor;
				nowX = newX;
				nowY = newY;
			}
		}
		canvas.drawBitmap(bitmap, (int)nowX, (int)nowY, null);
	}

	/**
	 * Handles the {@link MotionEvent.ACTION_DOWN} event. If the event happens on the 
	 * bitmap surface then the touched state is set to <code>true</code> otherwise to <code>false</code>
	 * @param eventX - the event's X coordinate
	 * @param eventY - the event's Y coordinate
	 */
	/*public void handleActionDown(int eventX, int eventY) {
		if (eventX >= (x - bitmap.getWidth() / 2) && (eventX <= (x + bitmap.getWidth()/2))) {
			if (eventY >= (y - bitmap.getHeight() / 2) && (y <= (y + bitmap.getHeight() / 2))) {
				// droid touched
				setTouched(true);
			} else {
				setTouched(false);
			}
		} else {
			setTouched(false);
		}

	}*/
}
