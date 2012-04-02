package com.slockerboss.juegos.matalbicho;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class GameView extends SurfaceView {
	
	private int numSprites = 4;
	Loop loop;
	SurfaceHolder sfHolder;

	Bitmap miBitmap;
	Paint paint;

	float xtouch = 0;
	float ytouch = 0;

	private List<Sprite> sprites = new ArrayList<Sprite>();

	private Circle circulo;
	private boolean ultimo = false;

	/* ### CONSTRUCTORS ### */
	public GameView(Context context){
		super(context);
	}
	public GameView(Context context, int numSprites) {
		super(context);
		this.numSprites = numSprites;
		paint = new Paint();
		loop = new Loop(this);
		
		sfHolder = getHolder();
		this.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View gameView, MotionEvent event) {
				xtouch = event.getX();
				ytouch = event.getY();
				return false;

			}
		});
		sfHolder.addCallback(new SurfaceHolder.Callback() {

			public void surfaceCreated(SurfaceHolder holder) {
				crearSprites(GameView.this.numSprites);

				loop.setRunning(true);
				loop.start();

			}

			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {
				// TODO Auto-generated method stub

			}

			public void surfaceDestroyed(SurfaceHolder holder) {
				boolean retry = true;
				loop.setRunning(false);
				while (retry) {
					try {
						loop.join();
						retry = false;
					} catch (InterruptedException e) {
					}
				}

			}
		});

	}

	/* ### EL RESTO ### */
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		circulo = new Circle(this, xtouch, ytouch, 25);

		paint.setColor(Color.RED);
		canvas.drawLine(this.getWidth() / 2, 0, this.getWidth() / 2,
				this.getHeight(), paint);

		if (xtouch != 0) {
			circulo.dibuja(canvas);
		}
		crearOdestruir();
		xtouch = 0;

		for (Sprite sprite : sprites) {
			sprite.dibuja(canvas);
		}

	}

	protected void crearOdestruir() {

		if (xtouch > this.getWidth() / 2) {
			if (numSprites > 0 && !ultimo) {

				eliminarSprites(1);
				numSprites--;
				if (numSprites == 1) {
					ultimo = true;
				}
			}

		} else if (numSprites < 4 && xtouch != 0) {
			crearSprites(1);
			numSprites++;
			ultimo = false;
		}

	}

	private void eliminarSprites(int numSprites) {
		for (int i = 0; i < numSprites; i++) {
			sprites.remove(1);
		}

	}

	private void crearSprites(int numSprites) {
		miBitmap = BitmapFactory
				.decodeResource(getResources(), R.drawable.bad1);

		for (int i = 0; i < numSprites; i++) {
			sprites.add(new Sprite(this, miBitmap));
		}

	}

	public void detenerJuego() {
		loop.setRunning(false);

	}

	public void arrancarJuego() {
		loop.setRunning(true);

	}

	/* ### SETTERS/GETTERS ### */
	public void comunicateXtouch(float x) {
		this.xtouch = x;

	}

	public void comunicateTtouch(float y) {
		this.ytouch = y;

	}

}