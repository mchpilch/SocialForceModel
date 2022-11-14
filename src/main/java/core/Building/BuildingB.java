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

public class BuildingB extends Game {
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

        //Building_B

        float scale = 1.0f;
        float moveX = -20f;
        float moveY = -10f;

        float moveRoomX =  moveX+20;
        float moveRoomY =  moveY+10;

        float doorWidth = 2f;
        float halfDoorWidth = doorWidth/2;

        float doorMargin = 1f;
        float roomMargin = -0.8f;

        //WALLs parallel
        //Wall wallAR = new Wall();
        Wall wallAR_L = new Wall();
        Wall wallAR_R = new Wall();
        //Wall wallBR = new Wall();
        Wall wallBR_L = new Wall();
        Wall wallBR_R = new Wall();
        Wall wallCR = new Wall();

        //wallAR.createWall(0f, 8f, 5f, 8f, world, scale, moveX, moveY);
        wallAR_L.createWall(0f, 8f, 2f, 8f, world, scale, moveX, moveY);
        wallAR_R.createWall(3f, 8f, 5f, 8f, world, scale, moveX, moveY);
        //wallBR.createWall(5f, 10f, 15f, 10f, world, scale, moveX, moveY);
        wallBR_L.createWall(5f, 10f, 13f, 10f, world, scale, moveX, moveY);
        wallBR_R.createWall(14f, 10f, 15f, 10f, world, scale, moveX, moveY);
        wallCR.createWall(25f, 7f, 30f, 7f, world, scale, moveX, moveY);

        //WALLs perpendicular
        Wall wallAP = new Wall();
        Wall wallBP = new Wall();
        Wall wallCP = new Wall();
        //Wall wallDP = new Wall();
        Wall wallDP_T = new Wall();
        Wall wallDP_B = new Wall();
        //Wall wallEP = new Wall();
        Wall wallEP_T = new Wall();
        Wall wallEP_M = new Wall();
        Wall wallEP_B = new Wall();
        Wall wallFP = new Wall();
        Wall wallGP = new Wall();
        Wall wallHP = new Wall();
        Wall wallIP = new Wall();
        Wall wallJP = new Wall();
        Wall wallKP = new Wall();

        wallAP.createWall(0f, 8f, 0f, 18f, world, scale, moveX, moveY);
        wallBP.createWall(5f, 5f, 5f, 10f, world, scale, moveX, moveY);
        wallCP.createWall(15f, 3f, 15f, 10f, world, scale, moveX, moveY);
        //wallDP.createWall(20f, 2f, 20f, 7f, world, scale, moveX, moveY);
        wallDP_B.createWall(20f, 2f, 20f, 5f, world, scale, moveX, moveY);
        wallDP_T.createWall(20f, 6f, 20f, 7f, world, scale, moveX, moveY);
        //wallEP.createWall(25f, 1f, 25f, 12f, world, scale, moveX, moveY);
        wallEP_B.createWall(25f, 1f, 25f, 5f, world, scale, moveX, moveY);
        wallEP_M.createWall(25f, 6f, 25f, 8f, world, scale, moveX, moveY);
        wallEP_T.createWall(25f, 9f, 25f, 12f, world, scale, moveX, moveY);
        wallFP.createWall(30f, 0f, 30f, 24f, world, scale, moveX, moveY);
        wallGP.createWall(22f, 9f, 22f, 14f, world, scale, moveX, moveY);
        wallHP.createWall(17f, 12f, 17f, 13f, world, scale, moveX, moveY);
        wallIP.createWall(5f, 13f, 5f, 19f, world, scale, moveX, moveY);
        wallJP.createWall(15f, 15f, 15f, 21f, world, scale, moveX, moveY);
        wallKP.createWall(25f, 17f, 25f, 23f, world, scale, moveX, moveY);

        //WALLs other
        //Wall wallCrooked1 = new Wall();
        Wall wallCrooked1_1 = new Wall();
        Wall wallCrooked1_2 = new Wall();
        //Wall wallCrooked2 = new Wall();
        Wall wallCrooked2_1 = new Wall();
        Wall wallCrooked2_2 = new Wall();
        Wall wallCrooked2_3 = new Wall();
        Wall wallCrooked2_4 = new Wall();
        Wall wallCrooked3 = new Wall();
        Wall wallCrooked4 = new Wall();
        Wall wallCrooked5 = new Wall();
        Wall wallCrooked6 = new Wall();
        Wall wallCrooked7 = new Wall();

