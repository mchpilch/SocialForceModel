package core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import core.Elements.Element;
import core.Elements.Human;
import core.Elements.Wall;

public class SocialForceModel extends Game {

    World world;
    OrthographicCamera camera;
    Box2DDebugRenderer debugRenderer;

    private float timeSeconds = 0f;
    private float period = 1f;
    private float timer = 0f;
    boolean envObjAdded = false;


    Array<Human> peopleStorage = new Array<Human>();
    Array<Wall> wallStorage = new Array<Wall>();
    Array<Body> allStorage = new Array<Body>();
    Array<Body> environmentStorage = new Array<Body>();

    float exitCoordinatesX = 20;
    float exitCoordinatesY = 5;


    @Override
    public void create() {
        world = new World(new Vector2(0, 0), true);
        camera = new OrthographicCamera(50, 25);
        debugRenderer = new Box2DDebugRenderer();
        Wall wall1 = new Wall();
        //bottom wall
        wall1.createWall(-20, 0f, 20, 0f, 0, world);
        // top wall
        Wall wall2 = new Wall();
        wall2.createWall(-20, 6f, 20, 6f, 0, world);
//        Element.createEdge(-10, 5f, 0, 5f, 0, world);
//        // left wall
//        Element.createEdge(-20, -10, -20, 10, 0, world);
//        // right wall
//        Element.createEdge(20, -10, 20, 10, 0, world);
//        Element.createEdge(1, 1, 0, 0, 0, world);

        //wall.createWall(-4f, -2f, 6f, 1f, 0, world);
        wallStorage.add(wall1);
        wallStorage.add(wall2);
        allStorage.add(wall1.body);
        allStorage.add(wall2.body);




       // Element.createRectangle(BodyDef.BodyType.StaticBody,exitCoordinatesX,exitCoordinatesY,1,1,0, world);

//        System.out.println("MESSAGE");
//        System.out.println( environmentStorage.size);

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

       // world.getBodies(allStorage);

//        float coefficient = 3f;
//        if (peopleStorage.notEmpty()) {
//            peopleStorage.forEach(pedestrian -> {
//                    Vector2 netForce = new Vector2(0f,0f);
//
//                    float currPosX = pedestrian.body.getPosition().x;
//                    float currPosY = pedestrian.body.getPosition().y;
//
//                    float valuePseudoExitForceX = exitCoordinatesX - currPosX;
//                    float valuePseudoExitForceY = exitCoordinatesY - currPosY;
//
//                    Vector2 pseudoExitForce = new Vector2(valuePseudoExitForceX,valuePseudoExitForceY).nor().scl(coefficient);
//                    pedestrian.body.setLinearVelocity(pseudoExitForce);
//
//                    float angle = (float) Math.atan2(valuePseudoExitForceY,valuePseudoExitForceX) ;
//                    pedestrian.body.setTransform(currPosX, currPosY, angle);
//
//                    timeSeconds += Gdx.graphics.getDeltaTime();
//                    if(timeSeconds > period){
//                        timeSeconds-=period;
//                        timer += period;
//                        float x = pedestrian.body.getLinearVelocity().x;
//                        float y = pedestrian.body.getLinearVelocity().y;
//
//                        float wynik = (float) Math.sqrt(x*x + y*y);
//                        System.out.println("szybkość: " + wynik);
//                    }
//            });
//        }


//        timeSeconds += Gdx.graphics.getDeltaTime();
//                    if(timeSeconds > period){
//                        timeSeconds-=period;
//                        timer += period;
////                        System.out.println("allStorage " + allStorage.size + allStorage);
////                        System.out.println("peopleStorage " + peopleStorage.size  + peopleStorage);
////                        System.out.println("environmentStorage " + environmentStorage.size  + environmentStorage);
//                    }

        float coeff = 10;
        if (peopleStorage.notEmpty() && wallStorage.notEmpty()) {
            peopleStorage.forEach(pedestrian -> {
                float pedastrianX = pedestrian.body.getPosition().x;
                float pedastrianY = pedestrian.body.getPosition().y;

                Vector2 wallNetForce = new Vector2();

                for(int i = 0; i < wallStorage.size; i++){


                        float szX = 0f;
                        float szY = 0f;

                        float wallX1 = wallStorage.get(i).getX1();
                        float wallX2 = wallStorage.get(i).getX2();
                        float wallY1 = wallStorage.get(i).getY1();
                        float wallY2 = wallStorage.get(i).getY2();

                        if(wallY1 == wallY2){
                            szX = pedastrianX;
                            szY = wallY1;
                           // System.out.println("wallY1 == wallY2");
                        }else if(wallX1 == wallX2){
                            szX = wallX1;
                            szY = pedastrianY;
                            //System.out.println("wallX1 == wallX2");
                        }else{
//                            System.out.println("ddd");
//                            System.out.println(wallY1);
//                            System.out.println(wallY2);
                        }

                        float wallPedestianX = pedastrianX - szX;
                        float wallPedestianY = pedastrianY - szY;
                        Vector2 wallPedastrian = new Vector2(wallPedestianX, wallPedestianY);
                        float r = wallPedastrian.len(); //do wyliczenia 1/r^2
                        wallPedastrian = wallPedastrian.nor(); //kierunek i zwrot działania siły
                        float inverselyCoeff = 1/(r*r);
                        Vector2 pseudoForceWall = wallPedastrian.scl(coeff*inverselyCoeff);
                        wallNetForce.add(pseudoForceWall);


                    timeSeconds += Gdx.graphics.getDeltaTime();
                    if(timeSeconds > period){
                        timeSeconds-=period;
                        timer += period;
                        float x = pedestrian.body.getLinearVelocity().x;
                        float y = pedestrian.body.getLinearVelocity().y;

                        float wynik = (float) Math.sqrt(x*x + y*y);
                        System.out.println("wall: " + i);
                        System.out.println("szybkość: " + wynik);
                        System.out.println("szX " + szX);
                        System.out.println("szY " + szY);
                    }
                }
                pedestrian.body.setLinearVelocity(wallNetForce);
            });
        }
    }



    @Override
    public void dispose () {
        world.dispose();
        debugRenderer.dispose();
    }
}
