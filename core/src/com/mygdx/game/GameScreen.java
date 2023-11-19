package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

import entities.ObstaculoEntity;
import entities.PlayerEntity;
import entities.SueloEntity;

public class GameScreen extends BaseScreen{

    private Stage stage;
    private World world;
    private PlayerEntity player;
    private SueloEntity suelo;

    private ObstaculoEntity obstaculo;

    public GameScreen(MyGdxGame game) {
        super(game);
        stage=new Stage(new FitViewport(640,370));
        world=new World(new Vector2(0,-10),true);

    }

    @Override
    public void show() {
        Texture playerTexture=game.getManager().get("aurorus.png");
        player=new PlayerEntity(world,playerTexture,new Vector2(1,1));
        stage.addActor(player);

        Texture sueloTexture=game.getManager().get("suelonieve.png");
        suelo=new SueloEntity(world,sueloTexture,new Vector2(0,0));
        stage.addActor(suelo);

        Texture obstaculoTexture=game.getManager().get("cactusnieve.png");
        obstaculo=new ObstaculoEntity(world,obstaculoTexture,new Vector2(4,1));
        stage.addActor(obstaculo);
    }

    @Override
    public void dispose() {
        stage.dispose();
        world.dispose();
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0.5f,0.9f,0.98f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        world.step(delta,6,2);
        stage.draw();
    }
}
