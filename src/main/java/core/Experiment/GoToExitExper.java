package core.Experiment;

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

public class GoToExitExper extends Game {
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


    @Override
    public void create() {
        world = new World(new Vector2(0, 0), true);
        camera = new OrthographicCamera(100, 50);
        debugRenderer = new Box2DDebugRenderer();

        createCollisionListener();

        float scale = 1f;
        float moveX = -16f;
        float moveY = -16f;

        float doorWidth = 2f;
        float halfDoorWidth = doorWidth/2;


        Room room1 = new Room(8f+moveX,0f+moveY, 16/2f,12/2f, world, "Room-1-TopRight", scale);
        Room room2 = new Room(-8f+moveX,0f+moveY, 16/2f,12/2f, world, "Room-2-TopLeft", scale);
//        Room room3 = new Room(-8f,-10f, 16/2f,12/2f, world, "Room-3-BottomLeft", scale);
//        Room room4 = new Room(8f,-10f, 16/2f,12/2f, world, "Room-4-BottomRight", scale);
        Room room5 = new Room(0f+moveX,15f+moveY, 32/2f,8/2f, world,"Room-5-MainHall", scale);//środek ciała jest ustawiany dlatego do pos dodaje polowe wartosci width i height



        roomStorage.add(room1);
        roomStorage.add(room2);
//        roomStorage.add(room3);
//        roomStorage.add(room4);
        roomStorage.add(room5);


        Door door1 = new Door(0,50, "door1", scale, moveX, moveY);
//        Door door2 = new Door(32,16, "door2", scale, moveX, moveY);
        Door door3 = new Door(-25,0, "door3", scale, moveX, moveY);
//        Door door4 = new Door(24,12, "door4", scale, moveX, moveY);
        Door door5 = new Door(25,0, "door5", scale, moveX, moveY);
//        Door door6 = new Door(16,6, "door6", scale, moveX, moveY);

        doorStorage.add(door1);
//        doorStorage.add(door2);
        doorStorage.add(door3);
//        doorStorage.add(door4);
        doorStorage.add(door5);
//        doorStorage.add(door6);

        roomAndDoors.put(room5,door1);
//        roomAndDoors.put(room5,door2);
        roomAndDoors.put(room1,door5);
//        roomAndDoors.put(room4,door4);
        roomAndDoors.put(room2,door3);
//        roomAndDoors.put(room3,door6);


        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown (int x, int y, int pointer, int button) {

                Vector3 touchedPoint = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
                camera.unproject(touchedPoint);
                Human man = new Human();
                man.createMan(touchedPoint.x, touchedPoint.y, 0.5f, 25, door1, world);
                Human man1 = new Human();
                man1.createMan(touchedPoint.x+1, touchedPoint.y, 0.5f, 25, door1, world);
                Human man2 = new Human();
                man2.createMan(touchedPoint.x+2, touchedPoint.y, 0.5f, 25, door1, world);
                Human man3 = new Human();
                man3.createMan(touchedPoint.x+3, touchedPoint.y, 0.5f, 25, door1, world);
                Human man4 = new Human();
                man4.createMan(touchedPoint.x+4, touchedPoint.y, 0.5f, 25, door1, world);
                Human man5 = new Human();
                man5.createMan(touchedPoint.x+5, touchedPoint.y, 0.5f, 25, door1, world);
                Human man6 = new Human();
                man6.createMan(touchedPoint.x+6, touchedPoint.y, 0.5f, 25, door1, world);
                Human man7 = new Human();
                man7.createMan(touchedPoint.x+7, touchedPoint.y, 0.5f, 25, door1, world);
                Human man8 = new Human();
                man8.createMan(touchedPoint.x+8, touchedPoint.y, 0.5f, 25, door1, world);
                Human man9 = new Human();
                man9.createMan(touchedPoint.x+9, touchedPoint.y, 0.5f, 25, door1, world);
                Human man10 = new Human();
                man10.createMan(touchedPoint.x+10, touchedPoint.y, 0.5f, 25, door1, world);

                peopleStorage.add(man);
                peopleStorage.add(man1);
                peopleStorage.add(man2);
                peopleStorage.add(man3);
                peopleStorage.add(man4);
                peopleStorage.add(man5);
                peopleStorage.add(man6);
                peopleStorage.add(man7);
                peopleStorage.add(man8);
                peopleStorage.add(man9);
                peopleStorage.add(man10);
                allStorage.add(man.body);
                //Human.createMan(touchedPoint.x, touchedPoint.y, 0.5f, 10, world);
                man.setRoom(room5);//
                man1.setRoom(room5);//
                man2.setRoom(room5);//
                man3.setRoom(room5);//
                man4.setRoom(room5);//
                man5.setRoom(room5);//
                man6.setRoom(room5);//
                man7.setRoom(room5);//
                man8.setRoom(room5);//
                man9.setRoom(room5);//
                man10.setRoom(room5);//
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
        //part responsible for actualising awareness of rooms in pedestrians
        float exitCoefficient = 3f;
        if (peopleStorage.notEmpty()) {
            peopleStorage.forEach(pedestrian -> {
                if(whichRoom(pedestrian).length > 0){
                    String humanID = whichRoom(pedestrian)[0];
                    String curRoom = whichRoom(pedestrian)[1];
                    if(String.valueOf(pedestrian.getId()).equals(humanID)){
                        System.out.println("Znaleziono peda z takim samym ID");
                        for(int j = 0; j < roomStorage.size; j++){
    //                                    System.out.println(roomStorage.get(j).getName());
    //                                    System.out.println(pedestrian.getRoom().getName());

                            if(roomStorage.get(j).getName().equals(curRoom)){
                                //System.out.println("USTAWIAM NOWY POKOJ : " + curRoom);
                                System.out.println("CHANGE FOR PED " + pedestrian.getId() );
                                pedestrian.setRoom(roomStorage.get(j));

                                //System.out.println("SPAWDZENIE 1 " + pedestrian.getRoom());
                            }

                        }
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
                    //System.out.println("PED ROOM " + pedestrian.getId() + " " + pedestrian.getRoom());
                }
            });
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

    public void chooseDoor(Human pedestrian){ //wybierz odpowiednie drzwi w pomieszczeniu przechowywanym w human
        String doorName = "";
        float minDistance = 100000f; //duża wartość która nie wystąpi i będzie na pewno nadpisana
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
