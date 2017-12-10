package kanhoudevelopment.waifurun.objects;

import android.graphics.BitmapFactory;
import android.view.SurfaceView;
import kanhoudevelopment.waifurun.R;
import kanhoudevelopment.waifurun.GameView;

public class BlockManager
{
    public void BlockManager(){}
    private static final int MAX_BLOCKS = 20;
    SurfaceView view;

    private Block blocks[];

    public void Initialize(GameView v)
    {
        blocks = new Block[MAX_BLOCKS];
        for (int i = 0; i < MAX_BLOCKS; i++)
        {
            blocks[i] = new Block();
            blocks[i].LoadContent(BitmapFactory.decodeResource(v.getResources(),
                    R.drawable.stone));
        }
    }

    public Block[] getBlocks()
    {
        return blocks;
    }

    public int spawnBlock(int speed, int y)
    {
        for (int i = 0; i < MAX_BLOCKS; i++)
            if (!blocks[i].isActive())
            {
                blocks[i].spawn(speed, y);
                return 1;
            }
        return 0;
    }

    public void Update()
    {
        for( int i = 0; i < MAX_BLOCKS; i++) {
            if (blocks[i].isActive()) {
                blocks[i].Update();
            }
        }
    }

    public void draw(GameView v)
    {
        for( int i = 0; i < MAX_BLOCKS; i++) {
            if (blocks[i].isActive()) {
                blocks[i].draw(v);
            }
        }
    }
}