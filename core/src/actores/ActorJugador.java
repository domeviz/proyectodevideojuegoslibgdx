package actores;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ActorJugador extends Actor {

    private Texture textureJugador;

    public ActorJugador(Texture textureJugador) {
        this.textureJugador = textureJugador;
    }

    @Override
    public void act(float delta) {

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(textureJugador,getX(),getY());
    }
}
