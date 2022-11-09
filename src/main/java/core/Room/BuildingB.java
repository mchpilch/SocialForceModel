package core.Room;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import core.Element.Wall;

public class BuildingB extends Game {
    World world;
    OrthographicCamera camera;
    Box2DDebugRenderer debugRenderer;

    @Override
    public void create() {
        world = new World(new Vector2(0, 0), true);
        camera = new OrthographicCamera(100, 50);
        debugRenderer = new Box2DDebugRenderer();

        float scale = 1.0f;
        float moveX = -20f;
        float moveY = -10f;

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
