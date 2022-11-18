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

public class BuildingD extends Game {
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

        //Building D
        float scale = 1.0f;

        float moveX = -12f;
        float moveY = -15f;

        float moveRoomX = moveX+12;
        float moveRoomY = moveY+7.5f;

        int nrOfDoors = 3; //1,2,3

        float doorWidth = 1.5f;
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
