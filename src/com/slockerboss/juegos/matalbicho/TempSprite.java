package com.slockerboss.juegos.matalbicho;

import java.util.List;
import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class TempSprite {

	Bitmap bmp;
	GameView gv;

	private int x = 0;
	private int y = 0;

	private int anchoSprite;
	private int altoSprite;

	private int SpriteLive = 10;
	private Rect rectangulo_fuente;
	private Rect rectangulo_destino;
	private Paint paint;
	List<TempSprite> tempSprites;

	public TempSprite(GameView gv, List<TempSprite> tempSprites, Bitmap bmp,
			float xtouch, float ytouch) {
		this.tempSprites = tempSprites;

		this.bmp = bmp;
		this.gv = gv;
		this.x = (int) xtouch;
		this.y = (int) ytouch;

		this.anchoSprite = bmp.getWidth();
		this.altoSprite = bmp.getHeight();
		paint = new Paint();
		paint.setColor(Color.GREEN);

	}
	
	public TempSprite(GameView gv,  Bitmap bmp,
			float xtouch, float ytouch) {
		this.tempSprites = tempSprites;

		this.bmp = bmp;
		this.gv = gv;
		this.x = (int) xtouch;
		this.y = (int) ytouch;

		this.anchoSprite = bmp.getWidth();
		this.altoSprite = bmp.getHeight();
		paint = new Paint();
		paint.setColor(Color.GREEN);

	}

	public void dibuja(Canvas canvas) {

		canvas.drawBitmap(bmp, x - anchoSprite / 2, y - altoSprite / 2, null);

	}

	private void actualizarEstado() throws Throwable {
		SpriteLive--;

		if (SpriteLive < 1) {
		
//			borraSprite();

		}

	}

	@Override
	protected void finalize() throws Throwable {

		super.finalize();
	}

	private void destruir() {

	}

	public boolean isColision(float xtouch, float ytouch) {
		if (xtouch > x && xtouch < x + anchoSprite && ytouch > y
				&& ytouch < y + altoSprite)
			return true;
		else
			return false;
	}

}