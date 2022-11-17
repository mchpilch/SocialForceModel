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

        float scale = 1.5f;
        float moveX = -20f;
        float moveY = -10f;

        float moveRoomX =  moveX; //noBoxRoomsHere
        float moveRoomY =  moveY; //noBoxRoomsHere

        float doorWidth = 2.5f;
        float door_1_width = doorWidth;
        float door_2_width = doorWidth;
        float door_3_width = doorWidth;

        float halfDoor_1_Width = door_1_width/2;
        float halfDoor_2_Width = door_2_width/2;
        float halfDoor_3_Width = door_3_width/2;

        float doorMargin = 0f;
        float roomMargin = -0.1f;

        //horizontal (poziome)
        Wall wallAH_L = new Wall();
        Wall wallAH_R = new Wall();
        Wall wallBH_L = new Wall();
        Wall wallBH_R = new Wall();
        Wall wallCH = new Wall();

        wallAH_L.createWall(0f, 8f, 2.5f-halfDoor_1_Width, 8f, world, scale, moveX, moveY);
        wallAH_R.createWall(2.5f+halfDoor_1_Width, 8f, 5f, 8f, world, scale, moveX, moveY);
        wallBH_L.createWall(5f, 10f, 13f, 10f, world, scale, moveX, moveY);
        wallBH_R.createWall(14f, 10f, 15f, 10f, world, scale, moveX, moveY);
        wallCH.createWall(25f, 7f, 30f, 7f, world, scale, moveX, moveY);

        //vertical (pionowe)
        Wall wallAV = new Wall();
        Wall wallBV = new Wall();
        Wall wallCV = new Wall();
        Wall wallDV_T = new Wall();
        Wall wallDV_B = new Wall();
        Wall wallEV_T = new Wall();
        Wall wallEV_M = new Wall();
        Wall wallEV_B = new Wall();
        Wall wallFV = new Wall();
        Wall wallGV = new Wall();
        Wall wallHV = new Wall();
        Wall wallIV = new Wall();
        Wall wallJV = new Wall();
        Wall wallKV = new Wall();

        wallAV.createWall(0f, 8f, 0f, 18f, world, scale, moveX, moveY);
        wallBV.createWall(5f, 5f, 5f, 10f, world, scale, moveX, moveY);
        wallCV.createWall(15f, 3f, 15f, 10f, world, scale, moveX, moveY);
        wallDV_B.createWall(20f, 2f, 20f, 5f, world, scale, moveX, moveY);
        wallDV_T.createWall(20f, 6f, 20f, 7f, world, scale, moveX, moveY);
        wallEV_B.createWall(25f, 1f, 25f, 5f, world, scale, moveX, moveY);
        wallEV_M.createWall(25f, 6f, 25f, 8f, world, scale, moveX, moveY);
        wallEV_T.createWall(25f, 9f, 25f, 12f, world, scale, moveX, moveY);
        wallFV.createWall(30f, 0f, 30f, 24f, world, scale, moveX, moveY);
        wallGV.createWall(22f, 9f, 22f, 14f, world, scale, moveX, moveY);
        wallHV.createWall(17f, 12f, 17f, 13f, world, scale, moveX, moveY);
        wallIV.createWall(5f, 13f, 5f, 19f, world, scale, moveX, moveY);
        wallJV.createWall(15f, 15f, 15f, 21f, world, scale, moveX, moveY);
        wallKV.createWall(25f, 17f, 25f, 23f, world, scale, moveX, moveY);

        //WALLs other
        Wall wallCrooked1_1 = new Wall();
        Wall wallCrooked1_2 = new Wall();
        Wall wallCrooked2_1 = new Wall();
        Wall wallCrooked2_2 = new Wall();
        Wall wallCrooked2_3 = new Wall();
        Wall wallCrooked2_4 = new Wall();
        Wall wallCrooked3 = new Wall();
        Wall wallCrooked4 = new Wall();
        Wall wallCrooked5 = new Wall();
        Wall wallCrooked6 = new Wall();
        Wall wallCrooked7_1 = new Wall();
        Wall wallCrooked7_2 = new Wall();

        wallCrooked1_1.createWall(0f, 18f, 27.5f-halfDoor_3_Width, 23.5f-(0.2f*halfDoor_3_Width), world, scale, moveX, moveY);//createWall(0f, 18f, 27f, 23.4f, world, scale, moveX, moveY);
        wallCrooked1_2.createWall(27.5f+halfDoor_3_Width, 23.5f+(0.2f*halfDoor_3_Width), 30f, 24f, world, scale, moveX, moveY);//(28f, 23.6f, 30f, 24f, world, scale, moveX, moveY);
        wallCrooked2_1.createWall(0f, 12f, 2f, 12.4f, world, scale, moveX, moveY);
        wallCrooked2_2.createWall(3f, 12.6f, 13f, 14.6f, world, scale, moveX, moveY);
        wallCrooked2_3.createWall(14f, 14.8f, 23f, 16.6f, world, scale, moveX, moveY);
        wallCrooked2_4.createWall(24f, 16.8f, 25f, 17f, world, scale, moveX, moveY);
        wallCrooked3.createWall(17f, 13f, 22f, 14f, world, scale, moveX, moveY);
        wallCrooked4.createWall(17f, 12f, 22f, 9f, world, scale, moveX, moveY);
        wallCrooked5.createWall(25f, 12f, 30f, 13f, world, scale, moveX, moveY);
        wallCrooked6.createWall(15f, 10f, 20f, 7f, world, scale, moveX, moveY);
        wallCrooked7_1.createWall(5f, 5f, 22.5f-halfDoor_2_Width, 1.5f+(0.2f*halfDoor_2_Width), world, scale, moveX, moveY);
        wallCrooked7_2.createWall(22.5f+halfDoor_2_Width, 1.5f-(0.2f*halfDoor_2_Width), 30f, 0f, world, scale, moveX, moveY);

        //ROOM SECTION //roomMargin < 0

        Vector2 vecR1_1 = new Vector2( 5, 5).add(-roomMargin,-roomMargin);
        Vector2 vecR1_2 = new Vector2(15, 3).add(roomMargin,-roomMargin);
        Vector2 vecR1_3 = new Vector2(15,10).add(roomMargin,roomMargin);
        Vector2 vecR1_4 = new Vector2( 5,10).add(-roomMargin,roomMargin);

        Vector2[] polyVerR1 = {
                vecR1_1,
                vecR1_2,
                vecR1_3,
                vecR1_4
        };


        Vector2 vecR2_1 = new Vector2(15, 3).add(-roomMargin,-roomMargin);
        Vector2 vecR2_2 = new Vector2(20, 2).add(roomMargin,-roomMargin);
        Vector2 vecR2_3 = new Vector2(20, 7).add(roomMargin,roomMargin);
        Vector2 vecR2_4 = new Vector2(15,10).add(-roomMargin,roomMargin+roomMargin);//

        Vector2[] polyVerR2 = {
                vecR2_1,
                vecR2_2,
                vecR2_3,
                vecR2_4
        };


        Vector2 vecR3_1 = new Vector2(25,1).add(-roomMargin,-roomMargin);
        Vector2 vecR3_2 = new Vector2(30,0).add(roomMargin,-roomMargin);
        Vector2 vecR3_3 = new Vector2(30,7).add(roomMargin,roomMargin);
        Vector2 vecR3_4 = new Vector2(25,7).add(-roomMargin,roomMargin);

        Vector2[] polyVerR3 = {
                vecR3_1,
                vecR3_2,
                vecR3_3,
                vecR3_4
        };


        Vector2 vecR4_1 = new Vector2(25, 7).add(-roomMargin,-roomMargin);
        Vector2 vecR4_2 = new Vector2(30, 7).add(roomMargin,-roomMargin);
        Vector2 vecR4_3 = new Vector2(30,13).add(roomMargin,roomMargin);
        Vector2 vecR4_4 = new Vector2(25,12).add(-roomMargin,roomMargin);

        Vector2[] polyVerR4 = {
                vecR4_1,
                vecR4_2,
                vecR4_3,
                vecR4_4
        };


        Vector2 vecR5_1 = new Vector2(0,12).add(-roomMargin,-roomMargin);
        Vector2 vecR5_2 = new Vector2(5,13).add(roomMargin,-roomMargin);
        Vector2 vecR5_3 = new Vector2(5,19).add(roomMargin,roomMargin);
        Vector2 vecR5_4 = new Vector2(0,18).add(-roomMargin,roomMargin);

        Vector2[] polyVerR5 = {
                vecR5_1,
                vecR5_2,
                vecR5_3,
                vecR5_4
        };


        Vector2 vecR6_1 = new Vector2( 5,13).add(-roomMargin,-roomMargin);
        Vector2 vecR6_2 = new Vector2(15,15).add(roomMargin,-roomMargin);
        Vector2 vecR6_3 = new Vector2(15,21).add(roomMargin,roomMargin);
        Vector2 vecR6_4 = new Vector2( 5,19).add(-roomMargin,roomMargin);

        Vector2[] polyVerR6 = {
                vecR6_1,
                vecR6_2,
                vecR6_3,
                vecR6_4
        };


        Vector2 vecR7_1 = new Vector2(15,15).add(-roomMargin,-roomMargin);
        Vector2 vecR7_2 = new Vector2(25,17).add(roomMargin,-roomMargin);
        Vector2 vecR7_3 = new Vector2(25,23).add(roomMargin,roomMargin);
        Vector2 vecR7_4 = new Vector2(15,21).add(-roomMargin,roomMargin);

        Vector2[] polyVerR7 = {
                vecR7_1,
                vecR7_2,
                vecR7_3,
                vecR7_4
        };


        Vector2 vecR8_1_1 = new Vector2(0, 8).add(-roomMargin,-roomMargin);
        Vector2 vecR8_1_2 = new Vector2(5, 8).add(roomMargin,-roomMargin);
        Vector2 vecR8_1_3 = new Vector2(5,13).add(roomMargin,roomMargin);
        Vector2 vecR8_1_4 = new Vector2(0,12).add(-roomMargin,roomMargin);

        Vector2[] polyVerR8_1 = {
                vecR8_1_1,
                vecR8_1_2,
                vecR8_1_3,
                vecR8_1_4
        };


        Vector2 vecR8_2_1 = new Vector2( 5,10).add(-roomMargin,-roomMargin);
        Vector2 vecR8_2_2 = new Vector2(15,10).add(roomMargin,-roomMargin);
        Vector2 vecR8_2_3 = new Vector2(15,15).add(roomMargin,roomMargin);
        Vector2 vecR8_2_4 = new Vector2( 5,13).add(-roomMargin,roomMargin);

        Vector2[] polyVerR8_2 = {
                vecR8_2_1,
                vecR8_2_2,
                vecR8_2_3,
                vecR8_2_4
        };


        Vector2 vecR8_3_1 = new Vector2(15,10).add(-roomMargin,-roomMargin);
        Vector2 vecR8_3_2 = new Vector2(20, 7).add(-roomMargin,-roomMargin);
        Vector2 vecR8_3_3 = new Vector2(25, 7).add(roomMargin,-roomMargin);
        Vector2 vecR8_3_4 = new Vector2(25,17).add(roomMargin,roomMargin);
        Vector2 vecR8_3_5 = new Vector2(15,15).add(-roomMargin,roomMargin);

        Vector2[] polyVerR8_3 = {
                vecR8_3_1,
                vecR8_3_2,
                vecR8_3_3,
                vecR8_3_4,
                vecR8_3_5,
        };


        Vector2 vecR8_4_1 = new Vector2(25,12).add(-roomMargin,-roomMargin);
        Vector2 vecR8_4_2 = new Vector2(30,13).add(roomMargin,-roomMargin);
        Vector2 vecR8_4_3 = new Vector2(30,24).add(roomMargin,roomMargin);
        Vector2 vecR8_4_4 = new Vector2(25,23).add(-roomMargin,roomMargin);

        Vector2[] polyVerR8_4 = {
                vecR8_4_1,
                vecR8_4_2,
                vecR8_4_3,
                vecR8_4_4
        };


        Vector2 vecR8_5_1 = new Vector2(20,2).add(-roomMargin,-roomMargin);
        Vector2 vecR8_5_2 = new Vector2(25,1).add(roomMargin,-roomMargin);
        Vector2 vecR8_5_3 = new Vector2(25,7).add(roomMargin,roomMargin);
        Vector2 vecR8_5_4 = new Vector2(20,7).add(-roomMargin,roomMargin);

        Vector2[] polyVerR8_5 = {
                vecR8_5_1,
                vecR8_5_2,
                vecR8_5_3,
                vecR8_5_4
        };

        Vector2 vecR102_1 = new Vector2(20,2-6);
        Vector2 vecR102_2 = new Vector2(25,1-6);
        Vector2 vecR102_3 = new Vector2(25,7-6);
        Vector2 vecR102_4 = new Vector2(20,8-6);

        Vector2[] polyVerR102 = {
                vecR102_1,
                vecR102_2,
                vecR102_3,
                vecR102_4
        };

        Room room1 = new Room(0,0, polyVerR1, world,"Room-1-X", scale, moveX, moveY);
        roomStorage.add(room1);

        Room room2 = new Room(0,0, polyVerR2, world,"Room-2-X", scale, moveX, moveY);
        roomStorage.add(room2);

        Room room3 = new Room(0,0, polyVerR3, world,"Room-3-X", scale, moveX, moveY);
        roomStorage.add(room3);

        Room room4 = new Room(0,0, polyVerR4, world,"Room-4-X", scale, moveX, moveY);
        roomStorage.add(room4);

        Room room5 = new Room(0,0, polyVerR5, world,"Room-5-X", scale, moveX, moveY);
        roomStorage.add(room5);

        Room room6 = new Room(0,0, polyVerR6, world,"Room-6-X", scale, moveX, moveY);
        roomStorage.add(room6);

        Room room7 = new Room(0,0, polyVerR7, world,"Room-7-X", scale, moveX, moveY);
        roomStorage.add(room7);

        Room room8_1 = new Room(0,0, polyVerR8_1, world,"Room-8-a", scale, moveX, moveY);
        roomStorage.add(room8_1);

        Room room8_2 = new Room(0,0, polyVerR8_2, world,"Room-8-b", scale, moveX, moveY);
        roomStorage.add(room8_2);

        Room room8_3 = new Room(0,0, polyVerR8_3, world,"Room-8-c", scale, moveX, moveY);
        roomStorage.add(room8_3);

        Room room8_4 = new Room(0,0, polyVerR8_4, world,"Room-8-d", scale, moveX, moveY);
        roomStorage.add(room8_4);

        Room room8_5 = new Room(0,0, polyVerR8_5, world,"Room-8-e", scale, moveX, moveY);
        roomStorage.add(room8_5);

        Room room101 = new Room(5f/2f,8f-10f/2f, 5.6f/2f-0.3f,10/2f+roomMargin, world, "Room-101-RightExit", scale, moveRoomX, moveRoomY);
        Room room102 = new Room(0f,-0.3f, polyVerR102, world, "Room-101-RightExit", scale, moveRoomX, moveRoomY);
        Room room103 = new Room(-14f,4.5f, polyVerR8_4, world, "Room-100-LeftExit", scale, moveRoomX, moveRoomY);

        Room room1000 = new Room(1000,1000,1,1,world,"Room-1000-FirstRoom",scale,moveRoomX,moveRoomY);

        roomStorage.add(room101);
        roomStorage.add(room102);
        roomStorage.add(room103);
        roomStorage.add(room1000);

        Door door1 = new Door(2.5f, 8 + doorMargin, "door1", scale, moveX, moveY);//-doorMargin
        Door door2 = new Door(22.5f, 1.5f + doorMargin, "door2", scale, moveX, moveY);//+doorMargin
        Door door3 = new Door(8, 20 - doorMargin, "door3", scale, moveX, moveY);
        Door door4 = new Door(2.5f, 12.5f + doorMargin, "door4", scale, moveX, moveY);
        Door door5 = new Door(13.5f, 14f + doorMargin, "door5", scale, moveX, moveY);
        Door door6 = new Door(23.5f, 16f + doorMargin, "door6", scale, moveX, moveY);
        Door door7 = new Door(25f + doorMargin, 8.5f, "door7", scale, moveX, moveY);
        Door door8 = new Door(25f + doorMargin, 5.5f, "door8", scale, moveX, moveY);
        Door door9 = new Door(20f - doorMargin, 5.5f, "door9", scale, moveX, moveY);
        Door door10 = new Door(13.5f, 10f - doorMargin, "door10", scale, moveX, moveY);

        Door door11 = new Door(5f + doorMargin,  11f , "door11", scale, moveX, moveY);
        Door door12 = new Door(15f + doorMargin, 12f , "door12", scale, moveX, moveY);
        Door door13 = new Door(25f - doorMargin, 15f , "door13", scale, moveX, moveY);
        Door door14 = new Door(22.5f, 8f  + doorMargin, "door14", scale, moveX, moveY);

        Door door101 = new Door(  0f , 2.5f ,"door100", scale, moveX, moveY);
        Door door102 = new Door(  22.5f , -3f ,"door101", scale, moveX, moveY);
        Door door103 = new Door(  27.5f , 26f ,"door101", scale, moveX, moveY);

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

        doorStorage.add(door101);
        doorStorage.add(door102);
        doorStorage.add(door103);

        roomAndDoors.put(room1,door10);
        roomAndDoors.put(room2,door9);
        roomAndDoors.put(room3,door8);
        roomAndDoors.put(room4,door7);
        roomAndDoors.put(room5,door4);
        roomAndDoors.put(room6,door5);
        roomAndDoors.put(room7,door6);
        roomAndDoors.put(room8_1,door1);
        roomAndDoors.put(room8_2,door11);
        roomAndDoors.put(room8_3,door12);
        roomAndDoors.put(room8_3,door13);
        roomAndDoors.put(room8_3,door14);
        roomAndDoors.put(room8_4,door3);
        roomAndDoors.put(room8_5,door2);

        roomAndDoors.put(room101,door101);
        roomAndDoors.put(room102,door102);
        roomAndDoors.put(room103,door103);

        roomAndDoors.put(room1000,door1);

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
