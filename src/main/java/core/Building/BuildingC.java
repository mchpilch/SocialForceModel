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

        float doorWidth = 2f;
        float halfDoorWidth = doorWidth/2;

        float doorMargin = 1f;
        float roomMargin = -0.8f;

        Wall wallAR = new Wall();
        Wall wallBR_L = new Wall();
        Wall wallBR_P = new Wall();
        Wall wallCR_L = new Wall();
        Wall wallCR_P = new Wall();
        Wall wallDR = new Wall();

        wallStorage.add(wallAR);
        wallStorage.add(wallBR_L);
        wallStorage.add(wallBR_P);
        wallStorage.add(wallCR_L);
        wallStorage.add(wallCR_P);
        wallStorage.add(wallDR);

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

        wallStorage.add(wallAP_T);
        wallStorage.add(wallAP_B);
        wallStorage.add(wallBP_T);
        wallStorage.add(wallBP_B);
        wallStorage.add(wallCP_T);
        wallStorage.add(wallCP_B);
        wallStorage.add(wallDP_T);
        wallStorage.add(wallDP_B);

        Room room1 = new Room(8f,10f, 16/2+roomMargin,12/2f+roomMargin, world, "Room-1-TopRight", scale);
        Room room2 = new Room(-8f,10f, 16/2f+roomMargin,12/2f+roomMargin, world, "Room-2-TopLeft", scale);
        Room room3 = new Room(-8f,-10f, 16/2f+roomMargin,12/2f+roomMargin, world, "Room-3-BottomLeft", scale);
        Room room4 = new Room(8f,-10f, 16/2f+roomMargin,12/2f+roomMargin, world, "Room-4-BottomRight", scale);
        Room room5 = new Room(0f,0f, 32/2f+roomMargin,8/2f+roomMargin, world,"Room-5-MainHall", scale);//środek ciała jest ustawiany dlatego do pos dodaje polowe wartosci width i height


        roomStorage.add(room1);
        roomStorage.add(room2);
        roomStorage.add(room3);
        roomStorage.add(room4);
        roomStorage.add(room5);


        Door door1 = new Door(0-doorMargin,16, "door1", scale, moveX, moveY);
        Door door2 = new Door(32+doorMargin,16, "door2", scale, moveX, moveY);
        Door door3 = new Door(8,20-doorMargin, "door3", scale, moveX, moveY);
        Door door4 = new Door(24,12+doorMargin, "door4", scale, moveX, moveY);
        Door door5 = new Door(16-doorMargin,26, "door5", scale, moveX, moveY);
        Door door6 = new Door(16+doorMargin,6, "door6", scale, moveX, moveY);

        doorStorage.add(door1);
        doorStorage.add(door2);
        doorStorage.add(door3);
        doorStorage.add(door4);
        doorStorage.add(door5);
        doorStorage.add(door6);

        roomAndDoors.put(room5,door1);
        roomAndDoors.put(room5,door2);
        roomAndDoors.put(room2,door3);
        roomAndDoors.put(room4,door4);
        roomAndDoors.put(room1,door5);
        roomAndDoors.put(room3,door6);
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
