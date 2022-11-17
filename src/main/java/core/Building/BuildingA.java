package core.Building;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import core.Element.Door;
import core.Element.Human;
import core.Element.Room;
import core.Element.Wall;

public class BuildingA extends Game {
    World world;
    OrthographicCamera camera;
    Box2DDebugRenderer debugRenderer;

    Array<Human> peopleStorage = new Array<Human>();
    Array<Wall> wallStorage = new Array<Wall>();
    Array<Door> doorStorage = new Array<Door>();
    Array<Room> roomStorage = new Array<Room>();
    Multimap<Room,Door> roomAndDoors = ArrayListMultimap.create();  //https://www.baeldung.com/guava-multimap //allows dupicated keys so one key can have many values

    //suggested PARAMETERS

    float coeffGPlus = 2f; //10f
    float coeffGMinus = -3f; //-3.6f
    float personalborder = 1f; //2f

    float wallRepNomCoeff = 8f; //6f
    float wallRepNomCoeff2 = 0.03f; //0.05f
    int powerR = 2; //2f

    float exitCoefficient = 4f; //3f

    @Override
    public void create() {
        world = new World(new Vector2(0, 0), true);
        camera = new OrthographicCamera(100, 50);
        debugRenderer = new Box2DDebugRenderer();

        //Building_A

        //PARAMETERS

        float scale = 1.0f;
        float moveX = -20f;
        float moveY = -10f;

        float moveRoomX =  moveX;
        float moveRoomY =  moveY;

        int numberOfDoors = 1; //0-1-2-3-4

        float doorWidth = 2f;
        float door_1_width = doorWidth;
        float door_2_width = doorWidth;
        float door_3_width = 0;
        float door_4_width = 0;

        float halfDoor_1_Width = door_1_width/2;
        float halfDoor_2_Width = door_2_width/2;
        float halfDoor_3_Width = door_3_width/2;
        float halfDoor_4_Width = door_4_width/2;

        float doorMargin = 0f;
        float roomMargin = -0.4f;

        //WALL SECTION

        //horizontal (poziome)
        Wall wallAH1 = new Wall(); //door1
        Wall wallAH2 = new Wall(); //door1  //door2
        Wall wallAH3 = new Wall();          //door2
        Wall wallBH1 = new Wall();
        Wall wallBH2 = new Wall();
        Wall wallCH1 = new Wall();
        Wall wallCH2 = new Wall();
        Wall wallCH3 = new Wall();
        Wall wallCH4 = new Wall();
        Wall wallCH5 = new Wall();
        Wall wallDH1 = new Wall();
        Wall wallDH2 = new Wall();
        Wall wallDH3 = new Wall();
        Wall wallEH = new Wall();
        Wall wallFH = new Wall();


        wallAH1.createWall(0f, 0f, 10f-halfDoor_1_Width, 0f, world, scale, moveX, moveY);
        wallAH2.createWall(10f+halfDoor_1_Width, 0f, 19f-halfDoor_2_Width, 0f, world, scale, moveX, moveY);
        wallAH3.createWall(19f+halfDoor_2_Width, 0f, 40f, 0f, world, scale, moveX, moveY);
        wallBH1.createWall(0f, 4f, 9f, 4f, world, scale, moveX, moveY);
        wallBH2.createWall(11f, 4f, 12f, 4f, world, scale, moveX, moveY);
        wallCH1.createWall(0f, 7f, 9f, 7f, world, scale, moveX, moveY);
        wallCH2.createWall(11f, 7f, 18f, 7f, world, scale, moveX, moveY);
        wallCH3.createWall(20f, 7f, 21f, 7f, world, scale, moveX, moveY);
        wallCH4.createWall(27f, 7f, 28f, 7f, world, scale, moveX, moveY);
        wallCH5.createWall(30f, 7f, 40f, 7f, world, scale, moveX, moveY);
        wallDH1.createWall(0f, 12f, 3f, 12f, world, scale, moveX, moveY);
        wallDH2.createWall(5f, 12f, 22f, 12f, world, scale, moveX, moveY);
        wallDH3.createWall(24f, 12f, 40f, 12f, world, scale, moveX, moveY);
        wallEH.createWall(19f, 17f, 27f, 17f, world, scale, moveX, moveY);
        wallFH.createWall(0f, 20f, 40f, 20f, world, scale, moveX, moveY);

        //vertical (pionowe)
        Wall wallAV_T = new Wall();   //door4
        Wall wallAV_B = new Wall();   //door4
        Wall wallBV = new Wall();
        Wall wallCV = new Wall();
        Wall wallDV = new Wall();
        Wall wallEV = new Wall();
        Wall wallFV = new Wall();
        Wall wallGV_T = new Wall();
        Wall wallGV_B = new Wall();
        Wall wallHV_T = new Wall();   //door3
        Wall wallHV_B = new Wall();   //door3


        wallAV_B.createWall(0f, 0f, 0f, 9.5f-halfDoor_4_Width, world, scale, moveX, moveY);
        wallAV_T.createWall(0f, 9.5f+halfDoor_4_Width, 0f, 20f, world, scale, moveX, moveY);
        wallBV.createWall(12f, 0f, 12f, 5f, world, scale, moveX, moveY);
        wallCV.createWall(16f, 12f, 16f, 20f, world, scale, moveX, moveY);
        wallDV.createWall(17f, 0f, 17f, 7f, world, scale, moveX, moveY);
        wallEV.createWall(21f, 0f, 21f, 7f, world, scale, moveX, moveY);
        wallFV.createWall(27f, 0f, 27f, 7f, world, scale, moveX, moveY);
        wallGV_T.createWall(27f, 15f, 27f, 18f, world, scale, moveX, moveY);
        wallGV_B.createWall(27f, 12f, 27f, 13f, world, scale, moveX, moveY);
        wallHV_B.createWall(40f, 0f, 40f, 9.5f-halfDoor_3_Width, world, scale, moveX, moveY);
        wallHV_T.createWall(40f, 9.5f+halfDoor_3_Width, 40f, 20f, world, scale, moveX, moveY);

        //Crooked
        Wall wallC1 = new Wall();
        Wall wallC2 = new Wall();

        wallC1.createWall(16f, 14f, 18f, 16f, world, scale, moveX, moveY); //przek w kwadracie
        wallC2.createWall(19f, 17f, 22f, 20f, world, scale, moveX, moveY);

        //ROOM SECTION //roomMargin < 0
        Vector2 vecR9_1 = new Vector2(0,0).add(-roomMargin,-roomMargin);
        Vector2 vecR9_2 = new Vector2(11,0).add(roomMargin,-roomMargin);
        Vector2 vecR9_3 = new Vector2(11,5).add(roomMargin,roomMargin);
        Vector2 vecR9_4 = new Vector2(3,5).add(0.2f,roomMargin);
        Vector2 vecR9_5 = new Vector2(0,2).add(-roomMargin,-0.2f);


        Vector2[] polyVerR9 = {
                vecR9_1,
                vecR9_2,
                vecR9_3,
                vecR9_4,
                vecR9_5
        };




        Vector2 vecR11_1 = new Vector2(0,0).add(-roomMargin-0.3f,-roomMargin+0.3f);
        Vector2 vecR11_2= new Vector2(0,6).add(-roomMargin-0.3f,roomMargin+0.3f);
        Vector2 vecR11_3 = new Vector2(6,6).add(roomMargin-0.3f,roomMargin+0.3f);

        Vector2[] polyVerR11 = {
                vecR11_1,
                vecR11_2,
                vecR11_3,
        };



        Vector2 vecR12_1 = new Vector2(0,0).add(-2*roomMargin,-roomMargin);
        Vector2 vecR12_2 = new Vector2(8,0).add(roomMargin,-roomMargin);
        Vector2 vecR12_3 = new Vector2(8,3).add(roomMargin,roomMargin);
        Vector2 vecR12_4 = new Vector2(3,3).add(0,roomMargin);

        Vector2[] polyVerR12 = {
                vecR12_1,
                vecR12_2,
                vecR12_3,
                vecR12_4,
        };




        //pozycja lewy dolny róg, + połowa hx lub hy
        Room room1 = new Room( 0f + 12 / 2f, 0f + 4 / 2f, 12 / 2f + roomMargin,4 / 2f  + roomMargin, world,  "Room-1-X",  scale, moveRoomX, moveRoomY);
        Room room2 = new Room( 0f + 12 / 2f, 4f + 3 / 2f, 12 / 2f + roomMargin,3 / 2f  + roomMargin, world,  "Room-2-X",  scale, moveRoomX, moveRoomY);
        Room room3 = new Room(12f + 5  / 2f, 0f + 7 / 2f, 5  / 2f + roomMargin,7 / 2f  + roomMargin, world,  "Room-3-X",  scale, moveRoomX, moveRoomY);
        Room room4 = new Room(17f + 4  / 2f, 0f + 7 / 2f, 4  / 2f + roomMargin,7 / 2f  + roomMargin, world,  "Room-4-X",  scale, moveRoomX, moveRoomY);
        Room room5 = new Room(21f + 6  / 2f, 0f + 7 / 2f, 6  / 2f + roomMargin,7 / 2f  + roomMargin, world,  "Room-5-X",  scale, moveRoomX, moveRoomY);
        Room room6 = new Room(27f + 13 / 2f, 0f + 7 / 2f, 13 / 2f + roomMargin,7 / 2f  + roomMargin, world,  "Room-6-X",  scale, moveRoomX, moveRoomY);
        Room room7 = new Room( 0f + 40 / 2f, 7f + 5 / 2f, 40 / 2f + roomMargin,5 / 2f  + roomMargin, world,  "Room-7-X",  scale, moveRoomX, moveRoomY);
        Room room8 = new Room( 0f + 16 / 2f,12f + 8 / 2f, 16 / 2f + roomMargin,8 / 2f  + roomMargin, world,  "Room-8-X",  scale, moveRoomX, moveRoomY);
        Room room10 = new Room(27f + 13 / 2f,12f + 8  /  2f,13 / 2f + roomMargin,8  /  2f + roomMargin, world,"Room-10-X", scale, moveRoomX, moveRoomY);

        Room room9 = new Room(16,12, polyVerR9, world,"Room-9-X", scale, moveX, moveY);
        Room room11 = new Room(16,14, polyVerR11, world,"Room-11-X", scale, moveX, moveY);
        Room room12 = new Room(19,17, polyVerR12, world,"Room-12-X", scale, moveX, moveY);


        Room room112 = new Room(12f,-5f, 24/2+ roomMargin,10/2f+ roomMargin, world, "Room-112-BottomExit", scale, moveRoomX, moveRoomY);
        Room room103 = new Room(50f,20/2f, 20/2+ roomMargin,20/2f+ roomMargin, world, "Room-101-LeftExit", scale, moveRoomX, moveRoomY);
        Room room104 = new Room(-10f,20/2f, 20/2+ roomMargin,20/2f+ roomMargin, world, "Room-101-RightExit", scale, moveRoomX, moveRoomY);

        Room room1000 = new Room(-500,-500,1,1,world,"Room-1000-FirstRoom",scale,moveRoomX,moveRoomY);

        roomStorage.add(room1);
        roomStorage.add(room2);
        roomStorage.add(room3);
        roomStorage.add(room4);
        roomStorage.add(room5);
        roomStorage.add(room6);
        roomStorage.add(room7);
        roomStorage.add(room8);
        roomStorage.add(room9); //poly
        roomStorage.add(room10);
        roomStorage.add(room11); //poly
        roomStorage.add(room12); //poly

        roomStorage.add(room112);
        roomStorage.add(room103);
        roomStorage.add(room104);


        Door door1 = new Door( 10f,    0f - doorMargin,   "door1", scale, moveX, moveY);
        Door door2 = new Door( 19f,    0f - doorMargin,   "door2", scale, moveX, moveY);
        Door door3 = new Door( 10f,    4f - doorMargin,   "door3", scale, moveX, moveY);
        Door door4 = new Door( 10f,    7f - doorMargin,   "door4", scale, moveX, moveY);
        Door door5 = new Door( 12f - doorMargin,    6f,   "door5", scale, moveX, moveY);
        Door door6 = new Door( 19f,    7f - doorMargin,    "door6", scale, moveX, moveY);
        Door door7 = new Door( 23.5f,  7f + 2,   "door7", scale, moveX, moveY);//
        Door door8 = new Door( 29f,    7f + 2,   "door8", scale, moveX, moveY);//
        Door door9 = new Door(  4f,    12f - doorMargin,  "door9", scale, moveX, moveY);
        Door door10 = new Door(23f,    12f - doorMargin,  "door10", scale, moveX, moveY);
        Door door11 = new Door(27f - doorMargin,   14f,   "door11", scale, moveX, moveY);
        Door door12 = new Door(19f + doorMargin,   16f- doorMargin,   "door12", scale, moveX, moveY);
        Door door13 = new Door( 27,   19,    "door13", scale, moveX, moveY);//

        Door door112 = new Door(19,-5, "door112", scale, moveX, moveY);
        Door door103 = new Door(50, 10, "door103", scale, moveX, moveY);
        Door door104 = new Door(-10, 10, "door104", scale, moveX, moveY);

        doorStorage.add(door1);
        doorStorage.add(door2);
        doorStorage.add(door3);
        doorStorage.add(door4);
        doorStorage.add(door5);
        doorStorage.add(door6);
        doorStorage.add(door7);
        doorStorage.add(door8);
        doorStorage.add(door9);
        doorStorage.add(door10);
        doorStorage.add(door11);
        doorStorage.add(door12);
        doorStorage.add(door13);

        doorStorage.add(door112);
        doorStorage.add(door103);
        doorStorage.add(door104);

        //wariant drzwi 1,2
        roomAndDoors.put(room1,door1);
        roomAndDoors.put(room2,door3);
        roomAndDoors.put(room3,door5);
        roomAndDoors.put(room4,door2);
        roomAndDoors.put(room5,door7);
        roomAndDoors.put(room6,door8);
        roomAndDoors.put(room7,door4);
        roomAndDoors.put(room7,door6);
        roomAndDoors.put(room8,door9);
        roomAndDoors.put(room9,door10);
        roomAndDoors.put(room10,door11);
        roomAndDoors.put(room11,door12);
        roomAndDoors.put(room12,door13);

        roomAndDoors.put(room112,door112);
        roomAndDoors.put(room103,door103);
        roomAndDoors.put(room104,door104);


//        roomAndDoors.put(room100,door100);
//        roomAndDoors.put(room101,door101);

        Human man1 = new Human();
        Human man2 = new Human();
        Human man3 = new Human();




        man1.createMan(5+2, 6, 0.5f, 25, door1, world);
        man2.createMan(5+3, 6, 0.5f, 25, door1, world);
        man3.createMan(5+4, 6, 0.5f, 25, door1, world);


        man1.setRoom(room1000);
        man2.setRoom(room1000);
        man3.setRoom(room1000);


        peopleStorage.add(man1);
        peopleStorage.add(man2);
        peopleStorage.add(man3);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(.125f, .125f, .125f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        debugRenderer.render(world, camera.combined); // render all your graphics before you do your physics step, so it won't be out of sync
        world.step(1 / 60f, 6, 2);
    }

    @Override
    public void dispose () {
        world.dispose();
        debugRenderer.dispose();
    }
}