        //wallCrooked1.createWall(0f, 18f, 30f, 24f, world, scale, moveX, moveY);
        wallCrooked1_1.createWall(0f, 18f, 27f, 23.4f, world, scale, moveX, moveY);
        wallCrooked1_2.createWall(28f, 23.6f, 30f, 24f, world, scale, moveX, moveY);
        //wallCrooked2.createWall(0f, 12f, 25f, 17f, world, scale, moveX, moveY);
        wallCrooked2_1.createWall(0f, 12f, 2f, 12.4f, world, scale, moveX, moveY);
        wallCrooked2_2.createWall(3f, 12.6f, 13f, 14.6f, world, scale, moveX, moveY);
        wallCrooked2_3.createWall(14f, 14.8f, 23f, 16.6f, world, scale, moveX, moveY);
        wallCrooked2_4.createWall(24f, 16.8f, 25f, 17f, world, scale, moveX, moveY);
        wallCrooked3.createWall(17f, 13f, 22f, 14f, world, scale, moveX, moveY);
        wallCrooked4.createWall(17f, 12f, 22f, 9f, world, scale, moveX, moveY);
        wallCrooked5.createWall(25f, 12f, 30f, 13f, world, scale, moveX, moveY);
        wallCrooked6.createWall(15f, 10f, 20f, 7f, world, scale, moveX, moveY);
        wallCrooked7.createWall(5f, 5f, 30f, 0f, world, scale, moveX, moveY);

        //ROOM SECTION //roomMargin < 0
        Vector2 vecR1_1 = new Vector2(5,5);
        Vector2 vecR1_2 = new Vector2(15,3);
        Vector2 vecR1_3 = new Vector2(15,10);
        Vector2 vecR1_4 = new Vector2(5,10);

        Vector2[] polyVerR1 = {
                vecR1_1,
                vecR1_2,
                vecR1_3,
                vecR1_4
        };

        Room room1 = new Room(0,0, polyVerR1, world,"Room-1-X", scale, moveX, moveY);
        roomStorage.add(room1);

        Vector2 vecR2_1 = new Vector2(20,7);
        Vector2 vecR2_2 = new Vector2(15,10);
        Vector2 vecR2_3 = new Vector2(15,3);
        Vector2 vecR2_4 = new Vector2(20,2);

        Vector2[] polyVerR2 = {
                vecR2_1,
                vecR2_2,
                vecR2_3,
                vecR2_4
        };

        Room room2 = new Room(0,0, polyVerR2, world,"Room-2-X", scale, moveX, moveY);
        roomStorage.add(room2);

        Vector2 vecR3_1 = new Vector2(25,1);
        Vector2 vecR3_2 = new Vector2(30,0);
        Vector2 vecR3_3 = new Vector2(30,7);
        Vector2 vecR3_4 = new Vector2(25,7);

        Vector2[] polyVerR3 = {
                vecR3_1,
                vecR3_2,
                vecR3_3,
                vecR3_4
        };

        Room room3 = new Room(0,0, polyVerR3, world,"Room-3-X", scale, moveX, moveY);
        roomStorage.add(room3);

        Vector2 vecR4_1 = new Vector2(25,7);
        Vector2 vecR4_2 = new Vector2(30,7);
        Vector2 vecR4_3 = new Vector2(30,13);
        Vector2 vecR4_4 = new Vector2(25,12);

        Vector2[] polyVerR4 = {
                vecR4_1,
                vecR4_2,
                vecR4_3,
                vecR4_4
        };

        Room room4 = new Room(0,0, polyVerR4, world,"Room-4-X", scale, moveX, moveY);
        roomStorage.add(room4);

        Vector2 vecR5_1 = new Vector2(0,12);
        Vector2 vecR5_2 = new Vector2(5,13);
        Vector2 vecR5_3 = new Vector2(5,19);
        Vector2 vecR5_4 = new Vector2(0,18);

        Vector2[] polyVerR5 = {
                vecR5_1,
                vecR5_2,
                vecR5_3,
                vecR5_4
        };

