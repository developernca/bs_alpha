package com.developernca.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Timer;
import com.developernca.actor.BirdActor;
import com.developernca.actor.BladeActor;
import com.developernca.actor.ExplosionActor;
import com.developernca.actor.LevelupParticleActor;
import com.developernca.actor.LiveActor;
import com.developernca.actor.BonusActor;
import com.developernca.actor.StillAtlasActor;
import com.developernca.actor.TouchAreaActor;
import com.developernca.game.BSGame;
import com.developernca.utility.BSUtils;
import com.developernca.utility.CameraShaker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 4/21/2018.
 *
 * @author Nyein Chan Aung
 * @since 1.0
 */

public class GamePlayScreen extends BSScreen {
    // data
    private int live;
    private int actualYTouchPos;
    private int bladeHalfWidth;
    private int score;
    private int birdLevel;
    private int levelLength;
    private boolean shouldGameStageCamShake = false;
    private int bonusActorCount;
    private float birdActorLowestYPos;
    /**
     * How many birds can shoot at one blade shoot and
     * this is used in making bonus.
     */
    private int destroyInOneShoot;
    // stage
    private Stage bgStage;
    private Stage gameStage;
    private Stage uiStage;
    // camera
    private Camera gameStageCam;
    private CameraShaker gameStageCameraShaker;
    // Texture Atlas
    private TextureAtlas explosionAtlas;
    // actors
    private TouchAreaActor touchAreaActor;
    private BladeActor bladeActor;
    private LiveActor liveActor;
    private LevelupParticleActor levelupParticleActor;
    // label
    private Label lblHighScore;
    private Label lblDebug; // label to show nodes
    // collection
    private ArrayList<BirdActor> birdList;
    private final int[] levelUpScoreArr = {0, 10, 20, 30};
    private final int[] birdSpawnArr = {10, 1, 1, 1};// {10, 5, 3, 4}
    // String
    private String scoreDefaultTxt;
    // Timer
    private Timer timer;

    GamePlayScreen(BSGame game) {
        super(game);
        // initialize data
        live = 5;
        birdLevel = 0;
        levelLength = levelUpScoreArr.length;
        destroyInOneShoot = 0;
        bonusActorCount = BonusActor.Type.values().length - 1;
        // initialize stages
        bgStage = new Stage();
        gameStage = new Stage();
        uiStage = new Stage();
        // initialize camera
        gameStageCam = gameStage.getCamera();
        gameStageCameraShaker = new CameraShaker(BSGame.as.pt(10.5f), BSGame.as.pt(10.5f), 55);
        // initialize actors
        // initialize clouds
        game.setupCloudActorList(bgStage);
        // initialize touch area
        touchAreaActor = new TouchAreaActor(0.0f, BSGame.as.pt(10.0f), Gdx.files.internal(BSGame.as.baf + "/touch_area.png"));
        birdActorLowestYPos = touchAreaActor.touchAreaTopYPos + BSGame.as.pt(100.0f);
        // label high score
        scoreDefaultTxt = game.i18NBundle.get("score") + " : ";
        lblHighScore = BSUtils.makeLabel(scoreDefaultTxt + String.format("%04d", 0), game.ttfName, Color.BLACK, (int) BSGame.as.pt(18.0f));
        lblHighScore.setPosition(0.0f, BSGame.gh - lblHighScore.getHeight());
        // label debug
        lblDebug = BSUtils.makeLabel("", game.ttfName, Color.BLACK, (int) BSGame.as.pt(12.0f));
        lblDebug.setPosition(0.0f, BSGame.gh - (lblHighScore.getHeight() + lblDebug.getHeight() + 10.0f));
        // player live actor
        liveActor = new LiveActor(0, 0, BSGame.atlas1.findRegion("live5"));
        // blade actor
        bladeActor = new BladeActor(0.0f, BSGame.gh, BSGame.atlas1.findRegion("blade_lvl1"), this);
        bladeHalfWidth = (int) bladeActor.getWidth() / 2;
        // level up particle actor
        levelupParticleActor = new LevelupParticleActor(BSGame.gw / 2, 0.0f, "particle/levelup_txt.pe", BSGame.atlas1);
        // bird actor list
        BirdActor.parentScreen = this;
        birdList = new ArrayList<>();
        // explosion atlas
        explosionAtlas = new TextureAtlas(Gdx.files.internal(BSGame.as.baf + "/anim/gray_explode_atlas.txt"));
        // Timer
        timer = new Timer();
        // add actors to stage
        // game stage
        gameStage.addActor(touchAreaActor);
        gameStage.addActor(bladeActor);
        // ui stage
        uiStage.addActor(lblHighScore);
        uiStage.addActor(lblDebug);
        uiStage.addActor(liveActor);
    }

