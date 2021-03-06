package kanhoudevelopment.waifurun.objects;

import kanhoudevelopment.waifurun.GameView;
import kanhoudevelopment.waifurun.R;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

public class Player
{

    private int posX, posY;
    private int speedY;
    private boolean jumping;


    Bitmap tex;
    private final int frameHeight = 170;
    int frameWidth = 100;
    private final int frameCount = 4;
    private int currentFrame = 0;
    int frameTicks = 0;
    private final int aniSpeed = 8;
    private final int INITIAL_POSITION_Y;

    public Player(int x, int y, GameView v) {
        posX = x;
        posY = y;
        tex = BitmapFactory.decodeResource(v.getResources(), R.drawable.megumin);
        posY -= (tex.getHeight() + 48);
        INITIAL_POSITION_Y = posY;
    }

    public void update() {
        posY += speedY;
        frameTicks++;
        if(frameTicks == aniSpeed) {
            frameTicks = 0;
            currentFrame++;

            if(currentFrame == frameCount ) {
                currentFrame = 0;
            }
        }

        if(jumping) {
            speedY += 1;
        }

    }


    public boolean isJumping() {
        return jumping;
    }

    public int getBottomY() {
        return posY+frameHeight;
    }
    public void resetPos() {
        posY = INITIAL_POSITION_Y;
    }

    public void landing() {
        speedY = 0;
        jumping = false;
        resetPos();
    }

    public void jump() {
        if(!jumping) {
            speedY = -20;
            jumping = true;
        }

    }


    public void draw(GameView view) {

        Rect frame = new Rect(currentFrame*frameWidth,0,(currentFrame+1)*frameWidth,frameHeight);
        RectF position = new RectF(0, 0, frameWidth, frameHeight);

        Bitmap tmp = Bitmap.createBitmap(frameWidth,frameHeight,Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(tmp);

        c.drawBitmap(tex,frame,position,null);

        view.draw(tmp, posX, posY);
    }
}
