package actores;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ActorRoca extends Actor {
    private Texture textureRoca;

    private boolean alive;

    public boolean isAlive(){
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    public ActorRoca(Texture textureRoca) {
        this.textureRoca = textureRoca;
        setSize(textureRoca.getWidth(),textureRoca.getHeight());
        this.alive=true;
    }

    @Override
    public void act(float delta) {
        if(isAlive()){
            setX(getX()-250*delta);
        } else{
            setX(getX());
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(textureRoca,getX(),getY());
    }
}