    @Override
    public void show() {
        super.show();
        BSGame.gamePlayBgMusic.setVolume(0.5f);
        BSGame.gamePlayBgMusic.setLooping(true);
        BSGame.gamePlayBgMusic.play();
        Gdx.input.setInputProcessor(this);
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void hide() {
        super.hide();
        BSGame.gamePlayBgMusic.stop();
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        bgStage.act(delta);
        bgStage.draw();
        update();
        gameStage.act(delta);
        gameStage.draw();
        uiStage.act(delta);
        uiStage.draw();
    }

    @Override
    public boolean keyDown(int keycode) {
        // on back press
        if (keycode == Input.Keys.BACK) {
            liveActor.resetRegionWidth();
            game.setScreen(BSGame.menuScreen);
        }
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        actualYTouchPos = BSGame.gh - screenY;
        if (touchAreaActor.isTouched(screenX, actualYTouchPos) && bladeActor.canSpawn()) {
            bladeActor.spawn(screenX - bladeHalfWidth, actualYTouchPos);
        }
        return true;
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    private void update() {
        lblDebug.setText("List Size : " + birdList.size());
        if (live == 0) {// game over
//            liveActor.resetRegionWidth();
//            if (score > BSGame.highScore) {
//                game.pref.putInteger(BSGame.H_SCORE, score);
//                game.pref.flush();
//            }
//            BSGame.bottleBreakSound.stop();
//
//            Map<String, String> result = new HashMap<>();
//            result.put("score", String.valueOf(score));
//            result.put("hi_score", String.valueOf(BSGame.highScore));
//            game.setScreen(new GameOverScreen(game, result));
            return;
        }

        // shake camera
        if (shouldGameStageCamShake) {
            shouldGameStageCamShake = gameStageCameraShaker.shake(gameStageCam);
        }

        if (birdLevel < levelLength && score == levelUpScoreArr[birdLevel]) {
            if (score != 0) {
                bladeActor.levelUp();
                gameStage.addActor(levelupParticleActor);
                // shouldGameStageCamShake = true;// The game stage camera should shake
            }
            TextureAtlas birdAtlas = new TextureAtlas(Gdx.files.internal(BSGame.as.baf + "/anim/lvl" + birdLevel + "_bird_atlas.txt"));
            BirdSpawnTimer birdSpawnTimer = new BirdSpawnTimer(birdAtlas, birdLevel, birdSpawnArr[birdLevel]);
            timer.scheduleTask(birdSpawnTimer, 1.5f, 1.5f);
            timer.start();
            birdLevel++;
        }
        bladeAndActorOverlap();
    }

    /**
     * Make bonus item.
     */
    public void makeBonusItem() {
        if (destroyInOneShoot < 3 || score <= 5) {// no chance to give bonus -> score back to 100, destroyInOneShoot < 6
            destroyInOneShoot = 0;
            return;
        }
        // make bonus decision
        if (destroyInOneShoot == 3) {
            BonusActor bonusActor = new BonusActor(BSGame.atlas1.findRegion(BonusActor.Type.BIRD_BOMB_LVL0.get()), BonusActor.Type.BIRD_BOMB_LVL0, this);
            uiStage.addActor(bonusActor);
        } else if (destroyInOneShoot > 3) {
            int rand = MathUtils.random(1, bonusActorCount);// start from one because index 0 is LEVEL0 bird destroy bomb.
            boolean bonusOk = false;
            while (!bonusOk) {
                if (rand == bonusActorCount && live == 5) {// random bonus give live bonus (because bonusActorCount is the last index of Type enum and is LIVE bonus) but player has 5 live.
                    // re-make bonus
                    rand = MathUtils.random(1, bonusActorCount);
                    continue;
                }
                BonusActor.Type bonusType = BonusActor.Type.values()[rand];
                BonusActor bonusActor;
                if (bonusType == BonusActor.Type.LIVE) {
                    bonusActor = new BonusActor(BSGame.atlas1.findRegion(bonusType.get()), bonusType, this);
                } else {
                    bonusActor = new BonusActor(BSGame.atlas1.findRegion(bonusType.get()), bonusType, this);
                }
                uiStage.addActor(bonusActor);
                bonusOk = true;
            }
        }
        destroyInOneShoot = 0;
    }

    /**
     * Use bonus item when user touch bonus item.
     */
    public void applyBonusOnBird(BonusActor.Type bonusType) {
        int listSize = birdList.size() - 1;
        int bonusLevel = -1;
        if (bonusType == BonusActor.Type.BIRD_BOMB_LVL0) {// brown bomb
            bonusLevel = 0;
        } else if (bonusType == BonusActor.Type.BIRD_BOMB_LVL1) {// green bomb
            bonusLevel = 1;
        }
//        if (bonusLevel >= 0) {
//            for (int i = listSize; i >= 0; i--) {
//                if (birdList.get(i).level == bonusLevel) {
//                    birdList.get(i).flyAction.restart();
//                }
//            }
//        }
    }

    public void increaseLive() {
        liveActor.increaseLive(++live);
    }

    /**
     * Check whether blade and one or more of the birds overlap
     */
    private void bladeAndActorOverlap() {
        for (BirdActor bird : birdList) {
            if (bird.isCircleOverlap(bladeActor.boundaryCircle)) {
                ExplosionActor explosionActor = new ExplosionActor(bird.getX(), bird.getY(), 0.03333f, explosionAtlas, Animation.PlayMode.NORMAL);
                gameStage.addActor(explosionActor);
                bird.setRandomPosition(birdActorLowestYPos);
                bird.flyAction.restart();
                lblHighScore.setText(scoreDefaultTxt + String.format("%04d", ++score));
                ++destroyInOneShoot;
            }
        }
    }

    /**
     * Reduce player live when a bird go off the
     * right side of the screen.
     */
    public void reducePlayerLive() {
        if (live > 1) {
            // make live bottle fall
            StillAtlasActor liveBottleActor = new StillAtlasActor(0, 0, BSGame.atlas1.findRegion("live"));
            liveBottleActor.setPosition(BSGame.gw - liveBottleActor.getWidth() - BSGame.as.pt(10.0f), BSGame.gh - liveBottleActor.getHeight());
            uiStage.addActor(liveBottleActor);
            liveBottleActor.addAction(Actions.sequence(Actions.moveTo(liveBottleActor.getX(), -liveBottleActor.getHeight(), 2.0f), Actions.run(() -> {
                liveBottleActor.remove();
            })));
            BSGame.bottleBreakSound.play(1.0f);
        }
        liveActor.decreaseLive(--live);
    }

    /**
     * Timer to spawn bird.
     */
    private class BirdSpawnTimer extends Timer.Task {

        TextureAtlas atlas;
        int level;
        int spawnCount;
        int count;

        BirdSpawnTimer(TextureAtlas atlas, int level, int spawnCount) {
            this.atlas = atlas;
            this.level = level;
            this.spawnCount = spawnCount;
            this.count = 0;
        }

        @Override
        public void run() {
            if (count == spawnCount) {
                cancel();
                System.out.println("Info: cancel cond: count => " + count + ", spawncount => " + spawnCount);
                return;
            }
            birdList.add(new BirdActor(birdActorLowestYPos, 0.06f, atlas, level));
            gameStage.addActor(birdList.get(birdList.size() - 1));
            ++count;
        }
    }
}
