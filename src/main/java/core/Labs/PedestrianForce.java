package core.Labs;

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
import core.Elements.Human;
import core.Elements.Wall;

public class PedestrianForce extends Game {

    World world;
    OrthographicCamera camera;
    Box2DDebugRenderer debugRenderer;

    private float timeSeconds = 0f;
    private float period = 1f;
    private float timer = 0f;
//    int pedIdCounter = 0;
    Array<Human> peopleStorage = new Array<Human>();
    Array<Wall> wallStorage = new Array<Wall>();
    Array<Body> allStorage = new Array<Body>();
    Array<Body> environmentStorage = new Array<Body>();

    @Override
    public void create() {
        world = new World(new Vector2(0, 0), true);
        camera = new OrthographicCamera(50, 25);
        debugRenderer = new Box2DDebugRenderer();
        Wall wall1 = new Wall();
        Wall wall2 = new Wall();
        Wall wall3 = new Wall();
        Wall wall4 = new Wall();

        wall1.createWall(-20, -10f, 20, -10f, 0, world); //bottom wall
        wall2.createWall(-20, 10f, 20, 10f, 0, world); // top wall
        wall3.createWall(-20, -10, -20, 10, 0, world); // left wall
        wall4.createWall(20, -10, 20, 10, 0, world); // right wall

        wallStorage.add(wall1);
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
//                man.setId(pedIdCounter);
//                pedIdCounter++;
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
        float coeffGPlus = 10f;
        float coeffGMinus = -3f;
        float personalborder = 3f;
        peopleStorage.forEach(pedestrian -> {
            float pedX = pedestrian.body.getPosition().x;
            float pedY = pedestrian.body.getPosition().y;
            Vector2 netGravForce = new Vector2(0,0);
            for(int i = 0; i < peopleStorage.size; i++){
                if(peopleStorage.get(i).getId() == i){ // ten warunek zawsze powinien być spełniony ale tak na wszelki wypadek
                    if(pedestrian.getId() != peopleStorage.get(i).getId()){//nie ma sensu liczyć wektora dla samego siebie i tak da zero
                        float storagePedX = peopleStorage.get(i).body.getPosition().x;
                        float storagePedY = peopleStorage.get(i).body.getPosition().y;
                        float valueVecX = storagePedX - pedX;
                        float valueVecY = storagePedY - pedY;
                        float gravityR = new Vector2(valueVecX,valueVecY).len();
                        Vector2 gravityForce = new Vector2(0,0);
                        if(gravityR > personalborder){
                            gravityForce = new Vector2(valueVecX,valueVecY).nor().scl(coeffGPlus/(gravityR*gravityR));
                        }else{
                            gravityForce = new Vector2(valueVecX,valueVecY).nor().scl(coeffGMinus/(gravityR*gravityR));
                        }

                        //pedestrian.humanForces.put(peopleStorage.get(i).getId(), gravityForce);
                        netGravForce.add(gravityForce);
                    }
                }

            }
            pedestrian.body.setLinearVelocity(netGravForce);
            float angle = (float) Math.atan2( netGravForce.y,netGravForce.x) ;
            pedestrian.body.setTransform(pedX, pedY, angle);

            timeSeconds += Gdx.graphics.getDeltaTime();
            if(timeSeconds > period){
                timeSeconds -= period;
                timer += period;
                System.out.println("pedestrian: ID  " +  pedestrian.getId() + " ped.pos:  " + pedestrian.body.getPosition());
//                if(pedestrian.humanForces != null){
                   // System.out.println(pedestrian.humanForces);
//                }
            }
        });

        debugRenderer.render(world, camera.combined);
        world.step(1 / 60f, 6, 2);


    }



    @Override
    public void dispose () {
        world.dispose();
        debugRenderer.dispose();
    }
}