package kanhoudevelopment.waifurun;
import kanhoudevelopment.waifurun.objects.BlockManager;

/**
 * Created by Kevin on 2017-12-10.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;


public class GameLoopThread extends Thread {

    private GameView view;
    private boolean running = false;
    private BlockManager bManager;


    public GameLoopThread(GameView view) {
        this.view = view;
        /*view.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                // ... Respond to touch events
                return hasPressedBtn(v,event);

            }
        });*/
    }
    /*
    private boolean hasPressedBtn(View v, MotionEvent ev) {
        if(ev.getX())
        return true;
    }*/

    public void setRunning(boolean run) {
        running = run;
    }

    @Override
    public void run() {
        bManager = new BlockManager();
        bManager.Initialize(view);
        int a = 0;

        while (running) {

            //DO GAME STUFF HERE

            if (a < 60)
                a++;
            else
            {
                a = 0;
                bManager.spawnBlock(10, 400);
            }

            bManager.Update();


            view.clear();
            //Do own draw stuff here
            //example: view.draw(block.getBitmap(), 10, 10)
            bManager.draw(view);

            //DON'T DRAW AFTER THIS!!!!!
            Canvas c = null;
            try {
                c = view.getHolder().lockCanvas();
                synchronized (view.getHolder()) {
                    view.onDraw(c);
                }
            } finally {
                if (c != null) {
                    view.getHolder().unlockCanvasAndPost(c);
                }
            }
        }
    }




}