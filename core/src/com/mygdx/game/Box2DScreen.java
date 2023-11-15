package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Box2DScreen extends BaseScreen{

    private World world;
    private Box2DDebugRenderer renderer;
    private OrthographicCamera camera;
    private Body body;
    private Fixture fixture;

    public Box2DScreen(MyGdxGame game) {
        super(game);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        world.step(delta,6,2);
        camera.update();
        renderer.render(world,camera.combined);
    }

    @Override
    public void show() {
        world=new World(new Vector2(0,-10),true);
        renderer=new Box2DDebugRenderer();
        camera=new OrthographicCamera(40,20);
//        camera=new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        camera.translate(0,1);
        BodyDef bodyDef=createBodyDef();
        body=world.createBody(bodyDef);
        PolygonShape polygonShape=new PolygonShape();
        polygonShape.setAsBox(1,1);
        fixture=body.createFixture(polygonShape,1);
    }

    private BodyDef createBodyDef(){
        BodyDef def=new BodyDef();
        def.position.set(0,10);
        def.type=BodyDef.BodyType.DynamicBody;
        return def;
    }

    @Override
    public void dispose() {
        this.renderer.dispose();
        this.world.dispose();
        world.destroyBody(this.body);
    }
}
