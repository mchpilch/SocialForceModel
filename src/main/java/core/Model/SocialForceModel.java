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
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multimap;
import core.Element.*;
import core.MathTool.LinearEquations;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SocialForceModel extends Game {

    World world;
    OrthographicCamera camera;
    Box2DDebugRenderer debugRenderer;

    private float evacuationTimeSeconds = 0f;
    private float evacuationPeriod = 0.05f;
    private float evacuationTimer = 0f;

    private float timeSecondsAngleAdjustment = 0f;
    private float periodAngleAdjustment = 0.1f;
    private float timerAngleAdjustment = 0f;


    Array<Human> peopleStorage = new Array<Human>();
    //Array<Human> toDestroyStorage = new Array<Human>();
    Array<Wall> wallStorage = new Array<Wall>();
    Array<Door> doorStorage = new Array<Door>();
    Array<Room> roomStorage = new Array<Room>();
    Multimap<Room,Door> roomAndDoors = ArrayListMultimap.create();  //https://www.baeldung.com/guava-multimap //allows dupicated keys so one key can have many values

    Array<Float> evacuationTimes = new Array<Float>();
    //PARAMETERS

    float coeffGPlus = 1f; //10f
    float coeffGMinus = -2f; //-3.6f
    float personalborder = 5f; //2f

    float wallRepNomCoeff = 2f; //6f
    float wallRepNomCoeff2 = 0.1f; //0.05f
    int powerR = 2; //2f

    float exitCoefficient = 2f; //3f

    @Override
    public void create() {
        world = new World(new Vector2(0, 0), true);
        camera = new OrthographicCamera(100, 50);
        debugRenderer = new Box2DDebugRenderer();

        //Building D
        float scale = 1.0f;

        float moveX = -12f;
        float moveY = -15f;

        float moveRoomX = moveX+12;
        float moveRoomY = moveY+7.5f;

        int nrOfDoors = 1; //1,2,3

        float doorWidth = 3.5f;
        float door_1_width = doorWidth;
        float door_2_width = doorWidth;
        float door_3_width = doorWidth;

        float halfDoor_1_Width = door_1_width / 2;
        float halfDoor_2_Width = door_2_width / 2;
        float halfDoor_3_Width = door_3_width / 2;

        float doorMargin = 1f;
        float roomMargin = -0.6f;

        Wall wallAH = new Wall();
        //Wall wallBH = new Wall();
        Wall wallBH_1 = new Wall();
        Wall wallBH_2 = new Wall();
        Wall wallBH_3 = new Wall();
        Wall wallBH_4 = new Wall();
        Wall wallCH = new Wall();

        wallStorage.add(wallAH);
        wallStorage.add(wallBH_1);
        wallStorage.add(wallBH_2);
        wallStorage.add(wallBH_3);
        wallStorage.add(wallBH_4);
        wallStorage.add(wallCH);

        wallAH.createWall(0f,  0f, 24f, 0f, world, scale, moveX, moveY);
        if(nrOfDoors == 1){
            wallBH_1.createWall(0f, 15f, 12f - door_1_width, 15f, world, scale, moveX, moveY);
            wallBH_2.createWall(12f + door_1_width, 15f, 24f, 15f, world, scale, moveX, moveY);

            wallStorage.add(wallAH);
            wallStorage.add(wallBH_1);
            wallStorage.add(wallBH_2);
            wallStorage.add(wallCH);

        }
        if(nrOfDoors == 2){
            wallBH_1.createWall(0f, 15f, 6f - door_1_width, 15f, world, scale, moveX, moveY);
            wallBH_2.createWall(6f + door_1_width , 15f, 18f - door_2_width, 15f, world, scale, moveX, moveY);
            wallBH_3.createWall(18f + door_2_width, 15f, 24f, 15f, world, scale, moveX, moveY);

            wallStorage.add(wallAH);
            wallStorage.add(wallBH_1);
            wallStorage.add(wallBH_2);
            wallStorage.add(wallBH_3);
            wallStorage.add(wallCH);
        }
        if(nrOfDoors == 3){
            wallBH_1.createWall(0f, 15f, 6f - door_1_width, 15f, world, scale, moveX, moveY);
            wallBH_2.createWall(6f + door_1_width , 15f, 12f - door_2_width, 15f, world, scale, moveX, moveY);
            wallBH_3.createWall(12f + door_2_width, 15f, 18f - door_3_width, 15f, world, scale, moveX, moveY);
            wallBH_3.createWall(18f + door_3_width, 15f, 24f, 15f, world, scale, moveX, moveY);

            wallStorage.add(wallAH);
            wallStorage.add(wallBH_1);
            wallStorage.add(wallBH_2);
            wallStorage.add(wallBH_3);
            wallStorage.add(wallBH_4);
            wallStorage.add(wallCH);
        }
        wallCH.createWall(0f, 30f, 24f, 30f, world, scale, moveX, moveY);

        Wall wallAV = new Wall();
        Wall wallBV = new Wall();

        wallStorage.add(wallAV);
        wallStorage.add(wallBV);

        wallAV.createWall(0f, 0f, 0f, 30f, world, scale, moveX, moveY);
        wallBV.createWall(24f, 0f, 24f, 30f, world, scale, moveX, moveY);

        Room room1 = new Room( 0f,0f, 24/2f+roomMargin,15/2f+roomMargin, world, "Room-1-TopRight", scale, moveRoomX, moveRoomY);
        Room room2 = new Room(0f,15f, 24/2f+roomMargin,15/2f+roomMargin, world, "Room-2-Exit", scale, moveRoomX, moveRoomY);

        roomStorage.add(room1);
        roomStorage.add(room2);

        float door1X = 0;
        float door2X = 0;
        float door3X = 0;


        if(nrOfDoors == 1){
            door1X = 12;
        }
        if(nrOfDoors == 2){
            door1X = 6;
            door2X = 18;
        }
        if(nrOfDoors == 3){
            door1X = 6;
            door2X = 12;
            door3X = 18;
        }

        Door door1 = new Door(door1X,15 + 1, "door1", scale, moveX, moveY);
        Door door2 = new Door(door2X,15 + 1, "door2", scale, moveX, moveY);
        Door door3 = new Door(door3X,15 + 1, "door3", scale, moveX, moveY);

        doorStorage.add(door1);
        doorStorage.add(door2);
        doorStorage.add(door3);

        roomAndDoors.put(room1,door1);
        roomAndDoors.put(room1,door2);
        roomAndDoors.put(room1,door3);

//        Human man1 = new Human();
//        man1.createMan(2, -13, 0.45f, 25, door1, world);
//        man1.setRoom(room1);
//        peopleStorage.add(man1);

        int nrOfPeds = 50 ;
        for(int i = 0; i < nrOfPeds ; i++){
            Human man = new Human();
            float rand = (float) Math.random();
            if(i<10){
                man.createMan(-3+i%10+rand, -10+rand, 0.45f, 25, door1, world);
            }
            if(i>=10 && i < 20){
                man.createMan(-6+i%10+rand, -11+rand, 0.45f, 25, door1, world);
            }
            if(i>=20 && i < 30){
                man.createMan(-4+i%10+rand, -12+rand, 0.45f, 25, door1, world);
            }
            if(i>=30 && i < 40){
                man.createMan(-4+i%10+rand, -13+rand, 0.45f, 25, door1, world);
            }
            if(i>=40){
                man.createMan(-4+i%10, -14+rand, 0.45f, 25, door1, world);
            }
            man.setRoom(room1);
            peopleStorage.add(man);
        }

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown (int x, int y, int pointer, int button) {

                Vector3 touchedPoint = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
                camera.unproject(touchedPoint);
                Human man = new Human();
                man.createMan(touchedPoint.x, touchedPoint.y, 0.5f, 25, door1, world);
                peopleStorage.add(man);
                //whichRoom(man);
                //Human.createMan(touchedPoint.x, touchedPoint.y, 0.5f, 10, world);
                man.setRoom(room1);//
                return true;
            }
        });

