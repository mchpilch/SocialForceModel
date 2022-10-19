package core.Models;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import core.Elements.Human;
import core.Elements.Wall;
import core.Mathematics.LinearEquations;

public class SocialForceModel extends Game {

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

    float exitCoordinatesX = 20;
    float exitCoordinatesY = 5;


    @Override
    public void create() {
        world = new World(new Vector2(0, 0), true);
        camera = new OrthographicCamera(50, 25);
        debugRenderer = new Box2DDebugRenderer();
        Wall wall1 = new Wall();
        Wall wall2 = new Wall();
        Wall wall3 = new Wall();
        Wall wall4 = new Wall();

//        Wall wall5 = new Wall();
//        Wall wall6 = new Wall();
//
//        Wall wall7 = new Wall();
//        Wall wall8 = new Wall();


        wall1.createWall(-20, -10f, 20, -10f, 0, world); //bottom wall
        wall2.createWall(-20, 10f, 20, 10f, 0, world); // top wall
        wall3.createWall(-20, -10, -20, 10, 0, world); // left wall
        wall4.createWall(20, -10, 20, 10, 0, world); // right wall

//        wall5.createWall(-10, -3, 10, -3, 0, world);
//        wall6.createWall(-20, -6, 20, 8, 0, world);
//
//        wall7.createWall(0, -100, 0, 100, 0, world); //x= 0
//        wall8.createWall(-100, 0, 100, 0, 0, world); //y = 0

        wallStorage.add(wall1);
        wallStorage.add(wall2);
        wallStorage.add(wall3);
        wallStorage.add(wall4);

//        wallStorage.add(wall5);
//        wallStorage.add(wall6);
//
//        wallStorage.add(wall7);
//        wallStorage.add(wall8);

//        allStorage.add(wall1.body);
//        allStorage.add(wall2.body);




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

        // WALLS
        float wallRepNomCoeff = 10; //0-200 deafault:10 wallRepulsionNominatorCoefficient
        if (peopleStorage.notEmpty() && wallStorage.notEmpty()) {
            peopleStorage.forEach(pedestrian -> {
                float pedestrianX = pedestrian.body.getPosition().x;
                float pedestrianY = pedestrian.body.getPosition().y;

                Vector2 wallNetForce = new Vector2();

                for(int i = 0; i < wallStorage.size; i++){


                    float szX = 0f;
                    float szY = 0f;

                    float wallX1 = wallStorage.get(i).getX1();
                    float wallX2 = wallStorage.get(i).getX2();
                    float wallY1 = wallStorage.get(i).getY1();
                    float wallY2 = wallStorage.get(i).getY2();

                    float a = (wallY2 - wallY1)/(wallX2 - wallX1);
                    float bWall = wallY1 - a*wallX1;

                    if(wallY1 == wallY2){
                        szX = pedestrianX;
                        szY = wallY1;
                        //Element.createRectangle(BodyDef.BodyType.StaticBody, szX,  szY,  0.1f,  0.1f,  1,  world);
                    }else if(wallX1 == wallX2){
                        szX = wallX1;
                        szY = pedestrianY;
                        //Element.createRectangle(BodyDef.BodyType.StaticBody, szX,  szY,  0.1f,  0.1f,  1,  world);
                    }else{

                        float bPerpendicular = (1/a) * pedestrianX + pedestrianY;

                        float a11 = -a;
                        float a12 = 1;
                        float k1 = bWall;
                        float a21 = (1/a);
                        float a22 = 1;
                        float k2 = bPerpendicular;

                        float[] answer = LinearEquations.solve2x2LinearEquation(a11,a12,a21,a22,k1,k2);
                        szX = answer[0];
                        szY = answer[1];
                        if( answer == null ){
                            System.out.println( "No unique solution exists" );
                        }

                        //Element.createRectangle(BodyDef.BodyType.StaticBody, szX,  szY,  0.1f,  0.1f,  1,  world);
                    }

                    float wallPedestianX = pedestrianX - szX;
                    float wallPedestianY = pedestrianY - szY;

                    Vector2 wallPedestrian = new Vector2(wallPedestianX, wallPedestianY);
                    float wallR = wallPedestrian.len(); //do wyliczenia 1/r^2
                    wallPedestrian = wallPedestrian.nor(); //kierunek i zwrot działania siły
                    float wallRepDenomCoeff = 1/(wallR*wallR);// r? r^2
                    Vector2 pseudoForceWall = wallPedestrian.scl(wallRepNomCoeff * wallRepDenomCoeff); //wallRepulsionNominatorCoefficient depending on R
                    wallNetForce.add(pseudoForceWall);


                    timeSeconds += Gdx.graphics.getDeltaTime();
                    if(timeSeconds > period){
                        timeSeconds-=period;
                        timer += period;
                        float x = pedestrian.body.getLinearVelocity().x;
                        float y = pedestrian.body.getLinearVelocity().y;
                        //Element.createRectangle(BodyDef.BodyType.DynamicBody, pedestrianX,  pedestrianY,  0.1f,  0.1f,  1,  world);

                        float wynik = (float) Math.sqrt(x*x + y*y);
                        //System.out.println("wall: " + i);
                        //System.out.println("szybkość: " + wynik);

                        System.out.println(pedestrian);
                        System.out.println("szX " + szX);
                        System.out.println("szY " + szY);
                    }
                }
                pedestrian.body.setLinearVelocity(wallNetForce);
                float angle = (float) Math.atan2( wallNetForce.y,wallNetForce.x) ;
                pedestrian.body.setTransform(pedestrianX, pedestrianY, angle);
            });
        }

    }



    @Override
    public void dispose () {
        world.dispose();
        debugRenderer.dispose();
    }
}
