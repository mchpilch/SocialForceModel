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

public class RoomA extends Game {
    World world;
    OrthographicCamera camera;
    Box2DDebugRenderer debugRenderer;

    @Override
    public void create() {
        world = new World(new Vector2(0, 0), true);
        camera = new OrthographicCamera(100, 50);
        debugRenderer = new Box2DDebugRenderer();

        float scale = 1.25f;
        float moveX = -20f;
        float moveY = -10f;

        Wall wallAR = new Wall();
        Wall wallBP = new Wall();
        Wall wallCR = new Wall();
        Wall wallDP = new Wall();

        wallAR.createWall(0f, 20f, 40f, 20f, 0, world, scale, moveX, moveY); // top wall
        //wallCR.createWall(0f + scaleX, 0f + scaleY, 40f + scaleX, 0f + scaleY, 0, world); // top wall
//        wallBP.createWall(0f, 10f, 0f, 0f, 0, world); // top wall
//        wallCR.createWall(0f, 10f, 0f, 0f, 0, world); // top wall
//        wallDP.createWall(0f, 10f, 0f, 0f, 0, world); // top wall
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