//        Gdx.input.setInputProcessor(new InputAdapter() {
//            @Override
//            public boolean touchDown (int x, int y, int pointer, int button) {
//
//                Vector3 touchedPoint = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
//                camera.unproject(touchedPoint);
//                Human man = new Human();
//                man.createMan(touchedPoint.x, touchedPoint.y, 0.5f, 25, door1, world);
//                Human man1 = new Human();
//                man1.createMan(touchedPoint.x+1, touchedPoint.y, 0.5f, 25, door1, world);
//                Human man2 = new Human();
//                man2.createMan(touchedPoint.x+2, touchedPoint.y, 0.5f, 25, door1, world);
//                Human man3 = new Human();
//                man3.createMan(touchedPoint.x+3, touchedPoint.y, 0.5f, 25, door1, world);
//                Human man4 = new Human();
//                man4.createMan(touchedPoint.x+4, touchedPoint.y, 0.5f, 25, door1, world);
//                Human man5 = new Human();
//                man5.createMan(touchedPoint.x+5, touchedPoint.y, 0.5f, 25, door1, world);
//                Human man6 = new Human();
//                man6.createMan(touchedPoint.x+6, touchedPoint.y, 0.5f, 25, door1, world);
//                Human man7 = new Human();
//                man7.createMan(touchedPoint.x+7, touchedPoint.y, 0.5f, 25, door1, world);
//                Human man8 = new Human();
//                man8.createMan(touchedPoint.x+8, touchedPoint.y, 0.5f, 25, door1, world);
//                Human man9 = new Human();
//                man9.createMan(touchedPoint.x+9, touchedPoint.y, 0.5f, 25, door1, world);
//                Human man10 = new Human();
//                man10.createMan(touchedPoint.x+10, touchedPoint.y, 0.5f, 25, door1, world);
//
//                peopleStorage.add(man);
//                peopleStorage.add(man1);
//                peopleStorage.add(man2);
//                peopleStorage.add(man3);
//                peopleStorage.add(man4);
//                peopleStorage.add(man5);
//                peopleStorage.add(man6);
//                peopleStorage.add(man7);
//                peopleStorage.add(man8);
//                peopleStorage.add(man9);
//                peopleStorage.add(man10);
//
//                man.setRoom(room5);//
//                man1.setRoom(room5);//
//                man2.setRoom(room5);//
//                man3.setRoom(room5);//
//                man4.setRoom(room5);//
//                man5.setRoom(room5);//
//                man6.setRoom(room5);//
//                man7.setRoom(room5);//
//                man8.setRoom(room5);//
//                man9.setRoom(room5);//
//                man10.setRoom(room5);//
//                return true;
//            }
//        });
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(.125f, .125f, .125f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        peopleStorage.forEach(pedestrian -> {

            String roomName = pedestrian.getRoom().getName();
            if(roomName.substring(roomName.length()-4).equals("Exit") && pedestrian.isSave() == false){
                pedestrian.setSave(true);
                pedestrian.setTimeOfEvacuation(evacuationTimer);
            }



            float pedX = pedestrian.body.getPosition().x;
            float pedY = pedestrian.body.getPosition().y;

            Vector2 netGravForce = new Vector2(0f,0f);
            Vector2 netWallForce = new Vector2(0f,0f);
            Vector2 netExitForce = new Vector2(0f,0f);

            Vector2 netTotalForce = new Vector2(0f,0f);

            if(peopleStorage.notEmpty()){
                 netGravForce = calculateNetGravityForce(pedestrian, personalborder, coeffGPlus, coeffGMinus);
                 netExitForce = calculateExitForce(pedestrian, pedestrian.getExit().getX(), pedestrian.getExit().getY(), exitCoefficient);


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
        });

        evacuationTimeSeconds += Gdx.graphics.getDeltaTime();
        if(evacuationTimeSeconds > evacuationPeriod){
            evacuationTimeSeconds -= evacuationPeriod;
            evacuationTimer += evacuationPeriod;
            System.out.println("Time pass " + (float) (Math.round(evacuationTimer * 100.0) / 100.0));


            Iterator<Human> iterator = peopleStorage.iterator(); //to safely remove elements while looping it is recommended to use iterator
            while (iterator.hasNext()) {
                Human pedestrian = iterator.next(); // must be called before you can call i.remove()
                if(pedestrian.isSave()){
                    evacuationTimes.add(pedestrian.getTimeOfEvacuation());
                    iterator.remove();
                }
            }
            System.out.println(evacuationTimes);
            System.out.println("Ilość jaka doszła: " + evacuationTimes.size);
        }

        Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer(
                true,
                true,
                false,
                false,
                true,//
                false
        );
        debugRenderer.render(world, camera.combined); // render all your graphics before you do your physics step, so it won't be out of sync
        world.step(1 / 60f, 6, 2);

//        for(int k = 0; k < toDestroyStorage.size; k++){
//            world.destroyBody(toDestroyStorage.get(k).body);
//            toDestroyStorage.removeIndex(k);
//        }

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
    // WALL END

    // EXIT START

    public String getHumanIdByRegex(String contactOutputHuman){

        Pattern patternHuman = Pattern.compile("id=[0-9]*");

        Matcher matcherHuman = patternHuman.matcher(contactOutputHuman);

        String infoHuman = "";

        while (matcherHuman.find()) {
            infoHuman = matcherHuman.group(0);
        }
        //System.out.println("infoHuman: " + infoHuman);
//        System.out.println(infoHuman);

        Pattern patternHumanBare = Pattern.compile("[0-9]+");

        Matcher matcherHumanBare = patternHumanBare.matcher(infoHuman);

        String bareInfoHuman = "";

        while (matcherHumanBare.find()) {
            bareInfoHuman = matcherHumanBare.group(0);
        }
        //System.out.println("bareInfoHuman: " + bareInfoHuman);
        //System.out.println(bareInfoHuman);

        return bareInfoHuman;
    }

    public String getCurrentRoomNameByRegex(String contactOutputRoom){

        Pattern patternRoom = Pattern.compile("name='Room-[0-9]*-[A-z0-9]*'");

        Matcher matcherRoom = patternRoom.matcher(contactOutputRoom);

        String infoRoom = "";

        while (matcherRoom.find()) {
            infoRoom = matcherRoom.group(0);
        }
        //System.out.println("infoRoom: " + infoRoom);
        //System.out.println(infoRoom);

        Pattern patternRoomBare = Pattern.compile("Room-[0-9]*-[A-z0-9]*");

        Matcher matcherRoomBare = patternRoomBare.matcher(infoRoom);

        String bareInfoRoom = "";

        while (matcherRoomBare.find()) {
            bareInfoRoom = matcherRoomBare.group(0);
        }


//        System.out.println("bareInfoRoom: " + bareInfoRoom);
//        System.out.println(bareInfoRoom);

        return bareInfoRoom;
    }


    public void chooseDoor(Human pedestrian){ //wybierz odpowiednie drzwi w pomieszczeniu przechowywanym w human
        String doorName = "";
        float minDistance = 100000; //duża wartość która nie wystąpi i będzie na pewno nadpisana
        Room pedRoom = pedestrian.getRoom();
        Array<Door> doorInCurrentRoom = new Array<Door>();
        for (Room room : roomAndDoors.keySet()) {
            if(room.getName().equals(pedRoom.getName())){
                int numberOfDoorsInRoom = roomAndDoors.get(room).size();
                for(int position = 0; position < numberOfDoorsInRoom; position++){
                    doorInCurrentRoom.add(Iterables.get(roomAndDoors.get(room), position));
                }
            }
        }
        float pedX = pedestrian.body.getPosition().x;
        float pedY = pedestrian.body.getPosition().y;
        for(int i = 0; i < doorInCurrentRoom.size; i++){
            float doorX = doorInCurrentRoom.get(i).getX();
            float doorY = doorInCurrentRoom.get(i).getY();
            Vector2 distanceVec = new Vector2(doorX-pedX,doorY-pedY);
            float distance = distanceVec.len();
            if(distance <= minDistance){
                minDistance = distance;
                doorName = doorInCurrentRoom.get(i).getCode();
            }
        }

        for(int i = 0; i < doorStorage.size; i++){
            if(doorStorage.get(i).getCode().equals(doorName)){
                pedestrian.setExit(doorStorage.get(i));
                break;
            }
        }

    }

    public String[] whichRoom(Human pedestrian){
        //System.out.println("XXX BEFORE: " + pedestrian.getRoom().getName());
        int numContacts = world.getContactCount();
        if (numContacts > 0) {
            for (Contact contact : world.getContactList()) {

                Fixture fixtureA = contact.getFixtureA();
                Fixture fixtureB = contact.getFixtureB();

                Fixture humanly;// = fixtureA;
                Fixture roomy;// = fixtureB;
                if(fixtureA != null && fixtureB != null && fixtureA.getUserData() != null && fixtureB.getUserData() != null){
                    if(fixtureA.isSensor() || fixtureB.isSensor()){
                        if(fixtureA.getUserData().getClass().equals(Room.class) && fixtureB.getUserData().getClass().equals(Human.class)){
                            //                    System.out.println(fixtureA.getUserData().toString());
                            //                    System.out.println(fixtureB.getUserData().toString());
                            roomy = fixtureA;
                            humanly = fixtureB;
                        } else if (fixtureB.getUserData().getClass().equals(Room.class) && fixtureA.getUserData().getClass().equals(Human.class)) {
                            roomy = fixtureB;
                            humanly = fixtureA;
                        } else {
                            break;
                        }

//                                System.out.println(humanly.getUserData().toString()+"X");
//                                System.out.println(roomy.getUserData().toString()+"Y");

                        String contactOutputHuman = humanly.getUserData().toString();
                        String contactOutputRoom = roomy.getUserData().toString();

                        String humanID = getHumanIdByRegex(contactOutputHuman);
                        String curRoom = getCurrentRoomNameByRegex(contactOutputRoom);

                        String[] res = {humanID,curRoom};

                        return res;

//                        System.out.println("humanID " + humanID);
//                        System.out.println("curRoom " + curRoom);

                        //for(int i = 0; i < peopleStorage.size; i++){
//                            if(String.valueOf(pedestrian.getId()).equals(humanID)){
//                                for(int j = 0; j < roomStorage.size; j++){
//                                    pedestrian.setRoom(roomStorage.get(j));
//                                }
//                            }
                        //}
                        //  for(int i = 0; i < peopleStorage.size; i++){
//                            if(String.valueOf(pedestrian.getId()).equals(humanID)){
//                                System.out.println("Znaleziono peda z takim samym ID");
//                                for(int j = 0; j < roomStorage.size; j++){
////                                    System.out.println(roomStorage.get(j).getName());
////                                    System.out.println(pedestrian.getRoom().getName());
//                                    if(roomStorage.get(j).getName().equals(curRoom)){
//                                        //System.out.println("USTAWIAM NOWY POKOJ : " + curRoom);
//                                        System.out.println("CHANGE FOR PED " + pedestrian.getId() );
//                                        pedestrian.setRoom(roomStorage.get(j));
//
//                                        //System.out.println("SPAWDZENIE 1 " + pedestrian.getRoom());
//                                    }
//
//                                }
//                            }
                        //    }
                    }
                }
            }
            //System.out.println("SPAWDZENIE 2 " + pedestrian.getRoom());
        }
        // System.out.println("YYY AFTER: " + pedestrian.getRoom().getName());
        String[] a = {};
        return  a;
    }

    public void pedestrianRoomAwareness(Human pedestrian){
        //zorientuj się w jakim pokoju jesteś
        String[] roomService = whichRoom(pedestrian);
        if(roomService.length > 0){
            String humanID = roomService[0];
            String curRoom = roomService[1];
            if(String.valueOf(pedestrian.getId()).equals(humanID)){
                for(int j = 0; j < roomStorage.size; j++){
                    if(roomStorage.get(j).getName().equals(curRoom)){
                        pedestrian.setRoom(roomStorage.get(j));
                    }
                }
            }
        }
        //wybierz najbliższe drzwi w tym pokoju
        chooseDoor(pedestrian);
    }


    public Vector2 calculateExitForce(Human pedestrian, float exitCoordinatesX, float exitCoordinatesY, float exitCoefficient) {

        pedestrianRoomAwareness(pedestrian);

        float valuePseudoExitForceX = exitCoordinatesX - pedestrian.body.getPosition().x;
        float valuePseudoExitForceY = exitCoordinatesY - pedestrian.body.getPosition().y;

        Vector2 pseudoExitForce = new Vector2(valuePseudoExitForceX,valuePseudoExitForceY).nor().scl(exitCoefficient);
        return pseudoExitForce;
    }

    //LOGS
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
