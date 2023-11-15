package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;

import actores.ActorJugador;
import actores.ActorRoca;

public class MainGameScreen extends BaseScreen{

    public MainGameScreen(MyGdxGame game) {
        super(game);
        texturaJugador=new Texture("dinosaurio.png");
        texturaRoca=new Texture("cactus.png");
    }

    private Stage stage;
    private ActorJugador jugador;

    private ActorRoca roca;

    private Texture texturaJugador;

    private Texture texturaRoca;

    @Override
    public void show(){
        this.stage=new Stage();
        this.stage.isDebugAll();
        this.jugador=new ActorJugador(texturaJugador);
        this.roca=new ActorRoca(texturaRoca);
        this.stage.addActor(jugador);
        this.stage.addActor(roca);
        this.jugador.setPosition(20,100);
        this.roca.setPosition(400,125);
    }

    private void comprobarColisiones(){
        if(jugador.getX()+jugador.getWidth()>roca.getX()){
            System.out.println("Colision Detectada");
        }
    }
    @Override
    public void hide() {
        stage.dispose();
    }

    @Override
    public void dispose() {
        super.dispose();
        texturaJugador.dispose();
        texturaRoca.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        comprobarColisiones();
        stage.draw();
    }
}