        Room room5 = new Room(0,0, polyVerR5, world,"Room-5-X", scale, moveX, moveY);
        roomStorage.add(room5);

        Vector2 vecR6_1 = new Vector2(5,19);
        Vector2 vecR6_2 = new Vector2(5,13);
        Vector2 vecR6_3 = new Vector2(15,15);
        Vector2 vecR6_4 = new Vector2(15,21);

        Vector2[] polyVerR6 = {
                vecR6_1,
                vecR6_2,
                vecR6_3,
                vecR6_4
        };

        Room room6 = new Room(0,0, polyVerR6, world,"Room-6-X", scale, moveX, moveY);
        roomStorage.add(room6);

        Vector2 vecR7_1 = new Vector2(25,23);
        Vector2 vecR7_2 = new Vector2(15,21);
        Vector2 vecR7_3 = new Vector2(15,15);
        Vector2 vecR7_4 = new Vector2(25,17);

        Vector2[] polyVerR7 = {
                vecR7_1,
                vecR7_2,
                vecR7_3,
                vecR7_4
        };

        Room room7 = new Room(0,0, polyVerR7, world,"Room-7-X", scale, moveX, moveY);
        roomStorage.add(room7);


        Vector2 vecR8_1_1 = new Vector2(5,13);
        Vector2 vecR8_1_2 = new Vector2(0,12);
        Vector2 vecR8_1_3 = new Vector2(0,8);
        Vector2 vecR8_1_4 = new Vector2(5,8);

        Vector2[] polyVerR8_1 = {
                vecR8_1_1,
                vecR8_1_2,
                vecR8_1_3,
                vecR8_1_4
        };

        Room room8_1 = new Room(0,0, polyVerR8_1, world,"Room-8-1", scale, moveX, moveY);
        roomStorage.add(room8_1);

        Vector2 vecR8_2_1 = new Vector2(5,13);
        Vector2 vecR8_2_2 = new Vector2(5,10);
        Vector2 vecR8_2_3 = new Vector2(15,10);
        Vector2 vecR8_2_4 = new Vector2(15,15);

        Vector2[] polyVerR8_2 = {
                vecR8_2_1,
                vecR8_2_2,
                vecR8_2_3,
                vecR8_2_4
        };

        Room room8_2 = new Room(0,0, polyVerR8_2, world,"Room-8-2", scale, moveX, moveY);
        roomStorage.add(room8_2);

        Vector2 vecR8_3_1 = new Vector2(15,10);
        Vector2 vecR8_3_2 = new Vector2(20,7);
        Vector2 vecR8_3_3 = new Vector2(25,7);
        Vector2 vecR8_3_4 = new Vector2(25,17);
        Vector2 vecR8_3_5 = new Vector2(15,15);

        Vector2[] polyVerR8_3 = {
                vecR8_3_1,
                vecR8_3_2,
                vecR8_3_3,
                vecR8_3_4,
                vecR8_3_5,
        };

        Room room8_3 = new Room(0,0, polyVerR8_3, world,"Room-8-3", scale, moveX, moveY);
        roomStorage.add(room8_3);

        Vector2 vecR8_4_1 = new Vector2(25,12);
        Vector2 vecR8_4_2 = new Vector2(30,13);
        Vector2 vecR8_4_3 = new Vector2(30,24);
        Vector2 vecR8_4_4 = new Vector2(25,23);

        Vector2[] polyVerR8_4 = {
                vecR8_4_1,
                vecR8_4_2,
                vecR8_4_3,
                vecR8_4_4
        };

        Room room8_4 = new Room(0,0, polyVerR8_4, world,"Room-8-4", scale, moveX, moveY);
        roomStorage.add(room8_4);

        Vector2 vecR8_5_1 = new Vector2(20,7);
        Vector2 vecR8_5_2 = new Vector2(25,7);
        Vector2 vecR8_5_3 = new Vector2(25,1);
        Vector2 vecR8_5_4 = new Vector2(20,2);

        Vector2[] polyVerR8_5 = {
                vecR8_5_1,
                vecR8_5_2,
                vecR8_5_3,
                vecR8_5_4
        };

        Room room8_5 = new Room(0,0, polyVerR8_5, world,"Room-8-5", scale, moveX, moveY);
        roomStorage.add(room8_5);
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
