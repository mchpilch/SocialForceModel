package core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import core.Bodies.Element;
import core.Bodies.Human;

import java.util.Random;

public class SocialForceModel extends Game {

    World world;
    OrthographicCamera camera;
    Box2DDebugRenderer debugRenderer;

    private float timeSeconds = 0f;
    private float period = 1f;
    private float timer = 0f;
    boolean envObjAdded = false;

    Array<Body> allStorage = new Array<Body>();
    Array<Human> peopleStorage = new Array<Human>();
    Array<Body> environmentStorage = new Array<Body>();

    float exitCoordinatesX = 16;
    float exitCoordinatesY = 6;


    @Override
    public void create() {
        world = new World(new Vector2(0, 0), true);
        camera = new OrthographicCamera(50, 25);
        debugRenderer = new Box2DDebugRenderer();

        //bottom wall
        Element.createEdge(-20, -10f, 20, -10f, 0, world);
        // top wall
        Element.createEdge(-20, 10f, 20, 10f, 0, world);
        // left wall
        Element.createEdge(-20, -10, -20, 10, 0, world);
        // right wall
        Element.createEdge(20, -10, 20, 10, 0, world);

        Element.createRectangle(BodyDef.BodyType.StaticBody,exitCoordinatesX,exitCoordinatesY,1,1,0, world);

        System.out.println("MESSAGE");
        System.out.println( environmentStorage.size);

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown (int x, int y, int pointer, int button) {

                Vector3 touchedPoint = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
                camera.unproject(touchedPoint);
                Human man = new Human();
                man.createMan(touchedPoint.x, touchedPoint.y, 0.5f, 100, world);
                peopleStorage.add(man);
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

        world.getBodies(allStorage);


//        if(all.size > environment.size + people.size){
//            Arrays.sort(all,myComparator); //https://stackoverflow.com/questions/12449766/java-sorting-sort-an-array-of-objects-by-property-object-not-allowed-to-use-co
//            people.add(all.get(all.size - 1));
//        }

//        all.forEach(elem -> {
//            if(elem.getType() == BodyDef.BodyType.DynamicBody){
//                people.add(elem);
//            }
//        });
        float coefficient = 5f;
        if (peopleStorage.notEmpty()) {
            peopleStorage.forEach(pedestrian -> {
                boolean wasFirstPosCalculated = false;;
                    float valuePseudoExitForceX = exitCoordinatesX - pedestrian.body.getPosition().x;
                    float valuePseudoExitForceY = exitCoordinatesY - pedestrian.body.getPosition().y;
                    float coefficientUnitVec = (float) (1/Math.sqrt(valuePseudoExitForceY*valuePseudoExitForceY + valuePseudoExitForceX*valuePseudoExitForceX));
                    Vector2 pseudoExitForce = new Vector2(valuePseudoExitForceX,valuePseudoExitForceY).scl(coefficientUnitVec*coefficient);
                    pedestrian.body.setLinearVelocity(pseudoExitForce);
                    wasFirstPosCalculated = true;

                //boolean w zależności czy wszyscy dostali

                        timeSeconds += Gdx.graphics.getDeltaTime();
                        if(timeSeconds > period){
                            timeSeconds-=period;
                            timer += period;
                            float x = pedestrian.body.getLinearVelocity().x;
                            float y = pedestrian.body.getLinearVelocity().y;

                            float wynik = (float) Math.sqrt(x*x + y*y);
                            System.out.println("szybkość: " + wynik);
                        }


            });
        }

        //clock for world
  /*      timeSeconds += Gdx.graphics.getDeltaTime();
        if(timeSeconds > period){
            timeSeconds-=period;
            timer += period;
            System.out.println(timer);

            System.out.println("all " + allStorage.size);
            System.out.println("environment " + environmentStorage.size);
            System.out.println("people " + peopleStorage.size);

//            if(peopleStorage.size >= 1){
//                int min = 0;
//                int max = peopleStorage.size - 1;
//                Random rand = new Random();
//                int nr = rand.nextInt((max - min) + 1) + min;
//
//                Human p = peopleStorage.get(nr);
//                p.body.setTransform(p.body.getPosition(),p.body.getAngle() + 0.5f);
//                System.out.println("zmieniam kąt");
//            }

//            for(int i = 0; i < all.size; i++){
////                all.sort();
//                System.out.println( i+1 + " " + all.get(i).getType() );
//            }
        }*/
    }

    @Override
    public void dispose () {
        world.dispose();
        debugRenderer.dispose();
    }
}
