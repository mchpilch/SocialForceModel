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

public class RoomC extends Game {
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
        float moveY = -20f;

        Wall wallAR = new Wall();
        Wall wallBR_L = new Wall();
        Wall wallBR_P = new Wall();
        Wall wallCR_L = new Wall();
        Wall wallCR_P = new Wall();
        Wall wallDR = new Wall();


        wallAR.createWall(0f, 0f, 32f, 0f, world, scale, moveX, moveY);
        wallBR_L.createWall(0f, 12f, 23f, 12f, world, scale, moveX, moveY);
        wallBR_P.createWall(25f, 12f, 32f, 12f, world, scale, moveX, moveY);
        wallCR_L.createWall(0f, 20f, 7f, 20f, world, scale, moveX, moveY);
        wallCR_P.createWall(9f, 20f, 32f, 20f, world, scale, moveX, moveY);
        wallDR.createWall(0f, 32f, 32f, 32f, world, scale, moveX, moveY);

        Wall wallAP_T = new Wall();
        Wall wallAP_B = new Wall();
        Wall wallBP_T = new Wall();
        Wall wallBP_B = new Wall();
        Wall wallCP_T = new Wall();
        Wall wallCP_B = new Wall();
        Wall wallDP_T = new Wall();
        Wall wallDP_B = new Wall();

        wallAP_T.createWall(0f, 17f, 0f, 32f, world, scale, moveX, moveY);
        wallAP_B.createWall(0f, 0f, 0f, 15f, world, scale, moveX, moveY);
        wallBP_T.createWall(32f, 17f, 32f, 32f, world, scale, moveX, moveY);
        wallBP_B.createWall(32f, 0f, 32f, 15f, world, scale, moveX, moveY);
        wallCP_T.createWall(16f, 27f, 16f, 32f, world, scale, moveX, moveY);
        wallCP_B.createWall(16f, 20f, 16f, 25f, world, scale, moveX, moveY);
        wallDP_T.createWall(16f, 7f, 16f, 12f, world, scale, moveX, moveY);
        wallDP_B.createWall(16f, 0f, 16f, 5f, world, scale, moveX, moveY);

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
