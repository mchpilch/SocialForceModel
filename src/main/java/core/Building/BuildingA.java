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

        float doorWidth = 2f;
        float halfDoorWidth = doorWidth/2;

        float doorMargin = 1f;
        float roomMargin = -0.8f;

        //WALL SECTION

        //parallel (równoległe)
        Wall wallAR1 = new Wall();
        Wall wallAR2 = new Wall();
        Wall wallAR3 = new Wall();
        Wall wallBR1 = new Wall();
        Wall wallBR2 = new Wall();
        Wall wallCR1 = new Wall();
        Wall wallCR2 = new Wall();
        Wall wallCR3 = new Wall();
        Wall wallCR4 = new Wall();
        Wall wallCR5 = new Wall();
        Wall wallDR1 = new Wall();
        Wall wallDR2 = new Wall();
        Wall wallDR3 = new Wall();
        Wall wallER = new Wall();
        Wall wallFR = new Wall();


        wallAR1.createWall(0f, 0f, 9f, 0f, world, scale, moveX, moveY);
        wallAR2.createWall(11f, 0f, 18f, 0f, world, scale, moveX, moveY);
        wallAR3.createWall(20f, 0f, 40f, 0f, world, scale, moveX, moveY);
        wallBR1.createWall(0f, 4f, 9f, 4f, world, scale, moveX, moveY);
        wallBR2.createWall(11f, 4f, 12f, 4f, world, scale, moveX, moveY);
        wallCR1.createWall(0f, 7f, 9f, 7f, world, scale, moveX, moveY);
        wallCR2.createWall(11f, 7f, 18f, 7f, world, scale, moveX, moveY);
        wallCR3.createWall(20f, 7f, 21f, 7f, world, scale, moveX, moveY);
        wallCR4.createWall(27f, 7f, 28f, 7f, world, scale, moveX, moveY);
        wallCR5.createWall(30f, 7f, 40f, 7f, world, scale, moveX, moveY);
        wallDR1.createWall(0f, 12f, 3f, 12f, world, scale, moveX, moveY);
        wallDR2.createWall(5f, 12f, 22f, 12f, world, scale, moveX, moveY);
        wallDR3.createWall(24f, 12f, 40f, 12f, world, scale, moveX, moveY);
        wallER.createWall(19f, 17f, 27f, 17f, world, scale, moveX, moveY);
        wallFR.createWall(0f, 20f, 40f, 20f, world, scale, moveX, moveY);

        //prostopadłe
        Wall wallAP = new Wall();
        Wall wallBP = new Wall();
        Wall wallCP = new Wall();
        Wall wallDP = new Wall();
        Wall wallEP = new Wall();
        Wall wallFP = new Wall();
        Wall wallGP_T = new Wall();
        Wall wallGP_B = new Wall();
        Wall wallHP = new Wall();


        wallAP.createWall(0f, 0f, 0f, 20f, world, scale, moveX, moveY);
        wallBP.createWall(12f, 0f, 12f, 5f, world, scale, moveX, moveY);
        wallCP.createWall(16f, 12f, 16f, 20f, world, scale, moveX, moveY);
        wallDP.createWall(17f, 0f, 17f, 7f, world, scale, moveX, moveY);
        wallEP.createWall(21f, 0f, 21f, 7f, world, scale, moveX, moveY);
        wallFP.createWall(27f, 0f, 27f, 7f, world, scale, moveX, moveY);
        wallGP_T.createWall(27f, 15f, 27f, 18f, world, scale, moveX, moveY);
        wallGP_B.createWall(27f, 12f, 27f, 13f, world, scale, moveX, moveY);
        wallHP.createWall(40f, 0f, 40f, 20f, world, scale, moveX, moveY);

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

        Room room9 = new Room(16,12, polyVerR9, world,"Room-9-X", scale, moveX, moveY);
        roomStorage.add(room9);

        Vector2 vecR11_1 = new Vector2(0,0).add(-roomMargin-0.3f,-roomMargin+0.3f);
        Vector2 vecR11_2= new Vector2(0,6).add(-roomMargin-0.3f,roomMargin+0.3f);
        Vector2 vecR11_3 = new Vector2(6,6).add(roomMargin-0.3f,roomMargin+0.3f);

        Vector2[] polyVerR11 = {
                 vecR11_1,
                 vecR11_2,
                 vecR11_3,
        };

        Room room11 = new Room(16,14, polyVerR11, world,"Room-11-X", scale, moveX, moveY);
        roomStorage.add(room11);

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

        Room room12 = new Room(19,17, polyVerR12, world,"Room-12-X", scale, moveX, moveY);
        roomStorage.add(room12);

        //pozycja lewy dolny róg, + połowa hx lub hy
        Room room1 = new Room( 0f + 12 / 2f, 0f + 4 / 2f, 12 / 2f + roomMargin,4 / 2f  + roomMargin, world,  "Room-1-X",  scale, moveRoomX, moveRoomY);
        Room room2 = new Room( 0f + 12 / 2f, 4f + 3 / 2f, 12 / 2f + roomMargin,3 / 2f  + roomMargin, world,  "Room-2-X",  scale, moveRoomX, moveRoomY);
        Room room3 = new Room(12f + 5  / 2f, 0f + 7 / 2f, 5  / 2f + roomMargin,7 / 2f  + roomMargin, world,  "Room-3-X",  scale, moveRoomX, moveRoomY);
        Room room4 = new Room(17f + 4  / 2f, 0f + 7 / 2f, 4  / 2f + roomMargin,7 / 2f  + roomMargin, world,  "Room-4-X",  scale, moveRoomX, moveRoomY);
        Room room5 = new Room(21f + 6  / 2f, 0f + 7 / 2f, 6  / 2f + roomMargin,7 / 2f  + roomMargin, world,  "Room-5-X",  scale, moveRoomX, moveRoomY);
        Room room6 = new Room(27f + 13 / 2f, 0f + 7 / 2f, 13 / 2f + roomMargin,7 / 2f  + roomMargin, world,  "Room-6-X",  scale, moveRoomX, moveRoomY);
        Room room7 = new Room( 0f + 40 / 2f, 7f + 5 / 2f, 40 / 2f + roomMargin,5 / 2f  + roomMargin, world,  "Room-7-X",  scale, moveRoomX, moveRoomY);
        Room room8 = new Room( 0f + 16 / 2f,12f + 8 / 2f, 16 / 2f + roomMargin,8 / 2f  + roomMargin, world,  "Room-8-X",  scale, moveRoomX, moveRoomY);
        //already def
        Room room10 = new Room(27f + 13 / 2f,12f + 8  /  2f,13 / 2f + roomMargin,8  /  2f + roomMargin, world,"Room-10-X", scale, moveRoomX, moveRoomY);
        //already def
        //already def


        roomStorage.add(room1);
        roomStorage.add(room2);
        roomStorage.add(room3);
        roomStorage.add(room4);
        roomStorage.add(room5);
        roomStorage.add(room6);
        roomStorage.add(room7);
        roomStorage.add(room8);
        //already def
        roomStorage.add(room10);
        //already def
        //already def


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
