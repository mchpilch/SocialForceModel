package core.Labs;

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
import core.Elements.Element;
import core.Elements.Human;
import core.Elements.Wall;
import core.Mathematics.LinearEquations;

import static core.Models.SocialForceModel.showInfo;

public class WallRepulsion extends Game {

    World world;
    OrthographicCamera camera;
    Box2DDebugRenderer debugRenderer;

    private float timeSeconds = 0f;
    private float period = 1f;
    private float timer = 0f;


    Array<Human> peopleStorage = new Array<Human>();
    Array<Wall> wallStorage = new Array<Wall>();

    @Override
    public void create() {
        world = new World(new Vector2(0, 0), true);
        camera = new OrthographicCamera(50, 25);
        debugRenderer = new Box2DDebugRenderer();
        Wall wall1 = new Wall();
        Wall wall2 = new Wall();
        Wall wall3 = new Wall();
        Wall wall4 = new Wall();

        Wall wall5 = new Wall();
        Wall wall6 = new Wall();

        Wall wall7 = new Wall();
        Wall wall8 = new Wall();


//        wall1.createWall(-20, -10f, 20, -10f, 0, world); //bottom wall
        //wall2.createWall(-20, 10f, 20, 10f, 0, world); // top wall
//        wall2.createWall(-20, 10f, -6, 5f, 0, world); // top wall
//        wall3.createWall(-20, -10, -20, 10, 0, world); // left wall
//        wall4.createWall(20, -10, 20, 10, 0, world); // right wall
//
//        wall5.createWall(-10, -3, 10, -3, 0, world);
        wall6.createWall(-20, -6, 20, 8, 0, world);

//        wall7.createWall(0, -100, 0, 100, 0, world); //x= 0
//        wall8.createWall(-100, 0, 100, 0, 0, world); //y = 0

//        wallStorage.add(wall1);
//        wallStorage.add(wall2);
//        wallStorage.add(wall3);
//        wallStorage.add(wall4);
//
//        wallStorage.add(wall5);
        wallStorage.add(wall6);

//        wallStorage.add(wall7);
//        wallStorage.add(wall8);

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown (int x, int y, int pointer, int button) {

                Vector3 touchedPoint = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
                camera.unproject(touchedPoint);
                Human man = new Human();
                man.createMan(touchedPoint.x, touchedPoint.y, 0.5f, 25, world);
                peopleStorage.add(man);
                return true;
            }
        });
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(.125f, .125f, .125f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float wallRepNomCoeff = 1; //0-200 deafault:10 wallRepulsionNominatorCoefficient

        peopleStorage.forEach(pedestrian -> {

            float pedX = pedestrian.body.getPosition().x;
            float pedY = pedestrian.body.getPosition().y;

            Vector2 netWallForce = new Vector2(0f,0f);

            Vector2 netTotalForce = new Vector2(0f,0f);

            if(peopleStorage.notEmpty()){
                if(wallStorage.notEmpty()){
                    netWallForce = calculateNetWallForce(pedestrian, wallRepNomCoeff);
                }
            }

            netTotalForce.add(netWallForce);

            pedestrian.body.setLinearVelocity(netTotalForce);
            float angle = calculatePedestrianAngle(netTotalForce);
            pedestrian.body.setTransform(pedX, pedY, angle);

            timeSeconds += Gdx.graphics.getDeltaTime();
            if(timeSeconds > period){
                timeSeconds -= period;
                timer += period;
                //showInfo(pedestrian,netTotalForce,netWallForce,new Vector2(0f,0f),new Vector2(0f,0f));
            }
        });




        debugRenderer.render(world, camera.combined); // render all your graphics before you do your physics step, so it won't be out of sync
        world.step(1 / 60f, 6, 2);
    }



    @Override
    public void dispose () {
        world.dispose();
        debugRenderer.dispose();
    }

    public Vector2 calculateWallRepulsionDirectionAndPhrase(float wallX1, float wallX2, float wallY1, float wallY2, float pedestrianX, float pedestrianY){

        float intersectX = 0f;
        float intersectY = 0f;

        float a = (wallY2 - wallY1)/(wallX2 - wallX1);//
        float bWall = wallY1 - a*wallX1;//

        if(wallY1 == wallY2){
            intersectX = pedestrianX;
            intersectY = wallY1;
        }else if(wallX1 == wallX2){
            intersectX = wallX1;
            intersectY = pedestrianY;
        }else{

            float bPerpendicular = (1/a) * pedestrianX + pedestrianY;

            float a11 = -a;
            float a12 = 1;
            float k1 = bWall;
            float a21 = (1/a);
            float a22 = 1;
            float k2 = bPerpendicular;

            float[] answer = LinearEquations.solve2x2LinearEquation(a11,a12,a21,a22,k1,k2);
            intersectX = answer[0];
            intersectY = answer[1];
            if( answer == null ){
                System.out.println( "No unique solution exists" );
            }
        }

        Element.createRectangle(BodyDef.BodyType.StaticBody, intersectX,  intersectY,  0.2f,  0.2f,  1,  world);

        float wallPedestianX = pedestrianX - intersectX;
        float wallPedestianY = pedestrianY - intersectY;
        Vector2 wallPedestrian = new Vector2(wallPedestianX, wallPedestianY);
        return wallPedestrian;
    }

    public Vector2 calculateWallRepulsionForce(Vector2 vector, float wallRepNomCoeff, int powerR){

        float wallR = vector.len(); //do wyliczenia 1/r^2
        vector = vector.nor(); //kierunek i zwrot działania siły
        float wallRepDenomCoeff = (float) (1/(Math.pow(wallR,powerR)));
        Vector2 pseudoForceWall = vector.scl(wallRepNomCoeff * wallRepDenomCoeff); //wallRepulsionNominatorCoefficient depending on R
        return pseudoForceWall;
    }

    public float calculatePedestrianAngle(Vector2 netForce){
        float angle = (float) Math.atan2( netForce.y,netForce.x) ;
        return angle;
    }

    public Vector2 calculateNetWallForce(Human pedestrian, float wallRepNomCoeff){
        Vector2 netWallForce = new Vector2();

        float pedestrianX = pedestrian.body.getPosition().x;
        float pedestrianY = pedestrian.body.getPosition().y;

        for(int i = 0; i < wallStorage.size; i++){

            float wallX1 = wallStorage.get(i).getX1();
            float wallX2 = wallStorage.get(i).getX2();
            float wallY1 = wallStorage.get(i).getY1();
            float wallY2 = wallStorage.get(i).getY2();
            System.out.println(checkWallRepulsionArea(pedestrian,wallStorage.get(i)));

            Vector2 wallPedestrian = calculateWallRepulsionDirectionAndPhrase(wallX1,wallX2,wallY1,wallY2,pedestrianX,pedestrianY);
            Vector2 pseudoForceWall = calculateWallRepulsionForce(wallPedestrian, wallRepNomCoeff,2);
            netWallForce.add(pseudoForceWall);

        }
        return  netWallForce;
    }

    public boolean checkWallRepulsionArea(Human pedestrian, Wall wall){
        //równanie prostej na której leży wall
        //y = (y2 - y1)/(x2 - x1)*(x - x1) + y1
        //CEL: znaleźć równania dwóch prostych prostopadłych przechodzących przez (x1,y1) (y2,x2)

        float pedX = pedestrian.body.getPosition().x;
        float pedY = pedestrian.body.getPosition().y;

        float wallX1 = wall.getX1();
        float wallX2 = wall.getX2();
        float wallY1 = wall.getY1();
        float wallY2 = wall.getY2();

        float asz = -(wallX2-wallX1)/(wallY2-wallY1);

        float b1sz = wallY1-asz*wallX1;
        float b2sz = wallY2-asz*wallX2;

        float y1border = asz*pedX + b1sz;
        float y2border = asz*pedX + b2sz;

        float borderMax = Math.max(y2border,y1border);
        float borderMin = Math.min(y2border,y1border);

        if(pedY >= borderMin && pedY <= borderMax){
            return true;
        }
        return false;
    }
}
