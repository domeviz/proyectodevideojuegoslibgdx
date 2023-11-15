package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

public class Box2DScreen extends BaseScreen{

    private World world;
    private Box2DDebugRenderer renderer;
    private OrthographicCamera camera;
    private Body body,sueloBody, rocaBody;
    private Fixture fixture,sueloFixture, rocaFixture;

    private Boolean colisionDetectada=false;

    public Box2DScreen(MyGdxGame game) {
        super(game);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(Gdx.input.justTouched() &&colisionDetectada==false){
            saltar();

        }
        float velocidadY=body.getLinearVelocity().y;
        body.setLinearVelocity(5,velocidadY);

        world.step(delta,6,2);
        camera.update();
        renderer.render(world,camera.combined);

    }

    @Override
    public void show() {
        world=new World(new Vector2(0,-10),true);
        renderer=new Box2DDebugRenderer();
        camera=new OrthographicCamera(32,18);
//        camera=new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        camera.translate(0,2);

        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Fixture fixtureA=contact.getFixtureA();
                Fixture fixtureB=contact.getFixtureB();

                if(fixtureB==fixture && fixtureA==rocaFixture){
                    colisionDetectada=true;
                }
            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });
        BodyDef bodyDef=createBodyDef();
        body=world.createBody(bodyDef);
        PolygonShape polygonShape=new PolygonShape();
        polygonShape.setAsBox(1,1);
        fixture=body.createFixture(polygonShape,1);

        BodyDef bodyDef2=createBodyDef2();
        sueloBody=world.createBody(bodyDef2);
        PolygonShape polygonShape2=new PolygonShape();
        polygonShape2.setAsBox(50,0);
        sueloFixture=sueloBody.createFixture(polygonShape2,1);

        BodyDef rocBody = createBodyDef3();
        rocaBody = world.createBody(rocBody);
        Vector2 [] vertices = new Vector2[3];
        vertices[0] = new Vector2(-0.5f, -0.5f);
        vertices[1] = new Vector2(0.5f, -0.5f);
        vertices[2] = new Vector2(0, 0.5f);
        PolygonShape shape3 = new PolygonShape();
        shape3.set(vertices);
        rocaFixture = rocaBody.createFixture(shape3, 1);
    }

    private BodyDef createBodyDef(){
        BodyDef def=new BodyDef();
        def.position.set(0,0);
        def.type=BodyDef.BodyType.DynamicBody;
        return def;
    }

    private BodyDef createBodyDef2(){
        BodyDef def=new BodyDef();
        def.position.set(0,0);
        def.type=BodyDef.BodyType.StaticBody;
        return def;
    }

    private BodyDef createBodyDef3() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(11f, 0.5f);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        return bodyDef;
    }

    @Override
    public void dispose() {
        this.renderer.dispose();
        this.world.dispose();
        world.destroyBody(this.body);
    }

    private void saltar(){
        Vector2 posicion=body.getPosition();
        body.applyLinearImpulse(0,20,posicion.x,posicion.y,true);
//        body.applyAngularImpulse(20.5f,true);
    }
}
