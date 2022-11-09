package core.Model;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import core.Element.Door;
import core.Element.Element;
import core.Element.Human;
import core.Element.Wall;
import core.MathTool.LinearEquations;

import static core.MathTool.ConvertToShoulderSpan.convertToShoulderSpan;
import static core.MathTool.NormalDistribution.generateRandomBMI;

public class SocialForceModel extends Game {

    World world;
    OrthographicCamera camera;
    Box2DDebugRenderer debugRenderer;
    private float timeSeconds = 0f;
    private float period = 1f;
    private float timer = 0f;

    private float timeSecondsInfo = 0f;
    private float periodInfo = 1f;
    private float timerInfo = 0f;

    private float timeSecondsAngleAdjustment = 0f;
    private float periodAngleAdjustment = 0.1f;
    private float timerAngleAdjustment = 0f;


    Array<Human> peopleStorage = new Array<Human>();
    Array<Wall> wallStorage = new Array<Wall>();
    Array<Body> allStorage = new Array<Body>();
    Array<Body> environmentStorage = new Array<Body>();
    Array<Door> doorStorage = new Array<Door>();

    float exitCoordinatesX = 0;
    float exitCoordinatesY = -12;


    @Override
    public void create() {
        world = new World(new Vector2(0, 0), true);
        camera = new OrthographicCamera(100, 50);
        debugRenderer = new Box2DDebugRenderer();

//        float scale = 2f;
//        float moveX = 0f;
//        float moveY = 0f;
//
//        Wall wall0bottomLeft = new Wall();
//        Wall wall0bottomRight = new Wall();
//
//        Wall wall1bottomLeft = new Wall();
//        Wall wall1bottomRight = new Wall();
//
//        Wall wall2bottomLeft = new Wall();
//        Wall wall2bottomRight = new Wall();
//
////        Wall wall1 = new Wall();
//        Wall wall2 = new Wall();
//        Wall wall3 = new Wall();
//        Wall wall4 = new Wall();
//
//
//        Element.createCircle(0,-12.73f,0.5f,1,world);
//
//
//        //wall1.createWall(-20, -10f, 20, -10f, world); //bottom wall
//        wall0bottomLeft.createWall(-20, -10f, -1f, -10f, world, scale, moveX, moveY); //bottom wall
//        wall0bottomRight.createWall(1f, -10f, 20, -10f, world, scale, moveX, moveY); //bottom wall
//
//        wall1bottomLeft.createWall(-20, -5f, -2, -5f, world, scale, moveX, moveY); //bottom wall
//        wall1bottomRight.createWall(2, -5f, 20, -5f, world, scale, moveX, moveY); //bottom wall
//
//        wall2bottomLeft.createWall(-20, 5f, -2, 5f, world, scale, moveX, moveY); //bottom wall
//        wall2bottomRight.createWall(2, 5f, 20, 5f, world, scale, moveX, moveY); //bottom wall
//
//        wall2.createWall(-20, 10f, 20, 10f, world, scale, moveX, moveY); // top wall
//        wall3.createWall(-20, -10, -20, 10, world, scale, moveX, moveY); // left wall
//        wall4.createWall(20, -10, 20, 10, world, scale, moveX, moveY); // right wall
//
//        //wallStorage.add(wall1);
//        wallStorage.add(wall0bottomLeft);
//        wallStorage.add(wall0bottomRight);
//        wallStorage.add(wall1bottomLeft);
//        wallStorage.add(wall1bottomRight);
//        wallStorage.add(wall2bottomLeft);
//        wallStorage.add(wall2bottomRight);
//        wallStorage.add(wall2);
//        wallStorage.add(wall3);
//        wallStorage.add(wall4);
//
//
//       // Element.createRectangle(BodyDef.BodyType.StaticBody,exitCoordinatesX,exitCoordinatesY,1,1,0, world);
//
////        System.out.println("MESSAGE");
////        System.out.println( environmentStorage.size);





        float scale = 1.0f;
        float moveX = -20f;
        float moveY = -10f;

        //WALLs parallel
        Wall wallAR = new Wall();
        //Wall wallCR = new Wall();
        Wall wallCRseg1 = new Wall();
        Wall wallCRseg2 = new Wall();
        Wall wallCRseg3 = new Wall();
        Wall wallER = new Wall();
        Wall wallFR = new Wall();
        Wall wallMR = new Wall();
        Wall wallNR = new Wall();
        Wall wallGR = new Wall();
        Wall wallHR = new Wall();
        Wall wallOR = new Wall();
        Wall wallPR = new Wall();
        Wall wallIR = new Wall();
        Wall wallJR = new Wall();
        Wall wallKR = new Wall();
        Wall wallLR = new Wall();

        wallAR.createWall(0f, 20f, 40f, 20f, world, scale, moveX, moveY);
        //wallCR.createWall(0f, 0f, 40f, 0f, world, scale, moveX, moveY);
        wallCRseg1.createWall(0f, 0f, 9f, 0f, world, scale, moveX, moveY);
        wallCRseg2.createWall(11f, 0f, 18f, 0f, world, scale, moveX, moveY);
        wallCRseg3.createWall(20f, 0f, 40f, 0f, world, scale, moveX, moveY);
        wallER.createWall(0f, 12f, 3f, 12f, world, scale, moveX, moveY);
        wallFR.createWall(5f, 12f, 22f, 12f, world, scale, moveX, moveY);
        wallMR.createWall(16f, 15f, 17f, 15f, world, scale, moveX, moveY);
        wallNR.createWall(19f, 17f, 27f, 17f, world, scale, moveX, moveY);
        wallGR.createWall(24f, 12f, 40f, 12f, world, scale, moveX, moveY);
        wallHR.createWall(0f, 7f, 9f, 7f, world, scale, moveX, moveY);
        wallOR.createWall(0f, 4f, 9f, 4f, world, scale, moveX, moveY);
        wallPR.createWall(11f, 4f, 12f, 4f, world, scale, moveX, moveY);
        wallIR.createWall(11f, 7f, 18f, 7f, world, scale, moveX, moveY);
        wallJR.createWall(20f, 7f, 21f, 7f, world, scale, moveX, moveY);
        wallKR.createWall(27f, 7f, 28f, 7f, world, scale, moveX, moveY);
        wallLR.createWall(30f, 7f, 40f, 7f, world, scale, moveX, moveY);

        wallStorage.add(wallAR);
        //wallStorage.add(wallCR);
        wallStorage.add(wallCRseg1);
        wallStorage.add(wallCRseg2);
        wallStorage.add(wallCRseg3);
        wallStorage.add(wallER);
        wallStorage.add(wallFR);
        wallStorage.add(wallMR);
        wallStorage.add(wallNR);
        wallStorage.add(wallGR);
        wallStorage.add(wallHR);
        wallStorage.add(wallOR);
        wallStorage.add(wallPR);
        wallStorage.add(wallIR);
        wallStorage.add(wallJR);
        wallStorage.add(wallKR);
        wallStorage.add(wallLR);



        //WALLs perpendicular
        Wall wallBP = new Wall();
        Wall wallCP = new Wall();
        Wall wallQP = new Wall();
        Wall wallTP = new Wall();
        Wall wallUP = new Wall();
        Wall wallVP = new Wall();
        Wall wallWP = new Wall();
        Wall wallSP = new Wall();
        Wall wallRP = new Wall();
        Wall wallZP = new Wall();

        wallBP.createWall(40f, 0f, 40f, 20f, world, scale, moveX, moveY);
        wallCP.createWall(0f, 0f, 0f, 20f, world, scale, moveX, moveY);
        wallQP.createWall(16f, 12f, 16f, 20f, world, scale, moveX, moveY);
        wallTP.createWall(21f, 19f, 21f, 20f, world, scale, moveX, moveY);
        wallUP.createWall(27f, 15f, 27f, 20f, world, scale, moveX, moveY);
        wallVP.createWall(27f, 12f, 27f, 13f, world, scale, moveX, moveY);
        wallWP.createWall(27f, 0f, 27f, 7f, world, scale, moveX, moveY);
        wallSP.createWall(21f, 0f, 21f, 7f, world, scale, moveX, moveY);
        wallRP.createWall(17f, 0f, 17f, 7f, world, scale, moveX, moveY);
        wallZP.createWall(12f, 0f, 12f, 5f, world, scale, moveX, moveY);

        wallStorage.add(wallBP);
        wallStorage.add(wallCP);
        wallStorage.add(wallQP);
        wallStorage.add(wallTP);
        wallStorage.add(wallUP);
        wallStorage.add(wallVP);
        wallStorage.add(wallWP);
        wallStorage.add(wallSP);
        wallStorage.add(wallRP);
        wallStorage.add(wallZP);

        //WALLs other
        Wall wallCrookedOne = new Wall();
        Wall wallCrookedTwo = new Wall();

        wallCrookedOne.createWall(17f, 15f, 18f, 16f, world, scale, moveX, moveY);
        wallCrookedTwo.createWall(19f, 17f, 21f, 19f, world, scale, moveX, moveY);

        wallStorage.add(wallCrookedOne);
        wallStorage.add(wallCrookedTwo);





        //wallAR.createWall(0f, 20f, 40f, 20f, world, scale, moveX, moveY);

        Door door1 = new Door(0,0, "door1");

        doorStorage.add(door1);
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown (int x, int y, int pointer, int button) {

                Vector3 touchedPoint = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
                camera.unproject(touchedPoint);
                Human man = new Human();
                float BMI = generateRandomBMI();
                float shoulderSpan = convertToShoulderSpan(BMI);
                man.createMan(touchedPoint.x, touchedPoint.y, shoulderSpan/2, 1000, door1, world);
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

        float coeffGPlus = 1f; //1f
        float coeffGMinus = -1f; //-1f
        float personalborder = 5f; //5f

        float wallRepNomCoeff = 8f; //8f
        float wallRepNomCoeff2 = 0.1f; //0.1f
        int powerR = 2; //2f

        float exitCoefficient = 5f; //3f



        peopleStorage.forEach(pedestrian -> {

            float pedX = pedestrian.body.getPosition().x;
            float pedY = pedestrian.body.getPosition().y;

            Vector2 netGravForce = new Vector2(0f,0f);
            Vector2 netWallForce = new Vector2(0f,0f);
            Vector2 netExitForce = new Vector2(0f,0f);

            Vector2 netTotalForce = new Vector2(0f,0f);

            if(peopleStorage.notEmpty()){
                 netGravForce = calculateNetGravityForce(pedestrian, personalborder, coeffGPlus, coeffGMinus);
                 netExitForce = calculateExitForceDirectionAndPhrase(pedestrian, exitCoordinatesX,exitCoordinatesY, exitCoefficient);
                if(wallStorage.notEmpty()){
                     netWallForce = calculateNetWallForce(pedestrian, wallRepNomCoeff, wallRepNomCoeff2, powerR);
                }
            }

            netTotalForce.add(netGravForce);
            netTotalForce.add(netWallForce);
            netTotalForce.add(netExitForce);

            pedestrian.body.setLinearVelocity(netTotalForce);

            timeSecondsAngleAdjustment += Gdx.graphics.getDeltaTime();

            if(timeSecondsAngleAdjustment > periodAngleAdjustment){
                timeSecondsAngleAdjustment -= periodAngleAdjustment;
                timerAngleAdjustment += periodAngleAdjustment;

                float angle = calculatePedestrianAngle(netTotalForce);
                pedestrian.body.setTransform(pedX, pedY, angle);
            }

            timeSecondsInfo += Gdx.graphics.getDeltaTime();
            if(timeSecondsInfo > periodInfo){
                timeSecondsInfo -= periodInfo;
                timerInfo += periodInfo;
//                System.out.println(timerInfo);
                showInfo(pedestrian,netTotalForce,netWallForce,netGravForce,netExitForce);

            }
        });

        timeSeconds += Gdx.graphics.getDeltaTime();
        if(timeSeconds > period){
            timeSeconds -= period;
            timer += period;
//            System.out.println(timer);
        }


        debugRenderer.render(world, camera.combined); // render all your graphics before you do your physics step, so it won't be out of sync
        world.step(1 / 60f, 6, 2);

    }



    @Override
    public void dispose () {
        world.dispose();
        debugRenderer.dispose();
    }

    public float calculatePedestrianAngle(Vector2 netForce){
        float angle = (float) Math.atan2( netForce.y,netForce.x) ;
        return angle;
    }

    public Vector2 calculateNetGravityForce(Human pedestrian, float personalborder, float coeffGPlus, float coeffGMinus){
        Vector2 netGravForce = new Vector2(0f,0f);

        float pedX = pedestrian.body.getPosition().x;
        float pedY = pedestrian.body.getPosition().y;

        for(int i = 0; i < peopleStorage.size; i++){
            if(peopleStorage.get(i).getId() == i){ // ten warunek zawsze powinien być spełniony ale tak na wszelki wypadek
                if(pedestrian.getId() != peopleStorage.get(i).getId()){//nie ma sensu liczyć wektora dla samego siebie i tak da zero
                    Vector2 gravityForce = new Vector2(0,0);


                    float storagePedX = peopleStorage.get(i).body.getPosition().x;
                    float storagePedY = peopleStorage.get(i).body.getPosition().y;
                    float valueVecX = storagePedX - pedX;
                    float valueVecY = storagePedY - pedY;
                    float gravityR = new Vector2(valueVecX,valueVecY).len();

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
        return netGravForce;
    }

   //WALL START
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
    // END

    public Vector2 calculateExitForceDirectionAndPhrase(Human pedestrian, float exitCoordinatesX, float exitCoordinatesY, float exitCoefficient){

        float valuePseudoExitForceX = exitCoordinatesX - pedestrian.body.getPosition().x;
        float valuePseudoExitForceY = exitCoordinatesY - pedestrian.body.getPosition().y;

        Vector2 pseudoExitForce = new Vector2(valuePseudoExitForceX,valuePseudoExitForceY).nor().scl(exitCoefficient);
        return pseudoExitForce;
    }

    public static void showInfo(Human pedestrian, Vector2 netTotalForce, Vector2 netWallForce, Vector2 netGravForce, Vector2 netExitForce){//spoko by bylo jkakby w klasie human to bylo trzymane o kadej sile
        System.out.println();
        System.out.println("pedestrian nr    " + pedestrian.getId());
        System.out.println("NET TOTAL:  ❇   " + infoArrow(netTotalForce)  + netTotalForce  + "  value: " + netTotalForce.len() );
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.println("netWallForce  \uD83E\uDDF1   " + infoStrength(netWallForce,netGravForce,netExitForce)  + infoArrow(netWallForce)  + "  value: " +  netWallForce.len()  + "  forcesVec: " + netWallForce     );
        System.out.println("netGravForce  \uD83C\uDF0D   " + infoStrength(netGravForce,netExitForce,netWallForce)  + infoArrow(netGravForce)  + "  value: " + netGravForce.len()   + "  forcesVec: " + netGravForce     );
        System.out.println("netExitForce  \uD83D\uDEAA   " +  infoStrength(netExitForce,netWallForce,netGravForce) + infoArrow(netExitForce)  + "  value: " + netExitForce.len()   + "  forcesVec: " + netExitForce    );
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.println();
    }

    public static String infoArrow(Vector2 vec){
        String arrow = "\uD83D\uDCA2";
        float margin = 1;

        if(vec.x > 0 && vec.y > 0){
            arrow = " ↗️";                   //↗️ UP-RIGHT
        }else if(vec.x < 0 && vec.y > 0){
            arrow = " ↖️";                   //↖️ UP-LEFT
        }else if(vec.x > 0 && vec.y < 0){
            arrow = " ↘️";                   //↘️ DOWN-RIGHT
        }else if(vec.x < 0 && vec.y < 0){
            arrow = " ↙️";                   //↙️ DOWN-LEFT
        }

        if(Math.abs(vec.x) < margin && vec.y > 0){
            arrow = " ⬆ ";                      //⬆  UP
        }else if(Math.abs(vec.x) < margin && vec.y < 0){
            arrow = " ⬇ ";                      //⬇  DOWN
        }else if(Math.abs(vec.y) < margin && vec.x > 0){
            arrow = " ➡ ";                      //➡️ RIGHT
        }else if(Math.abs(vec.y) < margin && vec.x < 0){
            arrow = " ⬅  ";                     //⬅️ LEFT
        }

        return arrow;
    }
    public static String infoStrength(Vector2 vec, Vector2 vecOtherForce1,  Vector2 vecOtherForce2){//w stosunku do innych sił
        String arrow = " ⬜⬜⬜⬜ ";
        float vecsLenTotal =  vec.len() + vecOtherForce1.len() + vecOtherForce2.len();
        if(vec.len() < vecsLenTotal/10){
            return arrow;
        }else if(vec.len() < vecsLenTotal/4){
            arrow = " \uD83D\uDFE8⬜⬜⬜ ";
        } else if (vec.len() < vecsLenTotal/2) {
            arrow = " \uD83D\uDFE8\uD83D\uDFE8⬜⬜ ";
        } else if (vec.len() < (vecsLenTotal/4)*3) {
            arrow = " \uD83D\uDFE7\uD83D\uDFE7\uD83D\uDFE7⬜ ";
        } else {
            arrow = " \uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5\uD83D\uDFE5 ";
        }
        return arrow;
    }
}
