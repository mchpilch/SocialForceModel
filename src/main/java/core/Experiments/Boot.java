package core.Experiments;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

//https://happycoding.io/tutorials/libgdx/libraries
public class Boot extends Game {
    World world;

    private float timeSeconds = 0f;
    private float period = 1f;
    private int counter = 0;

    OrthographicCamera camera;
    Box2DDebugRenderer debugRenderer;
    Array<Body> bodies = new Array<Body>();
    @Override
    public void create () {
        world = new World(new Vector2(0, -10), true);
        //x y w tym wektorze dedykują działanie sił w świecie
        // 1 problem F = ma -> stała siła = przy m const -> stałe przyśpieszenie obiekty przyśpieszją cały czas
        // 2 problem Siła działa na "świecie" nie na obiekt
        camera = new OrthographicCamera(50, 25);
        debugRenderer = new Box2DDebugRenderer();

         //ground
        createEdge(BodyDef.BodyType.StaticBody, -20, -10f, 20, -10f, 0);
        // celling
        createEdge(BodyDef.BodyType.StaticBody, -20, 10f, 20, 10f, 0);
        // left wall
        createEdge(BodyDef.BodyType.StaticBody, -20, -10, -20, 10, 0);
        // right wall
        createEdge(BodyDef.BodyType.StaticBody, 20, -10, 20, 10, 0);
        // przekątna

//        createEdge(BodyDef.BodyType.StaticBody, -20, -10, 20, 10, 0);
//        createRectangle(BodyDef.BodyType.StaticBody, 0,0,5,5,10);
//        createRectangle(BodyDef.BodyType.DynamicBody, 2,2,2,1,10);
//        createCircle(BodyDef.BodyType.DynamicBody, 0, 0, 1, 3);//initial circle


/*
// First we create a body definition
        BodyDef bodyDef = new BodyDef();
// We set our body to dynamic, for something like ground which doesn't move we would set it to StaticBody
        bodyDef.type = BodyDef.BodyType.DynamicBody;
// Set our body's starting position in the world
        bodyDef.position.set(0, 0);

// Create our body in the world using our body definition
        Body body = world.createBody(bodyDef);

// Create a circle shape and set its radius to 6
        CircleShape circle = new CircleShape();
        circle.setRadius(4f);

// Create a fixture definition to apply our shape to
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 55f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.1f; // Make it bounce a little bit

// Create our fixture and attach it to the body
        Fixture fixture = body.createFixture(fixtureDef);

// Remember to dispose of any shapes after you're done with them!
// BodyDef and FixtureDef don't need disposing, but shapes do.
        circle.dispose();
*/

//klikanie myszką
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown (int x, int y, int pointer, int button) {

                Vector3 touchedPoint = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
                camera.unproject(touchedPoint);
                createCircle(BodyDef.BodyType.DynamicBody, touchedPoint.x, touchedPoint.y, 0.2f, 10);
                return true;
            }
        });
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(.125f, .125f, .125f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        debugRenderer.render(world, camera.combined); // render all your graphics before you do your physics step, so it won't be out of sync
        world.step(1 / 60f, 6, 2);


        timeSeconds +=Gdx.graphics.getDeltaTime();
        if(timeSeconds > period){
            timeSeconds-=period;
            System.out.println("asdasd");
            world.getBodies(bodies);
            world.getBodies(bodies);
            System.out.println( timeSeconds%100);
            System.out.println(bodies.size);

            float  anglee = bodies.get(0).getAngle();
            Vector2  asdf = bodies.get(0).getPosition();


                bodies.get(0).setTransform(asdf,anglee + 1f);


            System.out.println(bodies.get(0));
        }
    }

    @Override
    public void dispose () {
        world.dispose();
        debugRenderer.dispose();
    }

    private Body createEdge(BodyDef.BodyType type, float x1, float y1, float x2, float y2, float density) {
        EdgeShape poly = new EdgeShape();
        poly.set(new Vector2(0, 0), new Vector2(x2 - x1, y2 - y1));

        BodyDef def = new BodyDef();
        def.type = type;
        Body body = world.createBody(def);
        body.createFixture(poly, density);
        body.setTransform(x1, y1, 0);
        poly.dispose();

        return body;
    }

    private Body createRectangle(BodyDef.BodyType type, float x, float y, float width, float height, float density) {
        PolygonShape poly = new PolygonShape();
        poly.setAsBox(width, height);

        BodyDef def = new BodyDef();
        def.type = type;
        Body body = world.createBody(def);
        body.createFixture(poly, density);
        body.setTransform(x, y, 0);
        poly.dispose();

        return body;
    }

    private Body createCircle(BodyDef.BodyType type, float x, float y, float radius, float density) {
        CircleShape poly = new CircleShape();
        poly.setRadius(radius);

        BodyDef def = new BodyDef();
        def.type = type;
        Body body = world.createBody(def);
        body.createFixture(poly, density);
        body.setTransform(x, y, 0);
        poly.dispose();

        return body;
    }

    private Body createMan(BodyDef.BodyType type, float x, float y, float radius, float density) {
        CircleShape poly = new CircleShape();
        poly.setRadius(radius);

        BodyDef def = new BodyDef();
        def.type = type;
        Body body = world.createBody(def);
        body.createFixture(poly, density);
        body.setTransform(x, y, 0);
        poly.dispose();

        return body;
    }
}

