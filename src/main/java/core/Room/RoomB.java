package core.Room;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import core.Element.Human;
import core.Element.Wall;

public class RoomB extends Game {
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
        Wall wallAR = new Wall();
        Wall wallBR = new Wall();
        Wall wallCR = new Wall();

        wallAR.createWall(0f, 8f, 5f, 8f, world, scale, moveX, moveY);
        wallBR.createWall(5f, 10f, 15f, 10f, world, scale, moveX, moveY);
        wallCR.createWall(25f, 7f, 30f, 7f, world, scale, moveX, moveY);

        //WALLs perpendicular
        Wall wallAP = new Wall();
        Wall wallBP = new Wall();
        Wall wallCP = new Wall();
        Wall wallDP = new Wall();
        Wall wallEP = new Wall();
        Wall wallFP = new Wall();
        Wall wallGP = new Wall();
        Wall wallHP = new Wall();
        Wall wallIP = new Wall();
        Wall wallJP = new Wall();
        Wall wallKP = new Wall();

        wallAP.createWall(0f, 8f, 0f, 18f, world, scale, moveX, moveY);
        wallBP.createWall(5f, 5f, 5f, 10f, world, scale, moveX, moveY);
        wallCP.createWall(15f, 3f, 15f, 10f, world, scale, moveX, moveY);
        wallDP.createWall(20f, 2f, 20f, 7f, world, scale, moveX, moveY);
        wallEP.createWall(25f, 1f, 25f, 12f, world, scale, moveX, moveY);
        wallFP.createWall(30f, 0f, 30f, 24f, world, scale, moveX, moveY);
        wallGP.createWall(22f, 9f, 22f, 14f, world, scale, moveX, moveY);
        wallHP.createWall(17f, 12f, 17f, 13f, world, scale, moveX, moveY);
        wallIP.createWall(5f, 13f, 5f, 19f, world, scale, moveX, moveY);
        wallJP.createWall(15f, 15f, 15f, 21f, world, scale, moveX, moveY);
        wallKP.createWall(25f, 17f, 25f, 23f, world, scale, moveX, moveY);

        //WALLs other
        Wall wallCrooked1 = new Wall();
        Wall wallCrooked2 = new Wall();
        Wall wallCrooked3 = new Wall();
        Wall wallCrooked4 = new Wall();
        Wall wallCrooked5 = new Wall();
        Wall wallCrooked6 = new Wall();
        Wall wallCrooked7 = new Wall();

        wallCrooked1.createWall(0f, 18f, 30f, 24f, world, scale, moveX, moveY);
        wallCrooked2.createWall(0f, 12f, 30f, 18f, world, scale, moveX, moveY);
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
