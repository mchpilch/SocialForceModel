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

        //ROOM SECTION
        Vector2 vec1 = new Vector2(0,0);
        Vector2 vec2 = new Vector2(11,0);
        Vector2 vec3 = new Vector2(11,5);
        Vector2 vec4 = new Vector2(3,5);
        Vector2 vec5 = new Vector2(0,2);


        Vector2[] polyVer = {
                vec1,
                vec2,
                vec3,
                vec4,
                vec5
        };
        Room roomP = new Room(16,12,polyVer, world,"polyRoom", scale, moveX, moveY);


//        Vector2 vecBL = new Vector2(0,0);
//        Vector2 vecBR = new Vector2(3,0);
//        Vector2 vecTR = new Vector2(5,5);
//        Vector2 vecTL = new Vector2(0,5);
//
//        Vector2[] polyVer = {
//                vecBL,
//                vecBR,
//                vecTR,
//                vecTL
//        };
//        Room roomP = new Room(0,0,polyVer, world,"polyRoom", 1);


        roomStorage.add(roomP);
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
