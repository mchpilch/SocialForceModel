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

public class BuildingC extends Game {
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

        //Building_C

        float scale = 1.0f;

        float moveX = -16f;
        float moveY = -16f;

        float moveRoomX = moveX+16;
        float moveRoomY = moveY+16;

        float doorWidth = 1.5f;
        float door_1_width = doorWidth;
        float door_2_width = doorWidth;
        float door_3_width = doorWidth;
        float door_4_width = doorWidth;
        float door_5_width = doorWidth;
        float door_6_width = doorWidth;

        float halfDoor_1_Width = door_1_width/2;
        float halfDoor_2_Width = door_2_width/2;
        float halfDoor_3_Width = door_3_width/2;
        float halfDoor_4_Width = door_4_width/2;
        float halfDoor_5_Width = door_5_width/2;
        float halfDoor_6_Width = door_6_width/2;

        float doorMargin = 1f;
        float roomMargin = -0.8f;

        Wall wallAH = new Wall();
        Wall wallBH_L = new Wall();
        Wall wallBH_P = new Wall();
        Wall wallCH_L = new Wall();
        Wall wallCH_P = new Wall();
        Wall wallDH = new Wall();

        wallStorage.add(wallAH);
        wallStorage.add(wallBH_L);
        wallStorage.add(wallBH_P);
        wallStorage.add(wallCH_L);
        wallStorage.add(wallCH_P);
        wallStorage.add(wallDH);

        wallAH.createWall(0f, 0f, 32f, 0f, world, scale, moveX, moveY);
        wallBH_L.createWall(0f, 12f, 24f-halfDoor_4_Width, 12f, world, scale, moveX, moveY);
        wallBH_P.createWall(24f+halfDoor_4_Width, 12f, 32f, 12f, world, scale, moveX, moveY);
        wallCH_L.createWall(0f, 20f, 8f-halfDoor_3_Width, 20f, world, scale, moveX, moveY);
        wallCH_P.createWall(8f+halfDoor_3_Width, 20f, 32f, 20f, world, scale, moveX, moveY);
        wallDH.createWall(0f, 32f, 32f, 32f, world, scale, moveX, moveY);

        Wall wallAV_T = new Wall();
        Wall wallAV_B = new Wall();
        Wall wallBV_T = new Wall();
        Wall wallBV_B = new Wall();
        Wall wallCV_T = new Wall();
        Wall wallCV_B = new Wall();
        Wall wallDV_T = new Wall();
        Wall wallDV_B = new Wall();

        wallAV_T.createWall(0f, 16f+halfDoor_1_Width, 0f, 32f, world, scale, moveX, moveY);
        wallAV_B.createWall(0f, 0f, 0f, 16f-halfDoor_1_Width, world, scale, moveX, moveY);
        wallBV_T.createWall(32f, 16f+halfDoor_2_Width, 32f, 32f, world, scale, moveX, moveY);
        wallBV_B.createWall(32f, 0f, 32f, 16f-halfDoor_2_Width, world, scale, moveX, moveY);
        wallCV_T.createWall(16f, 26f+halfDoor_5_Width, 16f, 32f, world, scale, moveX, moveY);
        wallCV_B.createWall(16f, 20f, 16f, 26f-halfDoor_5_Width, world, scale, moveX, moveY);
        wallDV_T.createWall(16f, 6f+halfDoor_6_Width, 16f, 12f, world, scale, moveX, moveY);
        wallDV_B.createWall(16f, 0f, 16f, 6f-halfDoor_6_Width, world, scale, moveX, moveY);

        wallStorage.add(wallAV_T);
        wallStorage.add(wallAV_B);
        wallStorage.add(wallBV_T);
        wallStorage.add(wallBV_B);
        wallStorage.add(wallCV_T);
        wallStorage.add(wallCV_B);
        wallStorage.add(wallDV_T);
        wallStorage.add(wallDV_B);

        Room room1 = new Room(8f,10f, 16/2+roomMargin,12/2f+roomMargin, world, "Room-1-TopRight", scale, moveRoomX, moveRoomY);
        Room room2 = new Room(-8f,10f, 16/2f+roomMargin,12/2f+roomMargin, world, "Room-2-TopLeft", scale, moveRoomX, moveRoomY);
        Room room3 = new Room(-8f,-10f, 16/2f+roomMargin,12/2f+roomMargin, world, "Room-3-BottomLeft", scale, moveRoomX, moveRoomY);
        Room room4 = new Room(8f,-10f, 16/2f+roomMargin,12/2f+roomMargin, world, "Room-4-BottomRight", scale, moveRoomX, moveRoomY);
        Room room5 = new Room(0f,0f, 32/2f+roomMargin,8/2f+roomMargin, world,"Room-5-MainHall", scale, moveRoomX, moveRoomY);//środek ciała jest ustawiany dlatego do pos dodaje polowe wartosci width i height

        Room room100 = new Room(-28f,0f, 24/2+roomMargin,24/2f+roomMargin, world, "Room-100-LeftExit", scale, moveRoomX, moveRoomY);
        Room room101 = new Room(28f,0f, 24/2+roomMargin,24/2f+roomMargin, world, "Room-101-RightExit", scale, moveRoomX, moveRoomY);


        Room room1000 = new Room(1000,1000,1,1,world,"Room-1000-FirstRoom",scale,moveRoomX,moveRoomY);

        roomStorage.add(room1);
        roomStorage.add(room2);
        roomStorage.add(room3);
        roomStorage.add(room4);
        roomStorage.add(room5);

        roomStorage.add(room100);
        roomStorage.add(room101);



        Door door1 = new Door(0-2,16, "door1", scale, moveX, moveY);//-doorMargin
        Door door2 = new Door(32+2,16, "door2", scale, moveX, moveY);//+doorMargin
        Door door3 = new Door(8,20-doorMargin, "door3", scale, moveX, moveY);
        Door door4 = new Door(24,12+doorMargin, "door4", scale, moveX, moveY);
        Door door5 = new Door(16-doorMargin,26, "door5", scale, moveX, moveY);
        Door door6 = new Door(16+doorMargin,6, "door6", scale, moveX, moveY);

        Door door100 = new Door(-20,16, "door100", scale, moveX, moveY);
        Door door101 = new Door(32+20, 16, "door101", scale, moveX, moveY);


        doorStorage.add(door1);
        doorStorage.add(door2);
        doorStorage.add(door3);
        doorStorage.add(door4);
        doorStorage.add(door5);
        doorStorage.add(door6);

        doorStorage.add(door100);
        doorStorage.add(door101);


        roomAndDoors.put(room5,door1);
        roomAndDoors.put(room5,door2);
        roomAndDoors.put(room2,door3);
        roomAndDoors.put(room4,door4);
        roomAndDoors.put(room1,door5);
        roomAndDoors.put(room3,door6);

        roomAndDoors.put(room100,door100);
        roomAndDoors.put(room101,door101);
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
