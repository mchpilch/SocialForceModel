package core.Model;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import core.Element.Door;
import core.Element.Human;
import core.Element.Wall;
import core.MathTool.LinearEquations;

public class WallForce extends Game {

    World world;
    OrthographicCamera camera;
    Box2DDebugRenderer debugRenderer;

    private float timeSeconds = 0f;
    private float period = 0.05f;
    private float timer = 0f;


    Array<Human> peopleStorage = new Array<Human>();
    Array<Wall> wallStorage = new Array<Wall>();

    Array<Door> doorStorage = new Array<Door>();

    @Override
    public void create() {
        world = new World(new Vector2(0, 0), true);
        camera = new OrthographicCamera(50, 25);
        debugRenderer = new Box2DDebugRenderer();

        float scale = 1f;
        float moveX = 0f;
        float moveY = 0f;

        Wall wall1 = new Wall();
        Wall wall2 = new Wall();
        Wall wall3 = new Wall();
        Wall wall4 = new Wall();

        Wall wall5 = new Wall();
        Wall wall6 = new Wall();

        Wall wall7 = new Wall();
        Wall wall8 = new Wall();


//        wall1.createWall(-20, -10f, 20, -10f, world, scale, moveX,moveY); //bottom wall
//        wall2.createWall(-20, 10f, 20, 10f, world, scale, moveX,moveY); // top wall
//        wall3.createWall(-20, -10, -20, 10, world, scale, moveX,moveY); // left wall
//        wall4.createWall(20, -10, 20, 10, world, scale, moveX,moveY); // right wall
//
        wall5.createWall(-10, -3, 0, -3, world, scale, moveX,moveY);
        wall6.createWall(1, -3, 2, -3, world, scale, moveX,moveY);
        wall7.createWall(5, -3, 10, -3, world, scale, moveX,moveY);

//        wall6.createWall(-20, -6, 20, 8, world, scale, moveX,moveY);

//        wall7.createWall(0, -100, 0, 100, world, scale, moveX,moveY); //x= 0
//        wall8.createWall(-100, 0, 100, 0, world, scale, moveX,moveY); //y = 0

//        wallStorage.add(wall1);
//        wallStorage.add(wall2);
//        wallStorage.add(wall3);
//        wallStorage.add(wall4);

        wallStorage.add(wall5);
        wallStorage.add(wall6);
        wallStorage.add(wall7);

//        wallStorage.add(wall8);

        Door door1 = new Door(0,0, "door1", scale, moveX, moveY);

        doorStorage.add(door1);

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown (int x, int y, int pointer, int button) {

                Vector3 touchedPoint = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
                camera.unproject(touchedPoint);
                Human man = new Human();
                man.createMan(touchedPoint.x, touchedPoint.y, 0.5f, 25, door1, world);
                peopleStorage.add(man);
                return true;
            }
        });
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(.125f, .125f, .125f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float wallRepNomCoeff = 12; //0-200 deafault:10 wallRepulsionNominatorCoefficient
        float wallRepNomCoeff2 = 1f; //0-200 deafault:10 wallRepulsionNominatorCoefficient
        int powerR = 2;

        peopleStorage.forEach(pedestrian -> {

            float pedX = pedestrian.body.getPosition().x;
            float pedY = pedestrian.body.getPosition().y;

            Vector2 netWallForce = new Vector2(0f,0f);

            Vector2 netTotalForce = new Vector2(0f,0f);

            if(peopleStorage.notEmpty()){
                if(wallStorage.notEmpty()){
                    netWallForce = calculateNetWallForce(pedestrian, wallRepNomCoeff, wallRepNomCoeff2, powerR);
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
                System.out.println(pedestrian.body.getLinearVelocity().len());
            }
        });

        Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer(
                true,
                true,
                false,
                false,
                true,//
                false
        );
        debugRenderer.render(world, camera.combined); // render all your graphics before you do your physics step, so it won't be out of sync
        world.step(1 / 60f, 6, 2);;
    }



    @Override
    public void dispose () {
        world.dispose();
        debugRenderer.dispose();
    }
    public Vector2 calculateNetWallForce(Human pedestrian, float wallRepNomCoeff, float wallRepNomCoeff2, int powerR){
        Vector2 netWallForce = new Vector2();

        for(int i = 0; i < wallStorage.size; i++){
            Wall wall = wallStorage.get(i);
            Vector2 pseudoForceWall = calculateWallRepulsionForce(pedestrian, wall, wallRepNomCoeff, wallRepNomCoeff2,powerR);
            netWallForce.add(pseudoForceWall);
        }
        return  netWallForce;
    }

    public Vector2 calculateWallRepulsionForce(Human pedestrian, Wall wall, float wallRepNomCoeff, float wallRepNomCoeff2, int powerR){
//        if(vector.len() == 0) return vector;
        boolean isPedestrianInArea =  checkWallRepulsionArea(pedestrian,wall);
        if(!isPedestrianInArea){
            Vector2 vector = calculateWallRepulsionDirectionAndPhraseOutArea(pedestrian,wall);
            float wallEdgeR = vector.len();
            vector = vector.nor();
            float outAreaCoeff = (float) (wallRepNomCoeff2/(Math.pow(wallEdgeR,powerR)));
            vector = vector.scl(outAreaCoeff);
            return vector;
        }// else licz to wszystko poniżej
        Vector2 vector = calculateWallRepulsionDirectionAndPhraseInArea(pedestrian,wall);
        float wallR = vector.len(); //do wyliczenia 1/r^2
        vector = vector.nor(); //kierunek i zwrot działania siły
        float wallRepDenomCoeff = (float) (1/(Math.pow(wallR,powerR)));
        Vector2 pseudoForceWall = vector.scl(wallRepNomCoeff * wallRepDenomCoeff); //wallRepulsionNominatorCoefficient depending on R
        return pseudoForceWall;
    }

    public Vector2 calculateWallRepulsionDirectionAndPhraseInArea(Human pedestrian, Wall wall){
        Vector2 wallPedestrian = new Vector2(0f,0f);

        float wallX1 = wall.getX1();
        float wallX2 = wall.getX2();
        float wallY1 = wall.getY1();
        float wallY2 = wall.getY2();

        float pedestrianX = pedestrian.body.getPosition().x;
        float pedestrianY = pedestrian.body.getPosition().y;

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

        //Element.createRectangle(BodyDef.BodyType.StaticBody, intersectX,  intersectY,  0.2f,  0.2f,  1,  world);

        float wallPedestianX = pedestrianX - intersectX;
        float wallPedestianY = pedestrianY - intersectY;
        wallPedestrian = new Vector2(wallPedestianX, wallPedestianY);
        return wallPedestrian;
    }
    public Vector2 calculateWallRepulsionDirectionAndPhraseOutArea(Human pedestrian, Wall wall){
        Vector2 wallPedestrian = new Vector2(0f,0f);

        float wallX1 = wall.getX1();
        float wallX2 = wall.getX2();
        float wallY1 = wall.getY1();
        float wallY2 = wall.getY2();

        float pedestrianX = pedestrian.body.getPosition().x;
        float pedestrianY = pedestrian.body.getPosition().y;

        float vec1X = pedestrianX - wallX1;
        float vec1Y = pedestrianY - wallY1;

        float vec2X = pedestrianX - wallX2;
        float vec2Y = pedestrianY - wallY2;

        Vector2 vector1 = new Vector2(vec1X,vec1Y);
        Vector2 vector2 = new Vector2(vec2X,vec2Y);
        if(vector1.len() < vector2.len()){ //odpycha ten kraniec wall który jest bliżej ped
            wallPedestrian = vector1;
        }else{
            wallPedestrian = vector2;
        }
        return wallPedestrian;
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

        if(wallY1 == wallY2){
            float borderXMax = Math.max(wallX1,wallX2);
            float borderXMin = Math.min(wallX1,wallX2);
            if(pedX >= borderXMin && pedX <= borderXMax){
                return true;
            }
            return false;
        }

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

    public float calculatePedestrianAngle(Vector2 netForce){
        float angle = (float) Math.atan2( netForce.y,netForce.x) ;
        return angle;
    }

}
