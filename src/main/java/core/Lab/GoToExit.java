package core.Lab;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import core.Element.Human;
import core.Element.Wall;

public class GoToExit extends Game {
    World world;
    OrthographicCamera camera;
    Box2DDebugRenderer debugRenderer;

    private float timeSeconds = 0f;
    private float period = 1f;
    private float timer = 0f;

    Array<Human> peopleStorage = new Array<Human>();
    Array<Wall> wallStorage = new Array<Wall>();
    Array<Body> allStorage = new Array<Body>();
    Array<Body> environmentStorage = new Array<Body>();

    float exitCoordinatesX = 10;
    float exitCoordinatesY = 10;

    @Override
    public void create() {
        world = new World(new Vector2(0, 0), true);
        camera = new OrthographicCamera(50, 25);
        debugRenderer = new Box2DDebugRenderer();

        float scale = 1f;
        float moveX = 0f;
        float moveY = 0f;

        Wall wall0bottomLeft = new Wall();
        Wall wall0bottomRight = new Wall();

        //Wall wall1 = new Wall();
        Wall wall2 = new Wall();
        Wall wall3 = new Wall();
        Wall wall4 = new Wall();

        //wall1.createWall(-20, -10f, 20, -10f, world, scale, moveX,moveY); //bottom wall
        wall0bottomLeft.createWall(-20, -10f, -1, -10f, world, scale, moveX,moveY); //bottom wall
        wall0bottomRight.createWall(1, -10f, 20, -10f, world, scale, moveX,moveY); //bottom wall

        wall2.createWall(-20, 10f, 20, 10f, world, scale, moveX,moveY); // top wall
        wall3.createWall(-20, -10, -20, 10, world, scale, moveX,moveY); // left wall
        wall4.createWall(20, -10, 20, 10, world, scale, moveX,moveY); // right wall

        //wallStorage.add(wall1);
        wallStorage.add(wall0bottomLeft);
        wallStorage.add(wall0bottomRight);
        wallStorage.add(wall2);
        wallStorage.add(wall3);
        wallStorage.add(wall4);


        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown (int x, int y, int pointer, int button) {

                Vector3 touchedPoint = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
                camera.unproject(touchedPoint);
                Human man = new Human();
                man.createMan(touchedPoint.x, touchedPoint.y, 0.5f, 25, world);
                peopleStorage.add(man);
                allStorage.add(man.body);
                //Human.createMan(touchedPoint.x, touchedPoint.y, 0.5f, 10, world);
                return true;
            }
        });
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(.125f, .125f, .125f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        debugRenderer.render(world, camera.combined); // render all your graphics before you do your physics step, so it won't be out of sync
        world.step(1 / 60f, 6, 2);

// GO TO EXIT
        float exitCoefficient = 3f;
        if (peopleStorage.notEmpty()) {
            peopleStorage.forEach(pedestrian -> {
                    float currPosX = pedestrian.body.getPosition().x;
                    float currPosY = pedestrian.body.getPosition().y;

                    Vector2 pseudoExitForce = calculateExitForceDirectionAndPhrase(pedestrian,exitCoordinatesX,exitCoordinatesY,exitCoefficient);
                    pedestrian.body.setLinearVelocity(pseudoExitForce);

                    float angle = calculatePedestrianAngle(pseudoExitForce);
                    pedestrian.body.setTransform(currPosX, currPosY, angle);



                    timeSeconds += Gdx.graphics.getDeltaTime();
                    if(timeSeconds > period){
                        timeSeconds -= period;
                        timer += period;
                        float x = pedestrian.body.getLinearVelocity().x;
                        float y = pedestrian.body.getLinearVelocity().y;

                        float wynik = (float) Math.sqrt(x*x + y*y);
                        System.out.println("szybkość: " + wynik);
                    }
            });
        }

                timeSeconds += Gdx.graphics.getDeltaTime();
                    if(timeSeconds > period){
                        timeSeconds-=period;
                        timer += period;
                        System.out.println("allStorage " + allStorage.size + allStorage);
                        System.out.println("peopleStorage " + peopleStorage.size  + peopleStorage);
                        System.out.println("environmentStorage " + environmentStorage.size  + environmentStorage);
                    }

    }

    @Override
    public void dispose () {
        world.dispose();
        debugRenderer.dispose();
    }

    public Vector2 calculateExitForceDirectionAndPhrase(Human pedestrian, float exitCoordinatesX, float exitCoordinatesY, float exitCoefficient){

        float valuePseudoExitForceX = exitCoordinatesX - pedestrian.body.getPosition().x;
        float valuePseudoExitForceY = exitCoordinatesY - pedestrian.body.getPosition().y;

        Vector2 pseudoExitForce = new Vector2(valuePseudoExitForceX,valuePseudoExitForceY).nor().scl(exitCoefficient);
        return pseudoExitForce;
    }
    public float calculatePedestrianAngle(Vector2 netForce){
        float angle = (float) Math.atan2( netForce.y,netForce.x) ;
        return angle;
    }
}

