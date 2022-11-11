package core.Lab;

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
import core.Room.RoomListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    Array<Door> doorStorage = new Array<Door>();
    Array<Room> roomStorage = new Array<Room>();
    Multimap<Room,Door> roomAndDoors = ArrayListMultimap.create();  //https://www.baeldung.com/guava-multimap //allows dupicated keys so one key can have many values

    RoomListener listener = new RoomListener();


    @Override
    public void create() {
        world = new World(new Vector2(0, 0), true);
        camera = new OrthographicCamera(100, 50);
        debugRenderer = new Box2DDebugRenderer();

        createCollisionListener();

        float scale = 1.0f;
        float moveX = -16f;
        float moveY = -16f;

        float doorWidth = 2f;
        float halfDoorWidth = doorWidth/2;

        Wall wallAR = new Wall();
        Wall wallBR_L = new Wall();
        Wall wallBR_P = new Wall();
        Wall wallCR_L = new Wall();
        Wall wallCR_P = new Wall();
        Wall wallDR = new Wall();


        wallAR.createWall(0f, 0f, 32f, 0f, world, scale, moveX, moveY);
        wallBR_L.createWall(0f, 12f, 24f-halfDoorWidth, 12f, world, scale, moveX, moveY);
        wallBR_P.createWall(24f+halfDoorWidth, 12f, 32f, 12f, world, scale, moveX, moveY);
        wallCR_L.createWall(0f, 20f, 8f-halfDoorWidth, 20f, world, scale, moveX, moveY);
        wallCR_P.createWall(8f+halfDoorWidth, 20f, 32f, 20f, world, scale, moveX, moveY);
        wallDR.createWall(0f, 32f, 32f, 32f, world, scale, moveX, moveY);

        Wall wallAP_T = new Wall();
        Wall wallAP_B = new Wall();
        Wall wallBP_T = new Wall();
        Wall wallBP_B = new Wall();
        Wall wallCP_T = new Wall();
        Wall wallCP_B = new Wall();
        Wall wallDP_T = new Wall();
        Wall wallDP_B = new Wall();

        wallAP_T.createWall(0f, 16f+halfDoorWidth, 0f, 32f, world, scale, moveX, moveY);
        wallAP_B.createWall(0f, 0f, 0f, 16f-halfDoorWidth, world, scale, moveX, moveY);
        wallBP_T.createWall(32f, 16f+halfDoorWidth, 32f, 32f, world, scale, moveX, moveY);
        wallBP_B.createWall(32f, 0f, 32f, 16f-halfDoorWidth, world, scale, moveX, moveY);
        wallCP_T.createWall(16f, 26f+halfDoorWidth, 16f, 32f, world, scale, moveX, moveY);
        wallCP_B.createWall(16f, 20f, 16f, 26f-halfDoorWidth, world, scale, moveX, moveY);
        wallDP_T.createWall(16f, 6f+halfDoorWidth, 16f, 12f, world, scale, moveX, moveY);
        wallDP_B.createWall(16f, 0f, 16f, 6f-halfDoorWidth, world, scale, moveX, moveY);

//        Room room1 = new Room(0,0, 0,0, world,"Room-1-Top-Right");
 //       Room room2 = new Room(0,0, 1,1, world,"Room-2-Top-Left", scale, moveX, moveY);
//        Room room3 = new Room(0,0, 0,0, world,"Room-3-Bottom-Left");
//        Room room4 = new Room(0,0, 0,0, world,"Room-4-Bottom-Right");
        Room room5 = new Room(0.5f+16f,0.5f+16f, 1f,1f, world,"Room-5-MainHall", scale, moveX, moveY);//środek ciała jest ustawiany dlatego do pos dodaje polowe wartosci width i height

//        roomStorage.add(room1);
    //    roomStorage.add(room2);
//        roomStorage.add(room3);
//        roomStorage.add(room4);
        roomStorage.add(room5);


        Door door1 = new Door(0,0, "door1", scale, moveX, moveY);
//        Door door2 = new Door(0,18, "door2");
//        Door door3 = new Door(36,36, "door3");
//        Door door4 = new Door(-36,-36, "door4");

        doorStorage.add(door1);
//        doorStorage.add(door2);
//        doorStorage.add(door3);
//        doorStorage.add(door4);

        roomAndDoors.put(room5,door1);
//        roomAndDoors.put(room5,door2);
//        roomAndDoors.put(room5,door3);
//        roomAndDoors.put(room5,door4);



        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown (int x, int y, int pointer, int button) {

                Vector3 touchedPoint = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
                camera.unproject(touchedPoint);
                Human man = new Human();
                man.createMan(touchedPoint.x, touchedPoint.y, 0.5f, 25, door1, world);
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
//        ShapeRenderer shapeRenderer = new ShapeRenderer();
//
        Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer(
                true,
                true,
                false,
                false,//
                true,
                false
        );
        debugRenderer.render(world,camera.combined);



        //debugRenderer.render(world, camera.combined); // render all your graphics before you do your physics step, so it won't be out of sync
        world.step(1 / 60f, 6, 2);

// GO TO EXIT
        float exitCoefficient = 3f;
        if (peopleStorage.notEmpty()) {
            peopleStorage.forEach(pedestrian -> {
                pedestrian.setRoom(roomStorage.get(0));

                for(int i = 0; i < roomStorage.size; i++){
                    if(roomStorage.get(i).getName().equals(chooseDoor(pedestrian))){
                        pedestrian.setRoom(roomStorage.get(i));
                    }
                }

                chooseDoor(pedestrian);

                float currPosX = pedestrian.body.getPosition().x;
                float currPosY = pedestrian.body.getPosition().y;

                Vector2 pseudoExitForce = calculateExitForceDirectionAndPhrase(pedestrian,exitCoefficient);
                pedestrian.body.setLinearVelocity(pseudoExitForce);

                float angle = calculatePedestrianAngle(pseudoExitForce);
                pedestrian.body.setTransform(currPosX, currPosY, angle);



                timeSeconds += Gdx.graphics.getDeltaTime();
                if(timeSeconds > period){
                    timeSeconds -= period;
                    timer += period;
                    float x = pedestrian.body.getLinearVelocity().x;
                    float y = pedestrian.body.getLinearVelocity().y;
                    //chooseDoor();
                    float wynik = (float) Math.sqrt(x*x + y*y);
                    //System.out.println("szybkość: " + wynik);
                }
            });
        }

        //part responsible for actualising awareness of rooms in pedestrians
        int numContacts = world.getContactCount();
        if (numContacts > 0) {
            for (Contact contact : world.getContactList()) {
                Fixture fixtureA = contact.getFixtureA();
                Fixture fixtureB = contact.getFixtureB();

                Fixture humanly = fixtureA;
                Fixture roomy = fixtureB;
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
                        }

//                        System.out.println(humanly.getUserData().toString());
//                        System.out.println(roomy.getUserData().toString());

                        String contactOutputHuman = humanly.getUserData().toString();
                        String contactOutputRoom = roomy.getUserData().toString();

                        getHumanIdByRegex(contactOutputHuman);
                        getCurrentRoomNameByRegex(contactOutputRoom);
                    }
                }
            }
        }


                timeSeconds += Gdx.graphics.getDeltaTime();
                    if(timeSeconds > period){
                        timeSeconds-=period;
                        timer += period;
//                        System.out.println("allStorage " + allStorage.size + allStorage);
//                        System.out.println("peopleStorage " + peopleStorage.size  + peopleStorage);
//                        System.out.println("environmentStorage " + environmentStorage.size  + environmentStorage);
                    }
    }

    @Override
    public void dispose () {
        world.dispose();
        debugRenderer.dispose();
    }

    public Vector2 calculateExitForceDirectionAndPhrase(Human pedestrian, float exitCoefficient){

        float valuePseudoExitForceX = pedestrian.getExit().getX() - pedestrian.body.getPosition().x;
        float valuePseudoExitForceY = pedestrian.getExit().getY() - pedestrian.body.getPosition().y;

        Vector2 pseudoExitForce = new Vector2(valuePseudoExitForceX,valuePseudoExitForceY).nor().scl(exitCoefficient);
        return pseudoExitForce;
    }

    public String chooseDoor(Human pedestrian){ //wybierz odpowiednie drzwi w pomieszczeniu przechowywanym w human
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
        return doorName;
    }


    //Iterables.get(myMultimap.get(key), position);

    public void checkRoom(){ //sprawdza w którym pomieszczeniu znajduje się pedestrian i ustawia mu odpowiednią wartość w human
       // System.out.println("check room");

    }
    public float calculatePedestrianAngle(Vector2 netForce){
        float angle = (float) Math.atan2( netForce.y,netForce.x) ;
        return angle;
    }

    private void createCollisionListener() {
        world.setContactListener(new RoomListener());
    }

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
        //System.out.println("bareInfoRoom: " + bareInfoRoom);
        //System.out.println(bareInfoRoom);

        return bareInfoRoom;
    }

}

