package core.Labs;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;


//https://happycoding.io/tutorials/libgdx/libraries
public class Lab extends Game {
    World world;
    OrthographicCamera camera;
    Box2DDebugRenderer debugRenderer;
    BodyDef bodyDef;
    Body bodyA;

    @Override
    public void create () {
        world = new World(new Vector2(0, 0), true);
        camera = new OrthographicCamera(50, 25);
        debugRenderer = new Box2DDebugRenderer();

        //bottom wall
        createEdge(BodyDef.BodyType.StaticBody, -20, -10f, 20, -10f, 0);
        // top wall
        createEdge(BodyDef.BodyType.StaticBody, -20, 10f, 20, 10f, 0);
        // left wall
        createEdge(BodyDef.BodyType.StaticBody, -20, -10, -20, 10, 0);
        // right wall
        createEdge(BodyDef.BodyType.StaticBody, 20, -10, 20, 10, 0);

        //LABORATORIOUM
        // First we create a body definition
        bodyDef = new BodyDef();
        // We set our body to dynamic, for something like ground which doesn't move we would set it to StaticBody
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        // Set our body's starting position in the world
        bodyDef.position.set(0, 0);

        // Create our body in the world using our body definition
        bodyA = world.createBody(bodyDef);

        // Create a circle shape and set its radius to 6
        CircleShape circle = new CircleShape();
        circle.setRadius(1f);

        // Create a fixture definition to apply our shape to
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.1f;
        fixtureDef.friction = 0f;
        fixtureDef.restitution = 0f; // Make it bounce a little bit

        // Create our fixture and attach it to the body
        Fixture fixture = bodyA.createFixture(fixtureDef);

        // Remember to dispose of any shapes after you're done with them!
        // BodyDef and FixtureDef don't need disposing, but shapes do.
        int x = -50;
        int y = 100;
        float angle = (float) Math.atan2(y,x) ;
        bodyA.setTransform(0,0, angle);
        circle.dispose();

//        body.applyForce(-100.0f, 50.0f, 0.5f, 0f, true);
     //   body.applyForce(100.0f, 0.0f, 0.5f, 0f, true);
        bodyA.applyForceToCenter(x, y, true);
//        body.applyLinearImpulse(10.0f, 0, 0, 0, true);
//        body.applyLinearImpulse(0, 10.0f, 0, 0, true);
//        body.applyForce(100.0f, 0.0f, 1f, 1f, true);
//        body.applyForce(100.0f, 0.0f, 1f, 1f, true);
        //body.applyForce(0, 50.0f, 0, 0, true);

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

        Vector2 vec= new Vector2(1,1);
//        bodyA.applyForceToCenter(vec, true);
        bodyA.setLinearVelocity(vec);
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

