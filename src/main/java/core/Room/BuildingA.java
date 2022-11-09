package core.Room;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import core.Element.Wall;

public class BuildingA extends Game {
    World world;
    OrthographicCamera camera;
    Box2DDebugRenderer debugRenderer;

    @Override
    public void create() {
        world = new World(new Vector2(0, 0), true);
        camera = new OrthographicCamera(100, 50);
        debugRenderer = new Box2DDebugRenderer();

        float scale = 2.0f;
        float moveX = -20f;
        float moveY = -10f;

        //WALLs parallel
        Wall wallAR = new Wall();
        //Wall wallCR = new Wall();
        Wall wallCRseg1 = new Wall();
        Wall wallCRseg2 = new Wall();
        Wall wallCRseg3 = new Wall();
        Wall wallER = new Wall();
        Wall wallFR = new Wall();
        Wall wallMR = new Wall();
        Wall wallNR = new Wall();
        Wall wallGR = new Wall();
        Wall wallHR = new Wall();
        Wall wallOR = new Wall();
        Wall wallPR = new Wall();
        Wall wallIR = new Wall();
        Wall wallJR = new Wall();
        Wall wallKR = new Wall();
        Wall wallLR = new Wall();

        wallAR.createWall(0f, 20f, 40f, 20f, world, scale, moveX, moveY);
        //wallCR.createWall(0f, 0f, 40f, 0f, world, scale, moveX, moveY);
        wallCRseg1.createWall(0f, 0f, 9f, 0f, world, scale, moveX, moveY);
        wallCRseg2.createWall(11f, 0f, 18f, 0f, world, scale, moveX, moveY);
        wallCRseg3.createWall(20f, 0f, 40f, 0f, world, scale, moveX, moveY);
        wallER.createWall(0f, 12f, 3f, 12f, world, scale, moveX, moveY);
        wallFR.createWall(5f, 12f, 22f, 12f, world, scale, moveX, moveY);
        wallMR.createWall(16f, 15f, 17f, 15f, world, scale, moveX, moveY);
        wallNR.createWall(19f, 17f, 27f, 17f, world, scale, moveX, moveY);
        wallGR.createWall(24f, 12f, 40f, 12f, world, scale, moveX, moveY);
        wallHR.createWall(0f, 7f, 9f, 7f, world, scale, moveX, moveY);
        wallOR.createWall(0f, 4f, 9f, 4f, world, scale, moveX, moveY);
        wallPR.createWall(11f, 4f, 12f, 4f, world, scale, moveX, moveY);
        wallIR.createWall(11f, 7f, 18f, 7f, world, scale, moveX, moveY);
        wallJR.createWall(20f, 7f, 21f, 7f, world, scale, moveX, moveY);
        wallKR.createWall(27f, 7f, 28f, 7f, world, scale, moveX, moveY);
        wallLR.createWall(30f, 7f, 40f, 7f, world, scale, moveX, moveY);

        //WALLs perpendicular
        Wall wallBP = new Wall();
        Wall wallCP = new Wall();
        Wall wallQP = new Wall();
        Wall wallTP = new Wall();
        //Wall wallUP = new Wall();
        Wall wallUP_T = new Wall();
        Wall wallUP_B = new Wall();
        Wall wallWP = new Wall();
        Wall wallSP = new Wall();
        Wall wallRP = new Wall();
        Wall wallZP = new Wall();

        wallBP.createWall(40f, 0f, 40f, 20f, world, scale, moveX, moveY);
        wallCP.createWall(0f, 0f, 0f, 20f, world, scale, moveX, moveY);
        wallQP.createWall(16f, 12f, 16f, 20f, world, scale, moveX, moveY);
        wallTP.createWall(21f, 19f, 21f, 20f, world, scale, moveX, moveY);
        wallUP_T.createWall(27f, 15f, 27f, 17f, world, scale, moveX, moveY);
        wallUP_B.createWall(27f, 12f, 27f, 13f, world, scale, moveX, moveY);
        wallWP.createWall(27f, 0f, 27f, 7f, world, scale, moveX, moveY);
        wallSP.createWall(21f, 0f, 21f, 7f, world, scale, moveX, moveY);
        wallRP.createWall(17f, 0f, 17f, 7f, world, scale, moveX, moveY);
        wallZP.createWall(12f, 0f, 12f, 5f, world, scale, moveX, moveY);

        //WALLs other
        Wall wallCrookedOne = new Wall();
        Wall wallCrookedTwo = new Wall();

        wallCrookedOne.createWall(17f, 15f, 18f, 16f, world, scale, moveX, moveY);
        wallCrookedTwo.createWall(19f, 17f, 21f, 19f, world, scale, moveX, moveY);


        wallAR.createWall(0f, 20f, 40f, 20f, world, scale, moveX, moveY);
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
